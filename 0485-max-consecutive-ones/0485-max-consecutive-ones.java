class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int count = 0;
        int max_Count=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==1){
                count++;
                max_Count = Math.max(count,max_Count);
            }
            else{
                count = 0;
            }
        }
        return max_Count;
    }
}