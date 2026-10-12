class Solution {
    public int maxSum(List<Integer> nums, int k) {
        long MOD = 1_000_000_007;
        int[] bitCount = new int[30];
        for (int num : nums) {
            for (int i = 0; i < 30; i++) {
                if (((num >> i) & 1) == 1) {
                    bitCount[i]++;
                }
            }
        }
        long totalSum = 0;
        for (int j = 0; j < k; j++) {
            long currentNum = 0;
            for (int i = 0; i < 30; i++) {
                if (bitCount[i] > 0) {
                    currentNum |= (1L << i);
                    bitCount[i]--;
                }
            }
            totalSum = (totalSum + (currentNum * currentNum) % MOD) % MOD;
        }
        return (int) totalSum;
    }
}