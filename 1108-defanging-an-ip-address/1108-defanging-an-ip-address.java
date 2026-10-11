class Solution {
    public String defangIPaddr(String address) {
        int n = address.length();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++){
            char ch = address.charAt(i);
            if(ch!='.'){
                sb.append(ch);
            }else{
                sb.append("[.]");
            }
        }
        return sb.toString();
    }
}