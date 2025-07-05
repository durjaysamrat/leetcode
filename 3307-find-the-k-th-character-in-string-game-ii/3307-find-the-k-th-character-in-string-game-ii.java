class Solution {
    public char kthCharacter(long k, int[] operations) {
        int n = operations.length;
        long[] lengths = new long[n + 1];
        lengths[0] = 1;

        // Precompute the length of the string after each operation
        for (int i = 0; i < n; i++) {
            if (operations[i] == 0) {
                lengths[i + 1] = lengths[i] * 2;
            } else {
                lengths[i + 1] = lengths[i] * 2;
            }
            // Cap to avoid overflow
            if (lengths[i + 1] > k) {
                lengths[i + 1] = k + 1;
            }
        }

        int shift = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (k > lengths[i]) {
                k -= lengths[i];
                if (operations[i] == 1) {
                    shift++;
                }
            }
        }

        return (char) ('a' + shift % 26);
    }
}
