class Solution {
    public int longestMountain(int[] arr) {
        int n = arr.length;
        int ans =0;
        for( int i=1;i<n-1;i++){
            // check peek or find mountain
            if(arr[i]>arr[i-1] && arr[i]>arr[i+1]){
                int count =1;
                int j =i;

                // check left side
                while(j>0 && arr[j]>arr[j-1]){
                    j--;
                    count++;
                }
                // check right side
                j=i;
                while(j<n-1 && arr[j]>arr[j+1]){
                    j++;
                    count++;
                }
                ans = Math.max(ans,count);
            }
        }
        return ans;
    }
}