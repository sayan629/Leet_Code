class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int window = Integer.MAX_VALUE;
        int currentSum = 0;
        int high = 0;
        int low = 0;
        for( high = 0; high< nums.length; high++){
            currentSum = currentSum + nums[high];
            

            while(currentSum>=target){
                int currentWindowSize = high-low+1;
                window = Math.min(window, currentWindowSize);
                currentSum = currentSum - nums[low];
                low ++;
            }
        }
        return window == Integer.MAX_VALUE?0:window;
    }
}