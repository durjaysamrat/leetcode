import java.util.*;

class Solution {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int[][] result = new int[m - k + 1][n - k + 1];

        for (int i = 0; i <= m - k; i++) {
            for (int j = 0; j <= n - k; j++) {
                Set<Integer> set = new HashSet<>();
                // Collect values in k x k submatrix
                for (int x = i; x < i + k; x++) {
                    for (int y = j; y < j + k; y++) {
                        set.add(grid[x][y]);
                    }
                }

                if (set.size() <= 1) {
                    result[i][j] = 0;
                    continue;
                }

                // Sort the values to find min absolute difference
                List<Integer> sorted = new ArrayList<>(set);
                Collections.sort(sorted);

                int minDiff = Integer.MAX_VALUE;
                for (int z = 1; z < sorted.size(); z++) {
                    minDiff = Math.min(minDiff, Math.abs(sorted.get(z) - sorted.get(z - 1)));
                }

                result[i][j] = minDiff;
            }
        }

        return result;
    }
}
