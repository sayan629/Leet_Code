class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer>helperStack = new Stack<>();
        int n = temperatures.length;
        int [] res = new int[n];
        for(int i =n-1;i>=0;i--){
            while(!helperStack.isEmpty() && temperatures[i]>= temperatures[helperStack.peek()]){
                helperStack.pop();
            }

            if(!helperStack.isEmpty()){
                res[i] = helperStack.peek() - i;
            }
            helperStack.push(i);
        }
        return res;
    }
}