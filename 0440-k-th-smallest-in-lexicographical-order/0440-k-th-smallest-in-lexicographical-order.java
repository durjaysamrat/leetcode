public class Solution {
    public long countPrefix(long prefix, long n) {
        long count = 0, curr = prefix, next = prefix + 1;
        while (curr <= n) {
            count += Math.min(n + 1, next) - curr;
            curr *= 10;
            next *= 10;
        }
        return count;
    }

    public int findKthNumber(int n, int k) {
        int prefix = 1;
        k = k - 1;
        while (k > 0) {
            long cnt = countPrefix(prefix, n);
            if (cnt <= k) {
                prefix++;
                k -= cnt;
            } else {
                prefix *= 10;
                k--;
            }
        }
        return prefix;
    }
}