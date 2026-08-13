public class Task398 {
    public static int countUpper(String str) {
        // Base case: empty string
        if (str.isEmpty()) {
            return 0;
        }

        int count = Character.isUpperCase(str.charAt(0)) ? 1 : 0;

        return count + countUpper(str.substring(1));
    }

    public static void main(String[] args) {
        IO.println(countUpper("Hello"));       // 1
        IO.println(countUpper("JavaProgram")); // 2
        IO.println(countUpper("ABC"));         // 3
        IO.println(countUpper("lower"));       // 0
    }
}
