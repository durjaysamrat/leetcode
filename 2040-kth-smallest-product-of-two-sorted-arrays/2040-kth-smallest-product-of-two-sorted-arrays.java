import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution {
    /**
     * Finds the k-th smallest product of two sorted integer arrays.
     *
     * @param nums1 The first sorted integer array.
     * @param nums2 The second sorted integer array.
     * @param k The k-th smallest product to find.
     * @return The k-th smallest product.
     */
    public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        // The product can range from a very small negative number to a very large positive number.
        // Given constraints up to 10^5, max product is 10^5 * 10^5 = 10^10, min product is -10^5 * 10^5 = -10^10.
        long low = -10_000_000_000L; // -10^10
        long high = 10_000_000_000L;   // 10^10
        long ans = high; // Initialize answer to a large value, as we're looking for the smallest valid mid

        // Binary search for the k-th smallest product value.
        while (low <= high) {
            long mid = low + (high - low) / 2; // Calculate mid-point to avoid overflow
            long count = 0; // Stores the number of products less than or equal to 'mid'

            // For each number in nums1, count how many numbers in nums2 form a product <= mid
            for (int num1 : nums1) {
                count += countPairs(num1, nums2, mid);
            }

            // Adjust the binary search range based on the total count:
            if (count >= k) {
                // If count is >= k, 'mid' could be our answer, or the answer is smaller.
                ans = mid;
                high = mid - 1; // Try to find a smaller product
            } else {
                // If count < k, 'mid' is too small; the answer must be larger.
                low = mid + 1; // Look in the upper half
            }
        }
        return ans;
    }

    /**
     * Helper function to count pairs (val, arr[i]) such that val * arr[i] <= target.
     * This function uses binary search efficiently.
     *
     * @param val The single integer from the first array (nums1).
     * @param arr The second sorted integer array (nums2).
     * @param target The target product value (mid from the main binary search).
     * @return The count of products (val * arr[i]) that are less than or equal to target.
     */
    private long countPairs(int val, int[] arr, long target) {
        if (arr.length == 0) {
            return 0;
        }

        // Special case: if val is 0, the product will always be 0.
        if (val == 0) {
            return target >= 0 ? arr.length : 0; // If target is non-negative, all products (0) are <= target. Otherwise, none.
        }

        long count = 0;
        int low = 0, high = arr.length - 1;

        if (val > 0) {
            // Case: val is positive.
            // We are looking for arr[i] such that val * arr[i] <= target.
            // This implies arr[i] <= target / val.
            // Since arr is sorted ascending, we need to find the rightmost 'i' that satisfies this.
            int r = -1; // Stores the index of the largest element in 'arr' that satisfies the condition
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if ((long) val * arr[mid] <= target) {
                    r = mid;
                    low = mid + 1; // Try to find a larger element
                } else {
                    high = mid - 1; // Current arr[mid] is too large, look left
                }
            }
            count = r + 1; // All elements from index 0 to r (inclusive) satisfy the condition
        } else { // val < 0
            // Case: val is negative.
            // We are looking for arr[i] such that val * arr[i] <= target.
            // Since val is negative, when we divide by val, the inequality flips: arr[i] >= target / val.
            // Since arr is sorted ascending, we need to find the leftmost 'i' that satisfies this.
            int l = arr.length; // Stores the index of the smallest element in 'arr' that satisfies the condition
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if ((long) val * arr[mid] <= target) {
                    l = mid;
                    high = mid - 1; // Try to find a smaller element
                } else {
                    low = mid + 1; // Current arr[mid] is too small, look right
                }
            }
            count = arr.length - l; // All elements from index l to arr.length-1 (inclusive) satisfy the condition
        }
        return count;
    }
}