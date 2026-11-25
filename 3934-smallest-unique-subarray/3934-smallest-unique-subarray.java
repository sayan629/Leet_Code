import java.util.HashMap;
import java.util.Map;

class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        int left = 1;
        int right = n;
        int ans = n;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (hasUniqueSubarray(nums, mid)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }

    private boolean hasUniqueSubarray(int[] nums, int len) {
        int n = nums.length;
        long base1 = 1000000007L;
        long mod1 = 1000000009L;
        long base2 = 1000000033L;
        long mod2 = 1000000087L;

        long pow1 = 1;
        long pow2 = 1;
        for (int i = 0; i < len - 1; i++) {
            pow1 = (pow1 * base1) % mod1;
            pow2 = (pow2 * base2) % mod2;
        }

        long hash1 = 0;
        long hash2 = 0;
        long offset = 1000000005L;

        for (int i = 0; i < len; i++) {
            long val = nums[i] + offset;
            hash1 = (hash1 * base1 + val) % mod1;
            hash2 = (hash2 * base2 + val) % mod2;
        }

        Map<Long, Integer> freq = new HashMap<>();
        long key = (hash1 << 32) | hash2;
        freq.put(key, 1);

        for (int i = len; i < n; i++) {
            long outVal = nums[i - len] + offset;
            long inVal = nums[i] + offset;

            hash1 = (hash1 - (outVal * pow1) % mod1 + mod1) % mod1;
            hash1 = (hash1 * base1 + inVal) % mod1;
            hash2 = (hash2 - (outVal * pow2) % mod2 + mod2) % mod2;
            hash2 = (hash2 * base2 + inVal) % mod2;

            key = (hash1 << 32) | hash2;
            freq.put(key, freq.getOrDefault(key, 0) + 1);
        }

        for (int count : freq.values()) {
            if (count == 1) {
                return true;
            }
        }

        return false;
    }
}