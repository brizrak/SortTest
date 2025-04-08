import java.util.Arrays;

public class Sorting {
    public static void quickSort(int[] a) {
        quickSort(a, 0, a.length - 1);
    }

    private static void quickSort(int[] a, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(a, left, right);
            quickSort(a, left, pivotIndex);
            quickSort(a, pivotIndex + 1, right);
        }
    }

    private static int partition(int[] a, int left, int right) {
        int pivot = a[(left + right) / 2];
        int i = left;
        int j = right;

        while (true) {
            while (i <= right && a[i] < pivot) i++;
            while (j >= left && a[j] > pivot) j--;

            if (i >= j) return j;

            swap(a, i, j);
            i++;
            j--;
        }
    }

    public static void heapSort(int[] a) {
        int n = a.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            siftUp(a, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            swap(a, 0, i);
            siftUp(a, i, 0);
        }
    }

    private static void siftUp(int[] a, int size, int root) {
        int current = root;
        while (true) {
            int left = 2 * current + 1;
            int right = 2 * current + 2;
            int largest = current;

            if (left < size && a[left] > a[largest]) {
                largest = left;
            }
            if (right < size && a[right] > a[largest]) {
                largest = right;
            }

            if (largest == current) break;

            swap(a, current, largest);
            current = largest;
        }
    }

    public static void radixSort(int[] a) {
        if (a.length == 0) return;
        int max = Arrays.stream(a).max().getAsInt();

        for (int exp = 1; max / exp > 0; exp *= 10) {
            hookingUpOfQueues(a, exp);
        }
    }

    private static void hookingUpOfQueues(int[] a, int exp) {
        int[] output = new int[a.length];
        int[] count = new int[10];

        for (int num : a) {
            int digit = (num / exp) % 10;
            count[digit]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = a.length - 1; i >= 0; i--) {
            int digit = (a[i] / exp) % 10;
            output[count[digit] - 1] = a[i];
            count[digit]--;
        }

        System.arraycopy(output, 0, a, 0, a.length);
    }

    public static void bubbleSort(int[] a) {
        int n = a.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                    swapped = true;
                }
            }

            if (!swapped) break;
        }
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
