class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer>newSet = new HashSet<>();
        for(int num:nums){
            if(newSet.contains(num)){
                return true;
            }
            newSet.add(num);
        }
        return false;
    }
}