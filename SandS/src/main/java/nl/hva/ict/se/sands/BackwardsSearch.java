package nl.hva.ict.se.sands;

public class BackwardsSearch {
    /**
     * Returns index of the right most location where <code>needle</code> occurs within <code>haystack</code>. Searching
     * starts at the right end side of the text (<code>haystack</code>) and proceeds to the first character (left side).
     * @param needle the text to search for.
     * @param haystack the text which might contain the <code>needle</code>.
     * @return -1 if the <code>needle</code> is not found and otherwise the left most index of the first
     * character of the <code>needle</code>.
     */
    private int successfulComparisons = 1;
    private int totalComparisons;

    int findLocation(String needle, String haystack) {
        char[] needleChars = needle.toCharArray();
        char[] haystackChars = haystack.toCharArray();
        int startIndex = haystackChars.length - needleChars.length;

        for (int i = startIndex; i >= 0;) {
            int iterationIndex = i;

            for (char needleChar : needleChars) {
                totalComparisons++;
                char haystackChar = haystackChars[iterationIndex];
                if (needleChar == haystackChar) {
                    if (successfulComparisons == needleChars.length) {
                        return i;
                    }

                    successfulComparisons++;
                    iterationIndex++;
                    continue;
                }

                successfulComparisons = 1;

                if (charInPattern(needleChar, haystackChars)) {
                    i--;
                    break;
                }

                i -= needleChars.length;
                break;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        BackwardsSearch backwardsSearch = new BackwardsSearch();
//        String needle = "the";
//        String haystack = "needleinthehaystack";
//
//        System.out.println(needle.length());
//        System.out.println(haystack.length());
//        System.out.println(backwardsSearch.findLocation(needle, haystack));

        String haystack = "abacadabrabracabracadabrabrabracad";
        String needle = "abacadab";

        System.out.println(needle.length());
        System.out.println(haystack.length());
        System.out.println(backwardsSearch.findLocation(needle, haystack));
    }

    boolean charInPattern(char character, char... pattern) {
        for (char c : pattern) {
            if (character == c) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns the number of character compared during the last search.
     * @return the number of character comparisons during the last search.
     */
    int getComparisonsForLastSearch() {
        return totalComparisons;
    }

}
