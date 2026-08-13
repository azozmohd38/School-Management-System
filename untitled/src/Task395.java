public class Task395 {
    public static boolean contains(String str, char target) {
        // Base case: empty string
        if (str.isEmpty()) {
            return false;
        }

        // Early exit if match found
        if (str.charAt(0) == target) {
            return true;
        }

        // Check remainder of the string
        return contains(str.substring(1), target);
    }

    public static void main(String[] args) {
        IO.println(contains("hello", 'e'));
        IO.println(contains("world", 'z'));
        IO.println(contains("java", 'a'));
        IO.println(contains("", 'x'));
    }
}
