import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int count = 100_000;
        Integer[] a = generateArray(count);
        Integer[] b = Arrays.copyOf(a, count);
        Integer[] c = Arrays.copyOf(a, count);

        long start = System.currentTimeMillis();
        sortBubble(a);
        System.out.println("sortBubble - " + (System.currentTimeMillis() - start));

        long start1 = System.currentTimeMillis();
        sortInsertion(b);
        System.out.println("sortInsertion - " + (System.currentTimeMillis() - start1));

        long start2 = System.currentTimeMillis();
        sortSelection(c);
        System.out.println("sortSelection - " + (System.currentTimeMillis() - start2));
    }

    private static Integer[] generateArray(int count) {
        Integer[] out = new Integer[count];
        for (int i = 0; i <out.length ; i++) {
            out[i] = i;
        }
        return out;
    }

    private static void sortInsertion(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    private static void sortSelection(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    private static void sortBubble(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    private static void swapElements(Integer[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }
}