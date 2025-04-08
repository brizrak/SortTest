import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ArrayGenerator {
    private static final String FILE_NAME = "data.json";
    private static final Gson gson = new Gson();

    public static void generate() throws IOException {
        Map<String, int[]> data = new LinkedHashMap<>();
        int[] sizes = {100, 1000, 10000, 1000000, 10000000};
        int[] volumes = {100, 1000, 100000, Integer.MAX_VALUE};

        for (int s : sizes) {
            for (int v : volumes) {
                data.put(s + "_" + v, randomFill(s, v));
            }
        }

        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            gson.toJson(data, writer);
        }
    }

    private static int[] randomFill(int size, int volume) {
        var r = new Random();
        var a = new int[size];
        for (int i = 0; i < size; i++) {
            a[i] = r.nextInt(volume);
        }
        return a;
    }

    public static void main(String[] args) throws IOException {
        generate();
    }
}
