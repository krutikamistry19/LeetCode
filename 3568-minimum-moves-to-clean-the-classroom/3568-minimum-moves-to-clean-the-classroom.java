import java.util.*;

class Solution {

    public int minMoves(String[] classroom, int energy) {

        int m = classroom.length;
        int n = classroom[0].length();

        // litterId[i][j] = which bit represents litter at (i,j)
        int[][] litterId = new int[m][n];

        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }

        int startRow = 0;
        int startCol = 0;
        int litterCount = 0;

        // Find S and give every L an ID
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    startRow = i;
                    startCol = j;
                }

                if (ch == 'L') {
                    litterId[i][j] = litterCount;
                    litterCount++;
                }
            }
        }

        // No litter
        if (litterCount == 0) {
            return 0;
        }

        /*
         * If there are k litter cells:
         *
         * allMask = 111...111
         *
         * Example:
         * k = 3
         * allMask = 111 = 7
         */
        int allMask = (1 << litterCount) - 1;

        /*
         * visited[row][col][energy][mask]
         */
        boolean[][][][] visited =
                new boolean[m][n][energy + 1][1 << litterCount];

        /*
         * State:
         * [row, col, remainingEnergy, mask]
         */
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{
                startRow,
                startCol,
                energy,
                allMask
        });

        visited[startRow][startCol][energy][allMask] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        int moves = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            // Process one BFS level
            for (int i = 0; i < size; i++) {

                int[] state = queue.poll();

                int r = state[0];
                int c = state[1];
                int currentEnergy = state[2];
                int mask = state[3];

                // All litter collected
                if (mask == 0) {
                    return moves;
                }

                // If no energy, cannot move
                if (currentEnergy == 0) {
                    continue;
                }

                // Try 4 directions
                for (int d = 0; d < 4; d++) {

                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    // Outside grid
                    if (nr < 0 || nr >= m ||
                        nc < 0 || nc >= n) {
                        continue;
                    }

                    // Wall
                    if (classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }

                    // Moving costs 1 energy
                    int newEnergy = currentEnergy - 1;

                    // If we reach reset area, restore energy
                    if (classroom[nr].charAt(nc) == 'R') {
                        newEnergy = energy;
                    }

                    // Copy current mask
                    int newMask = mask;

                    // Did we reach litter?
                    if (classroom[nr].charAt(nc) == 'L') {

                        int id = litterId[nr][nc];

                        // Remove this litter from mask
                        newMask = newMask & ~(1 << id);
                    }

                    // Have we seen this exact state?
                    if (!visited[nr][nc][newEnergy][newMask]) {

                        visited[nr][nc][newEnergy][newMask] = true;

                        queue.offer(new int[]{
                                nr,
                                nc,
                                newEnergy,
                                newMask
                        });
                    }
                }
            }

            moves++;
        }

        return -1;
    }
}