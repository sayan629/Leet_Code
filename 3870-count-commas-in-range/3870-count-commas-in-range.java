class Solution {
    public int countCommas(int n) {
        int count = 0;
        if(n>=1000){
            count += Math.min(n, 999999) - 999;
        }
        if(n>=1000000){
            count += 2L * (Math.min(n, 999999999) - 999999);
        }
        if (n >= 1000000000) {
            count += 3L * (n - 999999999L);
        }
        return count;
    }
}