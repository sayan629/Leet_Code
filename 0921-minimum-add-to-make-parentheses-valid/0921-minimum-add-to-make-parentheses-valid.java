class Solution {
    public int minAddToMakeValid(String s) {
        // Stack<Character>st = new Stack<>();
        int open=0, close=0;
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(ch == '('){
                open++ ;//st.push(ch);
            }
            else{
                if(open<=0){// if(st.isEmpty() || st.peek() == ')'){
                    close++;
                }
                else{
                    open--; //st.pop();
                }
            }
        }
         return (open+close);//return st.size();
    }
}