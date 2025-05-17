class Solution {
    public long countSubarrays(int[] nums, long k) {
        long count = 0;
        long currentSum = 0; 
        int start = 0; 
        for (int end = 0; end < nums.length; end++) {
            currentSum += nums[end]; 
            while (currentSum * (end - start + 1) >= k) {
                currentSum -= nums[start]; 
                start++;
            }
            count += (end - start + 1);
        }
        return count;
    }
}