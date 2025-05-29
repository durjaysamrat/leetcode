import java.util.*;

class Solution {
    static class Pair {
        int first;
        int second;
        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    // A single BFS that:
    // 1) returns the number of nodes on even-numbered levels, and
    // 2) if `included` is non-null, marks exactly those nodes at even levels
    private int bfs(int start, List<List<Integer>> adj, boolean[] included) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(start, -1));
        int count = 0;
        int level = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            // on even levels we both add to count and (optionally) record the nodes
            if (level % 2 == 0)
                count += size;

            while (size-- > 0) {
                Pair currPair = q.poll();
                int curr = currPair.first;
                int parent = currPair.second;
                if (included != null && level % 2 == 0) {
                    // mark this node as "included"
                    included[curr] = true;
                }
                for (int v : adj.get(curr)) {
                    if (v == parent) continue;
                    q.add(new Pair(v, curr));
                }
            }
            ++level;
        }
        return count;
    }

    public int[] maxTargetNodes(int[][] edges1, int[][] edges2) {
        // build adjacency lists
        int n1 = edges1.length + 1;
        int n2 = edges2.length + 1;
        List<List<Integer>> adj1 = new ArrayList<>();
        List<List<Integer>> adj2 = new ArrayList<>();

        for (int i = 0; i <= n1; i++) {
            adj1.add(new ArrayList<>());
        }
        for (int i = 0; i <= n2; i++) {
            adj2.add(new ArrayList<>());
        }

        for (int[] edge : edges1) {
            adj1.get(edge[0]).add(edge[1]);
            adj1.get(edge[1]).add(edge[0]);
        }
        for (int[] edge : edges2) {
            adj2.get(edge[0]).add(edge[1]);
            adj2.get(edge[1]).add(edge[0]);
        }

        // Step-1: Find the best you can do by adding the extra edge in tree-2
        int even_count2 = bfs(0, adj2, null);            // count of even-level nodes
        int odd_count2 = n2 - even_count2;                // the rest are odd-level
        int best2 = Math.max(even_count2, odd_count2);

        // Step-2: Run BFS on tree-1, and record nodes on even levels
        boolean[] included = new boolean[n1];
        int even_count1 = bfs(0, adj1, included);

        // Step-3: For each i, if i was even-level in tree-1 we connect it to tree-2's best even-level
        int[] ans = new int[n1];
        for (int i = 0; i < n1; ++i) {
            if (included[i]) {
                // it contributes even_count1 from tree-1 plus best2 from tree-2
                ans[i] = even_count1 + best2;
            } else {
                // it would be odd in tree-1, so you get (n1-even_count1) + best2
                ans[i] = (n1 - even_count1) + best2;
            }
        }
        return ans;
    }
}