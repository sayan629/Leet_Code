import java.util.Arrays;

class Solution {
    private static final int MOD = 1_000_000_007;

    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if (m == 1 && n == 1) {
            return 1;
        }
        int[][][] memo = new int[m][n][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }
        long totalPaths = (solve(0, 1, 0, grid, memo) + solve(1, 0, 1, grid, memo)) % MOD;
        return (int) totalPaths;
    }

    private int solve(int r, int c, int dir, int[][] grid, int[][][] memo) {
        int m = grid.length;
        int n = grid[0].length;
        if (r >= m || c >= n) {
            return 0;
        }
        if (r == m - 1 && c == n - 1) {
            return 1;
        }

        if (memo[r][c][dir] != -1) {
            return memo[r][c][dir];
        }

        long paths = 0;
        if (grid[r][c] == 1) {
            if (dir == 0) {
                paths = solve(r + 1, c, 1, grid, memo);
            } else {
                paths = solve(r, c + 1, 0, grid, memo);
            }
        } else {
            paths = (solve(r, c + 1, 0, grid, memo) + solve(r + 1, c, 1, grid, memo)) % MOD;
        }

        return memo[r][c][dir] = (int) paths;
    }
}