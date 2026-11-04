import java.util.Arrays;
import java.util.TreeMap;

class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] minOps = getMinOps(nums, x);

        long[][] dp = new long[n + 1][k + 1];
        long INF = (long) 1e16;

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
            dp[i][0] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = dp[i - 1][j];

                if (i >= x && dp[i - x][j - 1] != INF) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - x][j - 1] + minOps[i - x]);
                }
            }
        }

        return dp[n][k];
    }

    private long[] getMinOps(int[] nums, int x) {
        int n = nums.length;
        long[] minOps = new long[n - x + 1];

        TreeMap<Integer, Integer> lower = new TreeMap<>();
        TreeMap<Integer, Integer> upper = new TreeMap<>();
        long lowerSum = 0, upperSum = 0;
        int lowerCount = 0, upperCount = 0;

        for (int i = 0; i < n; i++) {
            if (lower.isEmpty() || nums[i] <= lower.lastKey()) {
                lower.put(nums[i], lower.getOrDefault(nums[i], 0) + 1);
                lowerSum += nums[i];
                lowerCount++;
            } else {
                upper.put(nums[i], upper.getOrDefault(nums[i], 0) + 1);
                upperSum += nums[i];
                upperCount++;
            }

            if (i >= x) {
                int outNum = nums[i - x];
                if (lower.containsKey(outNum)) {
                    removeOne(lower, outNum);
                    lowerSum -= outNum;
                    lowerCount--;
                } else {
                    removeOne(upper, outNum);
                    upperSum -= outNum;
                    upperCount--;
                }
            }

            while (lowerCount < upperCount) {
                int val = upper.firstKey();
                removeOne(upper, val);
                upperSum -= val;
                upperCount--;

                lower.put(val, lower.getOrDefault(val, 0) + 1);
                lowerSum += val;
                lowerCount++;
            }

            while (lowerCount > upperCount + 1) {
                int val = lower.lastKey();
                removeOne(lower, val);
                lowerSum -= val;
                lowerCount--;

                upper.put(val, upper.getOrDefault(val, 0) + 1);
                upperSum += val;
                upperCount++;
            }

            if (i >= x - 1) {
                long median = lower.lastKey();
                long cost = (median * lowerCount - lowerSum) + (upperSum - median * upperCount);
                minOps[i - x + 1] = cost;
            }
        }

        return minOps;
    }

    private void removeOne(TreeMap<Integer, Integer> map, int key) {
        int count = map.get(key);
        if (count == 1) {
            map.remove(key);
        } else {
            map.put(key, count - 1);
        }
    }
}