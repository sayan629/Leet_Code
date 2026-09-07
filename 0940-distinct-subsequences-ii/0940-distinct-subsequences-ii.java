class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        
        long[] last = new long[26];
        
        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            
            long currentSum = 0;
            for (long count : last) {
                currentSum = (currentSum + count) % MOD;
            }
    
            last[idx] = (1 + currentSum) % MOD;
        }

        long total = 0;
        for (long count : last) {
            total = (total + count) % MOD;
        }
        
        return (int) total;
    }
}