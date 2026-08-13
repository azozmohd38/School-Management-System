public class Task391 {
    public static boolean isSorted(int[] arr) {
        return isSortedHelper(arr, 0);
    }

    private static boolean isSortedHelper(int[] arr, int index) {
        // Base case: reached last element or empty array
        if (index >= arr.length - 1) {
            return true;
        }

        // Early exit if out of order
        if (arr[index] > arr[index + 1]) {
            return false;
        }

        // Recurse on next index
        return isSortedHelper(arr, index + 1);
    }

    public static void main(String[] args) {
        IO.println(isSorted(new int[]{1, 2, 3, 4}));
        IO.println(isSorted(new int[]{1, 5, 2, 8}));
        IO.println(isSorted(new int[]{10, 20, 30}));
        IO.println(isSorted(new int[]{3, 3, 3}));
    }
}
