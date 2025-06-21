import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int minimumDeletions(String word, int k) {
        int n = word.length();
        Map<Character, Integer> freq = new HashMap<>();

        // Count frequency of each character
        for (char c : word.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        int ans = n;

        // Iterate over each character frequency as the minimum reference
        for (Map.Entry<Character, Integer> entry1 : freq.entrySet()) {
            int f1 = entry1.getValue();
            int del = 0;

            for (Map.Entry<Character, Integer> entry2 : freq.entrySet()) {
                int f2 = entry2.getValue();
                int diff = f2 - f1;

                if (f1 > f2) {
                    del += f2;
                } else if (diff > k) {
                    del += (f2 - f1 - k);
                }
            }

            ans = Math.min(ans, del);
        }

        return ans;
    }
}
