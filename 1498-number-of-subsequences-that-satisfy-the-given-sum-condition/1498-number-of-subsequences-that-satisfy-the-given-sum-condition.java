import java.util.Arrays;

class Solution {
    public int numSubseq(int[] nums, int target) {
        int mod = 1_000_000_007;
        int[] powerOfTwo = new int[nums.length];
        powerOfTwo[0] = 1;
        
        for (int i = 1; i < nums.length; i++) {
            powerOfTwo[i] = (2 * powerOfTwo[i - 1]) % mod;
        }

        Arrays.sort(nums);
        int left = 0, right = nums.length - 1, count = 0;

        while (left <= right) {
            if (nums[left] + nums[right] <= target) {
                count = (count + powerOfTwo[right - left]) % mod;
                left++;
            } else {
                right--;
            }
        }

        return count;
    }
}
