package main.java.text;

public class SearchText {

    public String getSearchStr() {
        return "Search";
    }

    public String getPerformSearchStr() {
        return "Perform Search";
    }

    public String getSearchTypeLabel() {
        return "Object Type: ";
    }

    public String getUserStr() {
        return "User";
    }

    public String getRecipeStr() {
        return "Recipe";
    }

    public String getMessageStr() {
        return "Message";
    }

    public String getSearchAlgorithmLabel() {
        return "Match Algorithm: ";
    }

    public String getBruteForceStr() {
        return "Brute Force";
    }

    public String getRabinKarpStr() {
        return "Rabin Karp";
    }

    public String getBoyerMooreHorspoolStr() {
        return "Boyer-Moore-Horspool";
    }

    public String getKeywordLabel() {
        return "Keyword: ";
    }

    public String getResultTitleStr(int num) {
        return String.format("There are %d results for your search.", num);
    }

    public String getResultStr(int idx, String result) {
        return String.format("Result #%d:\n%s", idx, result);
    }
}
