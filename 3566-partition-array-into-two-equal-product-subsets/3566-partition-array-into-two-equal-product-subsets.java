class Solution {
    public boolean checkEqualPartitions(int[] nums, long target) {
        int n = nums.length;
        int total = 1 << n;

        for (int mask = 1; mask < total - 1; mask++) { 
            long prod1 = 1;
            boolean valid1 = true;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    prod1 *= nums[i];
                    if (prod1 > target) {
                        valid1 = false;
                        break;
                    }
                }
            }

            if (!valid1 || prod1 != target) continue;

            // now check complement
            long prod2 = 1;
            boolean valid2 = true;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) == 0) {
                    prod2 *= nums[i];
                    if (prod2 > target) {
                        valid2 = false;
                        break;
                    }
                }
            }

            if (valid2 && prod2 == target) return true;
        }

        return false;
    }
}
