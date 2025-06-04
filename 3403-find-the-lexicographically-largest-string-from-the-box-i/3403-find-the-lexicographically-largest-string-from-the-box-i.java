import java.util.*;

class Solution {
    public String answerString(String word, int numFriends) {
        int n = word.length(), i = 0, j = 1;
        if (numFriends == 1) return word;
        while (j < n) {
            while (j < n && word.charAt(i) > word.charAt(j)) j++;
            if (j < n && word.charAt(i) < word.charAt(j)) i = j;
            else {
                int k = 0;
                while (j + k < n && word.charAt(i + k) == word.charAt(j + k)) k++;
                if (j + k < n && word.charAt(i + k) < word.charAt(j + k)) i = j;
            }
            j++;
        }
        return word.substring(i, Math.min(n - i, n - numFriends + 1) + i);
    }
}

