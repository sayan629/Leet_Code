class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int result = nums[0]+nums[1]+nums[2];
        int min_diff = Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            int left = i+1;
            int right = n-1;
            while(left<right){
                int sum = nums[i]+nums[left]+nums[right];

                if(sum==target) return target;

                if(sum<target) 
                    left++;
                else
                    right--;

                int diff = Math.abs(sum-target);
                if(diff<min_diff){
                    result = sum;
                    min_diff = diff;
                }
            }
        }
        return result;
    }
}