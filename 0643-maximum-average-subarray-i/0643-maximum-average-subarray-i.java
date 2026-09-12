class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int n = nums.length;
        double ans =0;
        double win = 0;
        for(int i =0;i<k;i++){
            win+=nums[i];
        }
        ans = win/k;
        for(int right =k;right<nums.length;right++){
            win += nums[right]-nums[right - k];
            ans = Math.max(ans, win/k);
        }
        return ans;
    }
}