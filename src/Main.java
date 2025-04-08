import java.io.FileNotFoundException;
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Map;

public class Main {
    private static final String FILE_NAME = "data.json";
    private static final Gson gson = new Gson();

    interface SortAlgorithm {
        void sort(int[] a);
    }

    private static void MeasureTime(Map<String, int[]> data, SortAlgorithm algorithm, String name) {
        int[] aCopy;
        for (int i = 0; i < 10; i++) {
            aCopy = Arrays.copyOf(data.get("1000000_1000"), 1000000);
            algorithm.sort(aCopy);
        }

        data.forEach((key, a) -> {
            var start = System.nanoTime();
            algorithm.sort(a);
            var end = System.nanoTime();
            double durationMs = (end - start) / 1e6;
            System.out.printf("%8.3f\n", durationMs);
//            System.out.printf("%-10s | %s | %8.3f ms\n", name, format(key), durationMs);
        });
    }

    private static String format(String input) {
        var s = input.split("_");
        s[0] = replaceTrailingZeros(s[0]);
        s[1] = replaceTrailingZeros(s[1]).replace("2147483647", "max_int");
        return String.format("%-4s  %7s", s[0], s[1]);
    }

    private static String replaceTrailingZeros(String input) {
        int count = 0;
        for (int i = input.length() - 1; i >= 0 && input.charAt(i) == '0'; i--) {
            count++;
        }
        int groups = count / 3;
        if (groups == 0) {
            return input;
        }
        int cutIndex = input.length() - groups * 3;
        return input.substring(0, cutIndex) + "k".repeat(groups);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Type dataType = new TypeToken<Map<String, int[]>>() {
        }.getType();
        Map<String, int[]> data = gson.fromJson(new FileReader(FILE_NAME), dataType);

//        MeasureTime(data, Arrays::sort, "Дефолтная сортировка");
//        MeasureTime(data, Sorting::quickSort, "Быстрая сортировка");
//        MeasureTime(data, Sorting::heapSort, "Пирамидальная сортировка");
//        MeasureTime(data, Sorting::radixSort, "Поразрядная сортировка");
        MeasureTime(data, Sorting::bubbleSort, "Пузырьковая сортировка");

    }
}