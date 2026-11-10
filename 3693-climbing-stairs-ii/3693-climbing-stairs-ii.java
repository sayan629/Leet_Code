class Solution {
    public int climbStairs(int n, int[] costs) {
        long[] dp = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = Long.MAX_VALUE;
            long stepCost = costs[i - 1]; // costs array is 1-indexed for step i
            
            for (int k = 1; k <= 3; k++) {
                if (i - k >= 0) {
                    long jumpCost = (long) k * k;
                    dp[i] = Math.min(dp[i], dp[i - k] + stepCost + jumpCost);
                }
            }
        }
        return (int) dp[n];
    }
}