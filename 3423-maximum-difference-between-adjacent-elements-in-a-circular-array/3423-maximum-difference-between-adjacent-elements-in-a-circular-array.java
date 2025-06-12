class Solution {
    public int maxAdjacentDistance(int[] nums) {
        int n = nums.length;
        int maxDiff = 0;

        for (int i = 0; i < n; i++) {
            int next = (i + 1) % n; // wrap around for circular array
            int diff = Math.abs(nums[i] - nums[next]);
            maxDiff = Math.max(maxDiff, diff);
        }

        return maxDiff;
    }
}
