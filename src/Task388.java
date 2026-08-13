public class Task388 {
    public static void printReverse(String str) {

        if (str.isEmpty()) {
            return;
        }


        printReverse(str.substring(1));


        System.out.println(str.charAt(0));
    }

    public static void main(String[] args) {
        printReverse("cat");
        IO.println("---");
        printReverse("hello");
    }
}
