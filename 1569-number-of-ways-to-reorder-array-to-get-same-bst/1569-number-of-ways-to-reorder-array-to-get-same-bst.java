import java.util.ArrayList;
import java.util.List;

class Solution {
    private static final int MOD = 1_000_000_007;
    private long[][] pascal;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        pascal = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            pascal[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                pascal[i][j] = (pascal[i - 1][j - 1] + pascal[i - 1][j]) % MOD;
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        return (int) ((countWays(list) - 1 + MOD) % MOD);
    }

    private long countWays(List<Integer> arr) {
        if (arr.size() <= 2) {
            return 1;
        }

        int root = arr.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (int i = 1; i < arr.size(); i++) {
            int val = arr.get(i);
            if (val < root) {
                left.add(val);
            } else {
                right.add(val);
            }
        }

        int L = left.size();
        int R = right.size();

        long combinations = pascal[L + R][L];
        long leftWays = countWays(left);
        long rightWays = countWays(right);

        return (((combinations * leftWays) % MOD) * rightWays) % MOD;
    }
}