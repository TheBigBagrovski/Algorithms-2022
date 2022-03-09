package lesson1;

import kotlin.NotImplementedError;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class JavaTasks {
    /**
     * Сортировка времён
     * <p>
     * Простая
     * (Модифицированная задача с сайта acmp.ru)
     * <p>
     * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС AM/PM,
     * каждый на отдельной строке. См. статью википедии "12-часовой формат времени".
     * <p>
     * Пример:
     * <p>
     * 01:15:19 PM
     * 07:26:57 AM
     * 10:00:03 AM
     * 07:56:14 PM
     * 01:15:19 PM
     * 12:40:31 AM
     * <p>
     * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
     * сохраняя формат ЧЧ:ММ:СС AM/PM. Одинаковые моменты времени выводить друг за другом. Пример:
     * <p>
     * 12:40:31 AM
     * 07:26:57 AM
     * 10:00:03 AM
     * 01:15:19 PM
     * 01:15:19 PM
     * 07:56:14 PM
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortTimes(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Сортировка адресов
     * <p>
     * Средняя
     * <p>
     * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
     * где они прописаны. Пример:
     * <p>
     * Петров Иван - Железнодорожная 3
     * Сидоров Петр - Садовая 5
     * Иванов Алексей - Железнодорожная 7
     * Сидорова Мария - Садовая 5
     * Иванов Михаил - Железнодорожная 7
     * <p>
     * Людей в городе может быть до миллиона.
     * <p>
     * Вывести записи в выходной файл outputName,
     * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
     * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
     * <p>
     * Железнодорожная 3 - Петров Иван
     * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
     * Садовая 5 - Сидоров Петр, Сидорова Мария
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortAddresses(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Сортировка температур
     * <p>
     * Средняя
     * (Модифицированная задача с сайта acmp.ru)
     * <p>
     * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
     * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
     * Например:
     * <p>
     * 24.7
     * -12.6
     * 121.3
     * -98.4
     * 99.5
     * -12.6
     * 11.0
     * <p>
     * Количество строк в файле может достигать ста миллионов.
     * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
     * Повторяющиеся строки сохранить. Например:
     * <p>
     * -98.4
     * -12.6
     * -12.6
     * 11.0
     * 24.7
     * 99.5
     * 121.3
     */
    //временные затраты: O(N) (сортировка подсчетом)
    //затраты памяти: O(1) (не зависят от количества температур)
    static public void sortTemperatures(String inputName, String outputName) throws IOException {
        int[] positive = new int[5001];
        int[] negative = new int[2731];
        String string;
        float number;
        int intNumber;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputName))) {
            string = reader.readLine();
            while (string != null) {
                number = Float.parseFloat(string);
                number *= 10;
                if (number < 0) {
                    number *= -1;
                    intNumber = (int) number;
                    negative[intNumber]++;
                } else {
                    intNumber = (int) number;
                    positive[intNumber]++;
                }
                string = reader.readLine();
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputName))) {
            for (int i = 2730; i > 0; i--) {
                if (negative[i] != 0) {
                    number = (float) i / (-10);
                    string = Float.toString(number);
                    for (int j = 0; j < negative[i]; j++) {
                        writer.write(string + System.getProperty("line.separator"));
                    }
                }
            }
            for (int i = 0; i < 5001; i++) {
                if (positive[i] != 0) {
                    number = (float) i / (10);
                    string = Float.toString(number);
                    for (int j = 0; j < positive[i]; j++) {
                        writer.write(string + System.getProperty("line.separator"));
                    }
                }
            }
        }
    }

    /**
     * Сортировка последовательности
     * <p>
     * Средняя
     * (Задача взята с сайта acmp.ru)
     * <p>
     * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
     * <p>
     * 1
     * 2
     * 3
     * 2
     * 3
     * 1
     * 2
     * <p>
     * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
     * а если таких чисел несколько, то найти минимальное из них,
     * и после этого переместить все такие числа в конец заданной последовательности.
     * Порядок расположения остальных чисел должен остаться без изменения.
     * <p>
     * 1
     * 3
     * 3
     * 1
     * 2
     * 2
     * 2
     */
    //временные затраты: O(N)
    //затраты памяти: O(N)
    static public void sortSequence(String inputName, String outputName) throws IOException {
        List<Integer> numbers = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputName))) {
            String string = reader.readLine();
            while (string != null) {
                Integer current = Integer.parseInt(string);
                numbers.add(current);
                if (!map.containsKey(current)) map.put(current, 1);
                else map.put(current, map.get(current) + 1);
                string = reader.readLine();
            }
        }
        int numToWrite = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (numToWrite == 0) numToWrite = entry.getKey();
            else if (map.get(numToWrite) < entry.getValue()) numToWrite = entry.getKey();
            else if (map.get(numToWrite).equals(entry.getValue()) && entry.getKey() < numToWrite)
                numToWrite = entry.getKey();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputName))) {
            if (!map.isEmpty()) {
                for (Integer number : numbers) {
                    if (number != numToWrite) writer.write(number + System.getProperty("line.separator"));
                }
                for (int i = 0; i < map.get(numToWrite); i++) {
                    writer.write(numToWrite + System.getProperty("line.separator"));
                }
            }
        }
    }

    /**
     * Соединить два отсортированных массива в один
     * <p>
     * Простая
     * <p>
     * Задан отсортированный массив first и второй массив second,
     * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
     * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
     * <p>
     * first = [4 9 15 20 28]
     * second = [null null null null null 1 3 9 13 18 23]
     * <p>
     * Результат: second = [1 3 4 9 9 13 15 20 23 28]
     */
    static <T extends Comparable<T>> void mergeArrays(T[] first, T[] second) {
        throw new NotImplementedError();
    }
}
