import java.util.Arrays;

class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        // minLen[i] stores the min length of a valid subarray in arr[0...i]
        int[] minLen = new int[n];
        Arrays.fill(minLen, Integer.MAX_VALUE);
        
        int left = 0;
        int currentSum = 0;
        int result = Integer.MAX_VALUE;
        int minSoFar = Integer.MAX_VALUE;
        
        for (int right = 0; right < n; right++) {
            currentSum += arr[right];
            
            // Shrink window if currentSum exceeds target
            while (currentSum > target) {
                currentSum -= arr[left];
                left++;
            }
            
            // Found a valid subarray [left, right]
            if (currentSum == target) {
                int currentLength = right - left + 1;
                
                // If there exists a valid non-overlapping subarray to the left
                if (left > 0 && minLen[left - 1] != Integer.MAX_VALUE) {
                    result = Math.min(result, currentLength + minLen[left - 1]);
                }
                
                // Update min length seen so far up to current index 'right'
                minSoFar = Math.min(minSoFar, currentLength);
            }
            
            // Carry forward the minimum length seen up to 'right'
            minLen[right] = minSoFar;
        }
        
        return result == Integer.MAX_VALUE ? -1 : result;
    }
}