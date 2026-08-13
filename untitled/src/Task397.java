public class Task397 {
    public static String toBinary(int n) {
        // Base cases: 0 or 1
        if (n == 0) return "0";
        if (n == 1) return "1";


        return toBinary(n / 2) + (n % 2);
    }

    public static void main(String[] args) {
        IO.println(toBinary(2));
        IO.println(toBinary(5));
        IO.println(toBinary(10));
        IO.println(toBinary(1));
    }
}
