import java.util.*;

class Solution {
    public int closestMeetingNode(int[] edges, int node1, int node2) {
        int n = edges.length;
        int[][] dist = new int[n][2];
        for (int[] row : dist) {
            Arrays.fill(row, -1);
        }
        dist[node1][0] = 0;
        dist[node2][1] = 0;
        
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{node1, 0});
        q.offer(new int[]{node2, 1});
        
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int node = curr[0];
            int type = curr[1];
            
            int neighbor = edges[node];
            if (neighbor != -1 && dist[neighbor][type] == -1) {
                dist[neighbor][type] = dist[node][type] + 1;
                q.offer(new int[]{neighbor, type});
            }
        }
        
        int meetingPoint = -1;
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (dist[i][0] != -1 && dist[i][1] != -1) {
                int currDistance = Math.max(dist[i][0], dist[i][1]);
                if (currDistance < minDistance) {
                    minDistance = currDistance;
                    meetingPoint = i;
                }
            }
        }
        return meetingPoint;
    }
}
