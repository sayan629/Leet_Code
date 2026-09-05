class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;

        // Suffix minimum
        int[] rightMin = new int[n];
        rightMin[n - 1] = nums[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            rightMin[i] = Math.min(nums[i], rightMin[i + 1]);
        }

        // Prefix maximum
        int maxLeft = nums[0];

        for (int i = 0; i < n; i++) {
            maxLeft = Math.max(maxLeft, nums[i]);

            int score = maxLeft - rightMin[i];

            if (score <= k) {
                return i;
            }
        }

        return -1;
    }
}