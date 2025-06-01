class Solution {
    public long distributeCandies(int n, int limit) {
        long total = c2(n);
        long term2 = c2(n - (limit + 1));
        long term3 = c2(n - 2 * (limit + 1));
        long term4 = c2(n - 3 * (limit + 1));
        return total - 3 * term2 + 3 * term3 - term4;
    }
    
    private long c2(int k) {
        if (k < 0) {
            return 0;
        }
        return (long)(k + 1) * (k + 2) / 2;
    }
}