class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int MAX = n * n;
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        boolean[] visited = new boolean[MAX + 1];
        visited[1] = true;
        
        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int curr = q.poll();
                
                if (curr == MAX)
                    return level;
                
                for (int next = curr + 1; next <= Math.min(curr + 6, MAX); next++) {
                    int dest = next;
                    // Calculate board position
                    int row = (next - 1) / n;
                    int col = (next - 1) % n;
                    if (row % 2 == 1) // Odd rows are right-to-left
                        col = n - 1 - col;
                    
                    row = n - 1 - row; // Convert to board coordinates
                    
                    if (board[row][col] != -1)
                        dest = board[row][col];
                    
                    if (!visited[dest]) {
                        visited[dest] = true;
                        q.offer(dest);
                    }
                }
            }
            level++;
        }
        return -1;
    }
}
