public class Task396 {
    public static int sumEvenIndex(int[] arr) {
        return sumEvenIndexHelper(arr, 0);
    }

    private static int sumEvenIndexHelper(int[] arr, int index) {
        // Base case: index past array bounds
        if (index >= arr.length) {
            return 0;
        }

        // Add current element + recurse at index + 2
        return arr[index] + sumEvenIndexHelper(arr, index + 2);
    }

    public static void main(String[] args) {
        IO.println(sumEvenIndex(new int[]{10, 5, 20, 5, 30})); // 60
        IO.println(sumEvenIndex(new int[]{1, 2, 3, 4}));        // 4
        IO.println(sumEvenIndex(new int[]{7}));                 // 7
        IO.println(sumEvenIndex(new int[]{2, 9}));              // 2
    }
}
