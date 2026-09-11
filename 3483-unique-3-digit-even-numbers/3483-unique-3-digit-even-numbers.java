class Solution {
    public int totalNumbers(int[] digits) {
        int[] freq = new int[10];
        for (int d : digits) {
            freq[d]++;
        }
        
        int validCount = 0;
        
        // Check all possible 3-digit even numbers
        for (int num = 100; num < 1000; num += 2) {
            int d1 = num / 100;        // Hundreds place
            int d2 = (num / 10) % 10;  // Tens place
            int d3 = num % 10;          // Units place
            
            freq[d1]--;
            freq[d2]--;
            freq[d3]--;
            
            // If all digits were available in sufficient quantity
            if (freq[d1] >= 0 && freq[d2] >= 0 && freq[d3] >= 0) {
                validCount++;
            }
            
            // Restore frequency counts for next iteration
            freq[d1]++;
            freq[d2]++;
            freq[d3]++;
        }
        
        return validCount;
    }
}