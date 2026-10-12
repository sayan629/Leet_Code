class Solution {
    public boolean wordPattern(String pattern, String s) {
        String [] arr = s.split(" ");
        if(pattern.length()!= arr.length){
            return false;
        }
        HashMap<Character, String> hash = new HashMap<>();
        for(int i =0;i<pattern.length();i++){
            char ch = pattern.charAt(i);
            boolean containsKey = hash.containsKey(ch);
            if(hash.containsValue(arr[i]) && !containsKey){
                return false;
            }
             if(containsKey && !hash.get(ch).equals(arr[i])){
                return false;
            }
            else{
                hash.put(ch, arr[i]);
            }
        }
    return true;

    }
}