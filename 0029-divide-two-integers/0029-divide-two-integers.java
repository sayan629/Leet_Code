class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        boolean isNegative = (dividend < 0) ^ (divisor < 0);

        long absDividend = Math.abs((long) dividend);
        long absDivisor = Math.abs((long) divisor);

        long ans = 0;

        while (absDividend >= absDivisor) {
            int count = 0;
            while (absDividend >= (absDivisor << (count + 1))) {
                count++;
            }

            ans += (1L << count);
            absDividend -= (absDivisor << count);
        }

        return isNegative ? (int) -ans : (int) ans;
    }
}