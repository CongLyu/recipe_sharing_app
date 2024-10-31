package main.java.view_handler.search;

/**
 * A way to match the text and the pattern.
 * reference: https://wsww.baeldung.com/java-full-text-search-algorithms
 */
public class BoyerMooreHorspoolMatch implements PatternMatchStrategy {

    /**
     * Match the text to pattern from right to left
     * @param text  text needs to be compared
     * @param pattern pattern need to be compared
     * @return the index of the pattern starts. If no substring is found, return -1
     */
    @Override
    public int match(String text, String pattern) {
        int textLength = text.length();
        int patternLength = pattern.length();
        int[] shift = new int[256];
        for (int k = 0; k < 256; k++) {
            shift[k] = patternLength;
        }
        for (int k = 0; k < patternLength - 1; k++){
            shift[pattern.charAt(k)] = patternLength - 1 - k;
        }
        int i = 0;
        int j;
        while ((i + patternLength) <= textLength) {
            j = patternLength - 1;
            while (text.charAt(i + j) == pattern.charAt(j)) {
                j -= 1;
                if (j < 0)
                    return i;
            }
            i = i + shift[text.charAt(i + patternLength - 1)];
        }
        return -1;
    }
}
