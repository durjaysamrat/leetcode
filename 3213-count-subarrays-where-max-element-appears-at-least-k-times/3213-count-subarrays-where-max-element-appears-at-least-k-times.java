class Solution {
    public long countSubarrays(int[] nums, int k) {
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            max = Math.max(max, num); 
        }

        int left = 0;
        int count = 0;
        long result = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == max) {
                count++;  
            }

            while (count >= k) {
                result += nums.length - right;
                if (nums[left] == max) {
                    count--;
                }
                left++;  
            }
        }

        return result;
    }
}
