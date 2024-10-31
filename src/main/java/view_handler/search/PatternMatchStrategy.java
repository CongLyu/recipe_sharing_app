package main.java.view_handler.search;

public interface PatternMatchStrategy {

    /**
     * Matches the text and the pattern.
     * @param text the text needs to be compared
     * @param pattern the pattern needs to be compared
     * @return the index of the pattern starts. If no substring is found, return -1.
     */
    int match(String text, String pattern);
}
