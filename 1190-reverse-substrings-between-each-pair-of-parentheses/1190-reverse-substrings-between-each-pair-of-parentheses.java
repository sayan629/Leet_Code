class Solution {
    public String reverseParentheses(String s) {
        Stack<String> stack = new Stack<>();
        String current = "";
        for(char ch : s.toCharArray()){
            if(ch == '('){
                stack.push(current);
                current = "";
            }
            else if(ch == ')'){
                current = new StringBuilder(current).reverse().toString();
                String prev = stack.pop();
                current = prev+current;
            }else{
                current += ch;
            }
        }
        return current;
    }
}