import java.util.*;

class Solution {
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        Set<Integer> result = new HashSet<>();

        // First, find all indices j where nums[j] == key
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] == key) {
                // For each such j, mark all i such that |i - j| <= k
                int start = Math.max(0, j - k);
                int end = Math.min(nums.length - 1, j + k);
                for (int i = start; i <= end; i++) {
                    result.add(i);
                }
            }
        }

        // Convert set to list and sort the result
        List<Integer> sorted = new ArrayList<>(result);
        Collections.sort(sorted);
        return sorted;
    }
}
