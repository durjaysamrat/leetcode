import java.util.*;

class Solution {
    public int[] maxSubsequence(int[] nums, int k) {
        int n = nums.length;
        List<int[]> vals = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            vals.add(new int[]{i, nums[i]});
        }

        vals.sort((a, b) -> Integer.compare(b[1], a[1]));

        List<int[]> topK = vals.subList(0, k);

        topK.sort(Comparator.comparingInt(a -> a[0]));

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = topK.get(i)[1];
        }

        return res;
    }
}
