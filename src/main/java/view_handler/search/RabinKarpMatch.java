package main.java.view_handler.search;

import java.math.BigInteger;
import java.util.Random;

/**
 * A way to match the text and the pattern.
 * reference: https://www.baeldung.com/java-full-text-search-algorithms
 */

public class RabinKarpMatch implements PatternMatchStrategy {

    /**
     * Matches the hash value of the pattern with the hash value of current substring of textand if the hash values
     * match then only it starts matching individual characters.
     * @param text the text needs to be compared
     * @param pattern the pattern needs to be compared
     * @return the index of the pattern starts. If no substring is found, return -1
     */
    @Override
    public int match(String text, String pattern) {
        int textLength = text.length();
        int patternLength = pattern.length();
        long prime = getBiggerPrime(patternLength);
        long r = 1;
        for (int i = 0; i < patternLength - 1; i++) {
            r *= 2;
            r = r % prime;
        }
        long[] t = new long[textLength];
        t[0] = 0;
        long pfinger = 0;
        for (int j = 0; j < patternLength; j++) {
            t[0] = (2 * t[0] + text.charAt(j)) % prime;
            pfinger = (2 * pfinger + pattern.charAt(j)) % prime;
        }
        int diff = textLength - patternLength;
        for (int i = 0; i <= diff; i++) {
            if (t[i] == pfinger) {
                boolean passed = true;
                for (int k = 0; k < patternLength; k++) {
                    if (text.charAt(i + k) != pattern.charAt(k)) {
                        passed = false;
                        break;
                    }
                }
                if (passed) {
                    return i;
                }
            }
            if (i < diff) {
                long value = 2 * (t[i] - r * text.charAt(i)) + text.charAt(i + patternLength);
                t[i + 1] = ((value % prime) + prime) % prime;
            }
        }
        return -1;
    }

    private long getBiggerPrime(int m) {
        BigInteger prime = BigInteger.probablePrime(getNumberOfBits(m) + 1, new Random());
        return prime.longValue();
    }

    private int getNumberOfBits(int number) {
        return Integer.SIZE - Integer.numberOfLeadingZeros(number);
    }
}
