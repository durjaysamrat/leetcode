class Solution {
    public String mergeAlternately(String word1, String word2) {
        StringBuilder result = new StringBuilder();
        int i = 0, j = 0;
        int n = word1.length(), m = word2.length();

        while (i < n || j < m) {
            if (i < n) {
                result.append(word1.charAt(i));
                i++;
            }
            if (j < m) {
                result.append(word2.charAt(j));
                j++;
            }
        }

        return result.toString();
    }
}
