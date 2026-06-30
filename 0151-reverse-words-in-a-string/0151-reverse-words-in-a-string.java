class Solution {
    public String reverseWords(String s) {

        //Remove spaces form beginning and end
        s = s.trim();

        //Split wherever there are one or more spaces (or other whitespace).
        String [] w = s.split("\\s+");
        String res="";
        for(int i =w.length-1;i>=0;i--){
            res = res+w[i];
            if(i>0){
                res = res+ " ";
            }
        }
        return res;
    }
}