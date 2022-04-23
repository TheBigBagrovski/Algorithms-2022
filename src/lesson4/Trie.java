package lesson4;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * Префиксное дерево для строк
 */
public class Trie extends AbstractSet<String> implements Set<String> {

    private final Node root = new Node();
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root.children.clear();
        size = 0;
    }

    private String withZero(String initial) {
        return initial + (char) 0;
    }

    @Nullable
    private Node findNode(String element) {
        Node current = root;
        for (char character : element.toCharArray()) {
            if (current == null) return null;
            current = current.children.get(character);
        }
        return current;
    }

    @Override
    public boolean contains(Object o) {
        String element = (String) o;
        return findNode(withZero(element)) != null;
    }

    @Override
    public boolean add(String element) {
        Node current = root;
        boolean modified = false;
        for (char character : withZero(element).toCharArray()) {
            Node child = current.children.get(character);
            if (child != null) {
                current = child;
            } else {
                modified = true;
                Node newChild = new Node();
                current.children.put(character, newChild);
                current = newChild;
            }
        }
        if (modified) {
            size++;
        }
        return modified;
    }

    @Override
    public boolean remove(Object o) {
        String element = (String) o;
        Node current = findNode(element);
        if (current == null) return false;
        if (current.children.remove((char) 0) != null) {
            size--;
            return true;
        }
        return false;
    }

    @NotNull
    @Override
    public Iterator<String> iterator() {
        return new TrieIterator();
    }

    /**
     * Итератор для префиксного дерева
     * <p>
     * Спецификация: {@link Iterator} (Ctrl+Click по Iterator)
     * <p>
     * Сложная
     */

    private static class Node {
        SortedMap<Character, Node> children = new TreeMap<>();
    }

    public class TrieIterator implements Iterator<String> {
        private final int initialSize;
        private int nodesPassed;
        private final List<String> words;
        private String lastNode;

        //затраты памяти: O(N)
        private TrieIterator() {
            nodesPassed = 0;
            initialSize = size();
            words = new ArrayList<>();
            fillWords("", root);
        }

        //временные затраты: O(1)
        //затраты памяти: O(1)
        @Override
        public boolean hasNext() {
            return nodesPassed < initialSize;
        }

        //временные затраты: O(1)
        //затраты памяти: O(1)
        @Override
        public String next() {
            if (nodesPassed == initialSize || initialSize == 0) throw new NoSuchElementException();
            lastNode = words.get(nodesPassed);
            nodesPassed++;
            return lastNode;
        }

        //временные затраты: O(maxWordLength)
        //затраты памяти: O(1)
        @Override
        public void remove() {
            if (lastNode == null) throw new IllegalStateException();
            Trie.this.remove(lastNode);
            lastNode = null;
        }

        private void fillWords(String string, Node root) {
            for (char character : root.children.keySet()) {
                if (character != (char) 0) fillWords(string + character, root.children.get(character));
                else words.add(string);
            }
        }

    }

}