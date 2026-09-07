package utils;

public final class HelperUtils {

    private static int counter = 1;

    private HelperUtils() {
    }

    public static boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean isEmpty(Object[] values) {
        return values == null || values.length == 0;
    }

    public static boolean isBlank(String value) {
        return isEmpty(value);
    }

    public static boolean isValidText(String value) {
        return !isEmpty(value);
    }

    public static boolean isValidText(String value, int minLength) {
        return isValidText(value) && value.trim().length() >= minLength;
    }

    public static boolean isValidText(String value, int minLength, int maxLength) {
        return isValidText(value, minLength) && value.trim().length() <= maxLength;
    }

    public static String generateId() {
        return String.valueOf(counter++);
    }

    public static String generateId(String prefix) {
        return prefix + counter++;
    }

    public static boolean isPositive(int value) {
        return value > 0;
    }

    public static boolean isPositive(double value) {
        return value > 0;
    }

    public static boolean isInRange(int value, int min, int max) {
        return value >= min && value <= max;
    }

    public static boolean isInRange(double value, double min, double max) {
        return value >= min && value <= max;
    }

    public static boolean isValidAge(int age) {
        return isInRange(age, 0, 120);
    }

    public static boolean isValidPhone(String phone) {
        return phone != null && phone.trim().length() >= 8 && phone.trim().length() <= 15;
    }

    public static boolean isOneOf(String value, String[] allowed) {
        if (isEmpty(value) || allowed == null) {
            return false;
        }

        for (String item : allowed) {
            if (item != null && item.equalsIgnoreCase(value.trim())) {
                return true;
            }
        }

        return false;
    }
}
