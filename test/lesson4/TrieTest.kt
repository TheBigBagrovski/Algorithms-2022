package lesson4

import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

class TrieTest : AbstractTrieTest() {

    override fun create(): MutableSet<String> =
        Trie()

    @Test
    @Tag("Example")
    fun generalTestJava() {
        doGeneralTest()
    }

    @Test
    @Tag("7")
    fun iteratorTestJava() {
        doIteratorTest()
        //дополнительный тест
        doAdditionalIteratorTest()
    }

    @Test
    @Tag("8")
    fun iteratorRemoveTestJava() {
        doIteratorRemoveTest()
        //дополнительный тест
        doAdditionalIteratorRemoveTest()
    }

}