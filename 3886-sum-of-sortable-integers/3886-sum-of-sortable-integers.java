class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;

        int[] prefixMax = new int[n];
        prefixMax[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefixMax[i] = Math.max(prefixMax[i - 1], nums[i]);
        }

        int[] suffixMin = new int[n];
        suffixMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(suffixMin[i + 1], nums[i]);
        }

        int[] dropPrefix = new int[n];
        for (int i = 0; i < n - 1; i++) {
            dropPrefix[i + 1] = dropPrefix[i] + (nums[i] > nums[i + 1] ? 1 : 0);
        }

        int totalSum = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) continue;

            boolean isPossible = true;

            for (int b = 0; b < n; b += k) {
                if (b > 0 && prefixMax[b - 1] > suffixMin[b]) {
                    isPossible = false;
                    break;
                }

                int L = b;
                int R = b + k - 1;
                int internalDrops = dropPrefix[R] - dropPrefix[L];
                int circularDrop = (nums[R] > nums[L]) ? 1 : 0;

                if (internalDrops + circularDrop > 1) {
                    isPossible = false;
                    break;
                }
            }

            if (isPossible) {
                totalSum += k;
            }
        }

        return totalSum;
    }
}