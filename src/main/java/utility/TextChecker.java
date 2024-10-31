package main.java.utility;

public class TextChecker {

    public boolean matchLength(String str, int minLength) {
        return str.length() >= minLength;
    }

    public boolean matchLength(String str, int minLength, int maxLength) {
        return str.length() >= minLength && str.length() <= maxLength;
    }

    /**
     * return true if the string contains only alphabetic and numbers.
     */
    public boolean containOnlyAlphaNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!Character.isAlphabetic(c) && !Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * return true if the string contains only alphabetic and numbers and spaces.
     */
    public boolean containOnlyAlphaNumericSpace(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!Character.isAlphabetic(c) && !Character.isDigit(c)
                    && !Character.isSpaceChar(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * return true if the string contains both upper cases and lower cases.
     */
    public boolean containBothCases(String str) {
        boolean containUpper = false;
        boolean containLower = false;
        for (char c: str.toCharArray()) {
            if (Character.isUpperCase(c)) {
                containUpper = true;
            }
            if (Character.isLowerCase(c)) {
                containLower = true;
            }
            if (containUpper && containLower) {
                break;
            }
        }
        return containUpper && containLower;
    }

    /**
     * return true if the string contains digits.
     */
    public boolean containDigit(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * return true if the string contains only digits.
     */
    public boolean containOnlyDigit(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
