import java.util.LinkedList;
import java.util.Queue;

class Solution {
    private int countSubsequences(String s, String next) {
        int i = 0; // Index in s
        int j = 0; // Index in next
        int m = next.length();
        int subsequenceCount = 0;

        while (i < s.length()) {
            if (s.charAt(i) == next.charAt(j)) {
                j++;
                if (j == m) {
                    j = 0;
                    subsequenceCount++;
                }
            }
            i++;
        }
        return subsequenceCount;
    }

    public String longestSubsequenceRepeatedK(String s, int k) {
        int n = s.length();
        int[] freq = new int[26];
        for (int i = 0; i < n; ++i) {
            freq[s.charAt(i) - 'a']++;
        }

        // BFS
        String curr = "";
        Queue<String> queue = new LinkedList<>();
        queue.offer(curr);
        String res = "";

        while (!queue.isEmpty()) {
            curr = queue.poll();
            
            for (char c = 'a'; c <= 'z'; ++c) {
                if (freq[c - 'a'] < k) {
                    continue;
                }
                String next = curr + c;
                if (countSubsequences(s, next) >= k) {
                    res = next;
                    queue.offer(next);
                }
            }
        }
        return res;
    }
}
