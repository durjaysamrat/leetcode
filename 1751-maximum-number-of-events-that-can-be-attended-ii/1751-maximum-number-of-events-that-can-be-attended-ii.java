import java.util.Arrays;

class Solution {
    int n;
    int[][] dp;

    public int maxValue(int[][] events, int k) {
        Arrays.sort(events, (a, b) -> {
            if (a[1] == b[1]) return a[0] - b[0];
            return a[1] - b[1];
        });

        n = events.length;
        dp = new int[n + 1][k + 1];

        for (int[] row : dp)
            Arrays.fill(row, -1);

        return solve(events, 0, -1, k);
    }

    private int solve(int[][] events, int i, int prev, int k) {
        if (k == 0 || i == n)
            return 0;

        if (dp[prev + 1][k] != -1)
            return dp[prev + 1][k];

        int take = 0, skip = 0;
        if (prev == -1 || events[prev][1] < events[i][0]) {
            take = events[i][2] + solve(events, i + 1, i, k - 1);
        }

        skip = solve(events, i + 1, prev, k);

        return dp[prev + 1][k] = Math.max(take, skip);
    }
}
