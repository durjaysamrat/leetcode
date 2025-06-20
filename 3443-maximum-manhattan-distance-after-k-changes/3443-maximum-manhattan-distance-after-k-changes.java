import java.util.HashMap;

public class Solution {
    public int maxDistance(String s, int k) {
        HashMap<Character, Integer> freq = new HashMap<>();
        int maxDist = 0;

        for (char c : s.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);

            int vertical = Math.abs(freq.getOrDefault('N', 0) - freq.getOrDefault('S', 0));
            int horizontal = Math.abs(freq.getOrDefault('E', 0) - freq.getOrDefault('W', 0));
            int currDist = vertical + horizontal;

            int northSouthChanges = Math.min(k, Math.min(freq.getOrDefault('E', 0), freq.getOrDefault('W', 0)));
            int remainingK = k - northSouthChanges;
            int eastWestChanges = Math.min(remainingK, Math.min(freq.getOrDefault('N', 0), freq.getOrDefault('S', 0)));

            maxDist = Math.max(maxDist, currDist + 2 * (eastWestChanges + northSouthChanges));
        }

        return maxDist;
    }
}