class Solution {
    public boolean canJump(int[] nums) {
        int finalpos = nums.length-1;
        for(int idx = nums.length-2;idx>=0;idx--){
            if(idx+nums[idx]>=finalpos)
            {
                finalpos = idx;
            }
        }
        if(finalpos == 0){
            return true;
        }
        else{
            return false;
        }
    }
}