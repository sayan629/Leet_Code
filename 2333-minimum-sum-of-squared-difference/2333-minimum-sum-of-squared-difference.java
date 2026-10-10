class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = (long) k1 + k2;
        
        int maxDiff = 0;
        int[] freq = new int[100001];
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            freq[diff]++;
            maxDiff = Math.max(maxDiff, diff);
        }
        for (int d = maxDiff; d > 0 && k > 0; d--) {
            if (freq[d] == 0) {
                continue;
            }
            
            long take = Math.min(k, (long) freq[d]);
            freq[d] -= take;
            freq[d - 1] += take;
            k -= take;
        }
        long ans = 0;
        for (int d = 1; d <= maxDiff; d++) {
            if (freq[d] > 0) {
                ans += (long) freq[d] * d * d;
            }
        }
        
        return ans;
    }
}