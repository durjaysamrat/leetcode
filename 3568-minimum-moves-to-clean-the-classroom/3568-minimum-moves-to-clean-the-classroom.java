import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length, n = classroom[0].length();
        char[][] lumetarkon = new char[m][n];
        Map<String, Integer> litterMap = new HashMap<>();
        int litterId = 0, startRow = 0, startCol = 0;

        for (int i = 0; i < m; i++) {
            lumetarkon[i] = classroom[i].toCharArray();
            for (int j = 0; j < n; j++) {
                if (lumetarkon[i][j] == 'S') {
                    startRow = i;
                    startCol = j;
                } else if (lumetarkon[i][j] == 'L') {
                    litterMap.put(i + "," + j, litterId++);
                }
            }
        }

        int allCollected = (1 << litterId) - 1;
        Queue<int[]> queue = new LinkedList<>();
        Map<String, Integer> visited = new HashMap<>();

        queue.offer(new int[]{startRow, startCol, energy, 0, 0}); // row, col, energyLeft, mask, steps
        visited.put(key(startRow, startCol, 0), energy);

        int[][] dirs = {{0,1}, {1,0}, {0,-1}, {-1,0}};

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0], c = cur[1], en = cur[2], mask = cur[3], steps = cur[4];

            if (mask == allCollected) return steps;

            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if (nr < 0 || nc < 0 || nr >= m || nc >= n || lumetarkon[nr][nc] == 'X') continue;

                int newEnergy = en - 1;
                if (newEnergy < 0) continue;

                if (lumetarkon[nr][nc] == 'R') newEnergy = energy;

                int newMask = mask;
                if (lumetarkon[nr][nc] == 'L') {
                    String id = nr + "," + nc;
                    if (litterMap.containsKey(id)) {
                        newMask |= (1 << litterMap.get(id));
                    }
                }

                String stateKey = key(nr, nc, newMask);
                if (!visited.containsKey(stateKey) || visited.get(stateKey) < newEnergy) {
                    visited.put(stateKey, newEnergy);
                    queue.offer(new int[]{nr, nc, newEnergy, newMask, steps + 1});
                }
            }
        }

        return -1;
    }

    private String key(int r, int c, int mask) {
        return r + "," + c + "," + mask;
    }
}
