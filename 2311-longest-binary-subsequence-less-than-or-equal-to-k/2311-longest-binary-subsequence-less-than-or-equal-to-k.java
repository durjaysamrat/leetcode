class Solution {
    public int longestSubsequence(String s, int k) {
        int n = s.length();
        // Step-1: Keep all set bits until value exceeds k
        long val = 0;
        int i = 0;
        while (i < Math.min(n, 32)) {
            if (s.charAt(n - i - 1) == '1') {
                long power = (long) Math.pow(2, i);
                if (val + power > k) {
                    break;
                }
                val += power;
            }
            i++;
        }
        // Step-2: Count the removed bits
        int removed_count = 0;
        while (i < n) {
            if (s.charAt(n - i - 1) == '1') {
                removed_count++;
            }
            i++;
        }
        return n - removed_count;
    }
}
