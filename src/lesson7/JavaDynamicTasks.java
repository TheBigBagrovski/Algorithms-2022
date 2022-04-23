package lesson7;

import kotlin.NotImplementedError;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;

@SuppressWarnings("unused")
public class JavaDynamicTasks {
    /**
     * Наибольшая общая подпоследовательность.
     * Средняя
     * <p>
     * Дано две строки, например "nematode knowledge" и "empty bottle".
     * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
     * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
     * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
     * Если общей подпоследовательности нет, вернуть пустую строку.
     * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
     * При сравнении подстрок, регистр символов *имеет* значение.
     */
    //временные затраты: O(m*n)
    //затраты памяти: O(m*n)
    public static String longestCommonSubSequence(String first, String second) {
        int m = first.length(), n = second.length();
        int[][] lcs_matrix = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) lcs_matrix[i][j] = 0;
                else if (first.charAt(i - 1) == second.charAt(j - 1))
                    lcs_matrix[i][j] = lcs_matrix[i - 1][j - 1] + 1;
                else lcs_matrix[i][j] = max(lcs_matrix[i - 1][j], lcs_matrix[i][j - 1]);
            }
        }
        int index = lcs_matrix[m][n];
        char[] answer = new char[index];
        while (m > 0 && n > 0) {
            if (first.charAt(m - 1) == second.charAt(n - 1)) {
                answer[index - 1] = first.charAt(m - 1);
                m--;
                n--;
                index--;
            } else if (lcs_matrix[m - 1][n] > lcs_matrix[m][n - 1]) m--;
            else n--;
        }
        return new String(answer);
    }

    /**
     * Наибольшая возрастающая подпоследовательность
     * Сложная
     * <p>
     * Дан список целых чисел, например, [2 8 5 9 12 6].
     * Найти в нём самую длинную возрастающую подпоследовательность.
     * Элементы подпоследовательности не обязаны идти подряд,
     * но должны быть расположены в исходном списке в том же порядке.
     * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
     * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
     * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
     */
    //временные затраты: O(NlogN)
    //затраты памяти: O(N)
    public static List<Integer> longestIncreasingSubSequence(List<Integer> list) {
        int longestLength = 0;
        int[] prevIndices = new int[list.size()];
        int[] curIndices = new int[list.size() + 1];
        for (int i = list.size() - 1; i >= 0; i--) {
            //бинарный поиск (+ обход по списку => трудоемкость O(N*logN) )
            int lo = 1; //нижняя граница
            int hi = longestLength; //верхняя граница
            while (lo <= hi) {
                int mid = (hi + lo) / 2;
                if (list.get(curIndices[mid]) >= list.get(i)) lo = mid + 1;
                else hi = mid - 1;
            }
            if (lo > longestLength) longestLength = lo;
            prevIndices[i] = curIndices[lo - 1];
            curIndices[lo] = i;
        }
        int k = curIndices[longestLength];
        List<Integer> answer = new ArrayList<>(longestLength);
        for (int i = longestLength - 1; i >= 0; i--) {
            answer.add(list.get(k));
            k = prevIndices[k];
        }
        return answer;
    }

    /**
     * Самый короткий маршрут на прямоугольном поле.
     * Средняя
     * <p>
     * В файле с именем inputName задано прямоугольное поле:
     * <p>
     * 0 2 3 2 4 1
     * 1 5 3 4 6 2
     * 2 6 2 5 1 3
     * 1 4 3 2 6 2
     * 4 2 3 1 5 0
     * <p>
     * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
     * В каждой клетке записано некоторое натуральное число или нуль.
     * Необходимо попасть из верхней левой клетки в правую нижнюю.
     * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
     * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
     * <p>
     * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
     */
    public static int shortestPathOnField(String inputName) {
        throw new NotImplementedError();
    }
}
