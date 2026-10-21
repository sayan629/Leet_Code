class Solution {
    public int minOperations(String s) {
        int n =s.length();
        int low = 25, high =0;
        boolean sorted = true;
        for(int i=0;i<n;i++){
            int val = s.charAt(i)-'a';
            low = Math.min(low,val);
            high = Math.max(high,val);
            if(i>0 && s.charAt(i)<s.charAt(i-1)){
                sorted = false;
            }
        }
        if(sorted) return 0;
        if(n==2) return -1;

        int firstIdx = s.charAt(0)- 'a';
        int lastIdx = s.charAt(n-1)-'a';
        if(firstIdx == low || lastIdx == high) return 1;
        for(int i=1;i<n-1;i++){
            int idxVal = s.charAt(i)-'a';
            if(idxVal == low || idxVal == high) return 2;
        }
        return 3;
    
        }
    }
