class Solution {
    public int numberOfSets(int n, int k) {
        long MOD = 1000000007;
        
        // Compute Combination: C(n + k - 1, 2 * k) % MOD
        long N = n + k - 1;
        long R = 2 * k;
        
        if (R > N) {
            return 0;
        }
        
        // Pre-calculate factorials/inverses or compute C(N, R) directly
        long num = 1;
        long den = 1;
        
        for (int i = 1; i <= R; i++) {
            num = (num * (N - i + 1)) % MOD;
            den = (den * i) % MOD;
        }
        
        // Result = (num * den^(-1)) % MOD using Fermat's Little Theorem
        return (int) ((num * modInverse(den, MOD)) % MOD);
    }
    
    private long modInverse(long base, long exp) {
        return power(base, exp - 2, exp);
    }
    
    private long power(long base, long exp, long mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = (res * base) % mod;
            }
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }
}