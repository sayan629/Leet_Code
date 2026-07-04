class Solution {
    public String convert(String s, int numRows) {
        if(numRows == 1 ) return s;

        StringBuilder res = new StringBuilder();
        int len = 2*numRows-2;
        int n = s.length();
        for(int row = 0; row<numRows ;row++){
            for(int i=0;i+row<n;i+=len){
                res.append(s.charAt(i+row));

            // Handle Middle Row
            if(row!=0 && row != numRows-1 && i+len-row<n){
                res.append(s.charAt(i+len-row));
            }
            }
        }
        return res.toString();
        
    }
}