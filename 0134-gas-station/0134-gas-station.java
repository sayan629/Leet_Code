class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalgas = 0, totalcost = 0;
        for(int i=0;i<gas.length;i++){
            totalgas += gas[i];
            totalcost += cost[i];
        }
        if(totalgas<totalcost){
            return -1;
        }
        int currentgas=0, startidx=0;
        for(int i=0;i<gas.length;i++){
            int count = gas[i]-cost[i];
            currentgas += count;
            if(currentgas < 0){
                startidx = i+1;
                currentgas = 0;
            }
        }
        return startidx;
    }
}