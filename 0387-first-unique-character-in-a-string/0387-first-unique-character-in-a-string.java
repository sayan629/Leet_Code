class Solution {
    public int firstUniqChar(String s) {
        HashMap<Character, Integer>map = new HashMap<>();
        Queue<Integer> q = new LinkedList<>();
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            map.put(ch,map.getOrDefault(ch,0)+1);
            q.offer(i);
            while(!q.isEmpty() && map.get(s.charAt(q.peek()))>1){
                q.poll();
            }
        }
        return q.isEmpty()?-1:q.peek();
        
    }
}