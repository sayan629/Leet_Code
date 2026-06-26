class Solution {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int [] result = new int[n];
        for(int i =0 ;i<n;i++){
            nums[i] = nums[i] * nums[i];
        }

        int head = 0;
        int tail = n-1;

        for(int pos=n-1; pos>=0;pos--){
            if(nums[head] >= nums[tail]){
                result[pos] = nums[head];
                head++;
            }
            else{
                result[pos] = nums[tail];
                tail--;
            }
        }
        return result;
    }
}