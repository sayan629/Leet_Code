class Solution {
    public int numOfSubarrays(int[] arr) {
        final int MOD = 1_000_000_007;
        int evenCount = 1;
        int oddCount = 0;
        int prefixParity = 0;
        long ans = 0;
        
        for (int num : arr) {
            prefixParity ^= (num & 1);
            if (prefixParity == 1) {
                ans += evenCount;
                oddCount++;
            } else {
                ans += oddCount;
                evenCount++;
            }
        }
        return (int) (ans % MOD);
    }
}