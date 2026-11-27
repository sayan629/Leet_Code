class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int maxFactor = 0;
        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            maxFactor = Math.max(maxFactor, item[0]);
            minPrice = Math.min(minPrice, item[1]);
        }

        int[] factorFreq = new int[maxFactor + 1];
        for (int[] item : items) {
            factorFreq[item[0]]++;
        }

        int[] multiplesCount = new int[maxFactor + 1];
        for (int f = 1; f <= maxFactor; f++) {
            if (factorFreq[f] > 0) {
                int count = 0;
                for (int m = f; m <= maxFactor; m += f) {
                    count += factorFreq[m];
                }
                multiplesCount[f] = count - 1;
            }
        }

        int[] dp = new int[budget + 1];
        for (int[] item : items) {
            int p = item[1];
            int val = 1 + multiplesCount[item[0]];
            if (p <= budget) {
                for (int b = budget; b >= p; b--) {
                    dp[b] = Math.max(dp[b], dp[b - p] + val);
                }
            }
        }

        int maxTotal = 0;
        for (int b = 0; b <= budget; b++) {
            int remBudget = budget - b;
            int extraCopies = remBudget / minPrice;
            maxTotal = Math.max(maxTotal, dp[b] + extraCopies);
        }

        return maxTotal;
    }
}