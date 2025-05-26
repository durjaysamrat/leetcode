class Solution {
    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();
        List<List<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[n];

        // Build graph
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            indegree[edge[1]]++;
        }

        // DP table to store color frequency up to each node
        int[][] dp = new int[n][26];
        Queue<Integer> queue = new LinkedList<>();

        // Initialize queue with nodes having zero indegree
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) queue.offer(i);
            dp[i][colors.charAt(i) - 'a'] = 1;
        }

        int visited = 0;
        int max = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            visited++;

            for (int neighbor : graph.get(node)) {
                // Update dp for each color
                for (int c = 0; c < 26; c++) {
                    int val = dp[node][c] + (colors.charAt(neighbor) - 'a' == c ? 1 : 0);
                    dp[neighbor][c] = Math.max(dp[neighbor][c], val);
                }

                // Decrease indegree and add to queue if it becomes 0
                if (--indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }

            // Update global max
            for (int c = 0; c < 26; c++) {
                max = Math.max(max, dp[node][c]);
            }
        }

        return visited == n ? max : -1; // Cycle check
    }
}