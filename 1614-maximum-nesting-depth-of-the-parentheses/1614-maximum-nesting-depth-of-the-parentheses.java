class Solution {
    public int maxDepth(String s) {
        int maxOpen = 0;
        int currentOpen = 0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('){
                currentOpen++;
            }
            else if(s.charAt(i)==')'){
                currentOpen--;
            }
            maxOpen = Math.max(currentOpen,maxOpen);
        }
        return maxOpen;
    }
}