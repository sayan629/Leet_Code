class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long costSeparate = (long) need1 * cost1 + (long) need2 * cost2;
        int common = Math.min(need1, need2);
        long costPartialBoth = (long) common * costBoth + (long) (need1 - common) * cost1 + (long) (need2 - common) * cost2;

        int maxNeed = Math.max(need1, need2);
        long costFullBoth = (long) maxNeed * costBoth;
        
        return Math.min(costSeparate, Math.min(costPartialBoth, costFullBoth));
    }
}