class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if(strs==null || strs.length == 0)
            return new ArrayList<>();

        Map<String,List<String>> map= new HashMap<>();
        for(String word:strs){

            // Convert word in an character array
            char [] chars = word.toCharArray();

            // now sorting perform
            Arrays.sort(chars);

            // so it basically character type but in hashmap it only allow string type so now create key

            String key = new String(chars);

            //check in map exists or not
            if(!map.containsKey(key)){

                // If key doesn't exist, create a new list
                map.put(key, new ArrayList<>());
            }
            // Add the original word to the list
            map.get(key).add(word);
        } 
        return new ArrayList<>(map.values());
    }
}