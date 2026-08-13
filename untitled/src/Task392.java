public class Task392 {
    public static String removeChar(String str, char target) {
        // Base case: empty string
        if (str.isEmpty()) {
            return "";
        }

        char first = str.charAt(0);
        String rest = removeChar(str.substring(1), target);

        // Include character only if it's not the target
        if (first == target) {
            return rest;
        } else {
            return first + rest;
        }
    }

    public static void main(String[] args) {
        IO.println(removeChar("banana", 'a'));
        IO.println(removeChar("hello", 'l'));
        IO.println(removeChar("mississippi", 's'));
       IO.println(removeChar("abc", 'z'));
    }
}