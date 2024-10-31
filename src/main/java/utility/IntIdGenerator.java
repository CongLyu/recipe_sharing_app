package main.java.utility;

public class IntIdGenerator {

    private Integer count = 0;

    /**
     * return an unique integer to help recipe entity generate an unique id.
     */
    public Integer generate() {
        return ++count;
    }
}
