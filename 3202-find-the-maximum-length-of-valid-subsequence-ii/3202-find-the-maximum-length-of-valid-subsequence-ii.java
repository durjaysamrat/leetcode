class Solution {
    public int maximumLength(int[] nums, int k) {
        int n = nums.length;
        int result = 1;

        for (int mod = 0; mod < k; mod++) {
            int[] dp = new int[k];
            for (int num : nums) {
                int rem = num % k;
                int prevRem = (mod - rem + k) % k;
                dp[rem] = Math.max(dp[rem], dp[prevRem] + 1);
                result = Math.max(result, dp[rem]);
            }
        }

        return result;
    }
}
