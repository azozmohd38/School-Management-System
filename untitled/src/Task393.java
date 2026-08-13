public class Task393 {
    public static int power(int b, int n) {
        // Base case: any number to power 0 is 1
        if (n == 0) {
            return 1;
        }

        int halfPower = power(b, n / 2);

        // If even exponent
        if (n % 2 == 0) {
            return halfPower * halfPower;
        } else { // If odd exponent
            return b * halfPower * halfPower;
        }
    }

    public static void main(String[] args) {
        IO.println(power(2, 10));
        IO.println(power(3, 4));
        IO.println(power(5, 3));
        IO.println(power(2, 0));  
    }
}