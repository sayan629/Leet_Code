import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int startR = -1, startC = -1;
        List<int[]> litters = new ArrayList<>();

        // Locate 'S' and all 'L' positions
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                char ch = classroom[r].charAt(c);
                if (ch == 'S') {
                    startR = r;
                    startC = c;
                } else if (ch == 'L') {
                    litters.add(new int[]{r, c});
                }
            }
        }

        int totalLitters = litters.size();
        if (totalLitters == 0) return 0;
        int allCollectedMask = (1 << totalLitters) - 1;

        // Check if starting position is already on a litter item
        int startMask = 0;
        for (int k = 0; k < totalLitters; k++) {
            if (litters.get(k)[0] == startR && litters.get(k)[1] == startC) {
                startMask |= (1 << k);
            }
        }

        // Standard BFS Queue storing [row, col, mask, current_energy, moves]
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startR, startC, startMask, energy, 0});

        // Visited array: [row][col][bitmask][energy_level]
        boolean[][][][] visited = new boolean[m][n][1 << totalLitters][energy + 1];
        visited[startR][startC][startMask][energy] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            int mask = curr[2];
            int e = curr[3];
            int moves = curr[4];

            // If all litters are collected, return total moves
            if (mask == allCollectedMask) {
                return moves;
            }

            // Stop exploring from this path if out of energy
            if (e == 0) continue;

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                // Check grid boundaries and obstacles
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                char cell = classroom[nr].charAt(nc);
                if (cell == 'X') continue;

                int nextEnergy = e - 1;
                int nextMask = mask;

                // Restore energy on 'R'
                if (cell == 'R') {
                    nextEnergy = energy;
                }

                // Pick up litter on 'L'
                if (cell == 'L') {
                    for (int k = 0; k < totalLitters; k++) {
                        if (litters.get(k)[0] == nr && litters.get(k)[1] == nc) {
                            nextMask |= (1 << k);
                            break;
                        }
                    }
                }

                // Standard visited check
                if (!visited[nr][nc][nextMask][nextEnergy]) {
                    visited[nr][nc][nextMask][nextEnergy] = true;
                    queue.offer(new int[]{nr, nc, nextMask, nextEnergy, moves + 1});
                }
            }
        }

        return -1; // Unreachable
    }
}