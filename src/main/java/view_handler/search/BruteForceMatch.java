package main.java.view_handler.search;

/**
 * A way to match the text and the pattern.
 */
public class BruteForceMatch implements PatternMatchStrategy {

    /**
     * Compares the pattern with the text for each possible shift of P relative to T, until either a match is found,
     * or all placements of the pattern have been tried.
     * @param text the text needs to be compared
     * @param pattern the pattern needs to be compared
     * @return the index of the pattern starts. If no substring is found, return -1
     */
    @Override
    public int match(String text, String pattern) {
        int textLength = text.length();
        int patternLength = pattern.length();
        int i = 0;
        while ((i + patternLength) <= textLength) {
            int j = 0;
            while (text.charAt(i + j) == pattern.charAt(j)) {
                j ++;
                if (j >= patternLength) {
                    return i;
                }
            }
            i ++;
        }
        return -1;
    }
}
