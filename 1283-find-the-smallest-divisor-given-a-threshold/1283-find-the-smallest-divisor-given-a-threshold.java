class Solution {
    private int SumbyD(int [] nums , int div){
        int n = nums.length;
        int sum = 0;
        for (int i=0;i<n;i++){
            sum += Math.ceil((double)nums[i]/div);
        }
        return sum;
    }
    public int smallestDivisor(int[] nums, int threshold) {
        int low=1,high = nums[0];
        for(int num:nums){
            high = Math.max(high,num);
        }
        while(low<=high){
            int mid = (low+high)/2;

            if(SumbyD(nums,mid)<=threshold){
                high = mid-1;
            }
            else{
                low = mid+1;
            }
        }
        return low;
    }
}