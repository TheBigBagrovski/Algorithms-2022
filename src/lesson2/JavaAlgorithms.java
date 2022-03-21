package lesson2;

import kotlin.NotImplementedError;
import kotlin.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@SuppressWarnings("unused")
public class JavaAlgorithms {
    /**
     * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
     * Простая
     * <p>
     * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
     * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
     * <p>
     * 201
     * 196
     * 190
     * 198
     * 187
     * 194
     * 193
     * 185
     * <p>
     * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
     * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
     * Вернуть пару из двух моментов.
     * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
     * Например, для приведённого выше файла результат должен быть Pair(3, 4)
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    //временные затраты: O(N) (проход по всем числам)
    //затраты памяти: O(1)    (все числа нигде не хранятся)
    static public Pair<Integer, Integer> optimizeBuyAndSell(String inputName) throws IOException {
        int maxProfit = 0, minPrice, currentPrice;
        int probableMinDay, resultMinDay = 0, resultMaxDay = 0, dayCounter = 1;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputName))) {
            String string = reader.readLine();
            if (!string.matches("^\\d+$")) throw new IllegalArgumentException();
            minPrice = Integer.parseInt(string);
            probableMinDay = dayCounter;
            string = reader.readLine();
            while (string != null) {
                if (!string.matches("^\\d+$")) throw new IllegalArgumentException();
                currentPrice = Integer.parseInt(string);
                dayCounter++;
                if (minPrice > currentPrice) {
                    minPrice = currentPrice;
                    probableMinDay = dayCounter;
                }
                if (currentPrice - minPrice > maxProfit) {
                    maxProfit = currentPrice - minPrice;
                    resultMaxDay = dayCounter;
                    resultMinDay = probableMinDay;
                }
                string = reader.readLine();
            }
        }
        return new Pair<>(resultMinDay, resultMaxDay);
    }

    /**
     * Задача Иосифа Флафия.
     * Простая
     * <p>
     * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
     * <p>
     * 1 2 3
     * 8   4
     * 7 6 5
     * <p>
     * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
     * Человек, на котором остановился счёт, выбывает.
     * <p>
     * 1 2 3
     * 8   4
     * 7 6 х
     * <p>
     * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
     * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
     * <p>
     * 1 х 3
     * 8   4
     * 7 6 Х
     * <p>
     * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
     * <p>
     * 1 Х 3
     * х   4
     * 7 6 Х
     * <p>
     * 1 Х 3
     * Х   4
     * х 6 Х
     * <p>
     * х Х 3
     * Х   4
     * Х 6 Х
     * <p>
     * Х Х 3
     * Х   х
     * Х 6 Х
     * <p>
     * Х Х 3
     * Х   Х
     * Х х Х
     * <p>
     * Общий комментарий: решение из Википедии для этой задачи принимается,
     * но приветствуется попытка решить её самостоятельно.
     */
    static public int josephTask(int menNumber, int choiceInterval) {
        throw new NotImplementedError();
    }

    /**
     * Наибольшая общая подстрока.
     * Средняя
     * <p>
     * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
     * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
     * Если общих подстрок нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     * Если имеется несколько самых длинных общих подстрок одной длины,
     * вернуть ту из них, которая встречается раньше в строке first.
     */
    //N, M - длины первой и второй строк соответственно
    //временные затраты: O(N*M) (вложенный цикл)
    //затраты памяти: O(N*M)    (массив N*M)
    static public String longestCommonSubstring(String first, String second) {
        int[][] arr = new int[first.length() + 1][second.length() + 1];
        int maxLength = 0, maxIndex = 0;
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= first.length(); i++) {
            for (int j = 1; j <= second.length(); j++) {
                if (first.charAt(i - 1) == second.charAt(j - 1)) {
                    arr[i][j] = arr[i - 1][j - 1] + 1;
                    if (arr[i][j] > maxLength) {
                        maxLength = arr[i][j];
                        maxIndex = i;
                    }
                }
            }
        }
        for (int i = maxIndex - maxLength; i < maxIndex; i++) result.append(first.charAt(i));
        return result.toString();
    }

    /**
     * Число простых чисел в интервале
     * Простая
     * <p>
     * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
     * Если limit <= 1, вернуть результат 0.
     * <p>
     * Справка: простым считается число, которое делится нацело только на 1 и на себя.
     * Единица простым числом не считается.
     */
    //временные затраты: O(N*log(logN)) (решето Эратосфена)
    //затраты памяти: O(N)    (массив длиной limit + 1)
    static public int calcPrimesNumber(int limit) {
        if (limit <= 1) return 0;
        boolean[] isPrime = new boolean[limit + 1];
        for (int i = 2; i <= limit; i++) isPrime[i] = true;
        int result = 0;
        for (int i = 2; i <= limit; i++) {
            if (isPrime[i]) result++;
            for (int j = i * 2; j <= limit; j += i) { //при j=i*i на больших значениях происходит выход за пределы int
                isPrime[j] = false;
            }
        }
        return result;
    }
}