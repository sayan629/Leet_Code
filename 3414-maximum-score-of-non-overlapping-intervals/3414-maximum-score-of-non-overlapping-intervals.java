import java.util.*;

class Solution {
    private static class Interval {
        int l, r, weight, origIndex;

        Interval(int l, int r, int weight, int origIndex) {
            this.l = l;
            this.r = r;
            this.weight = weight;
            this.origIndex = origIndex;
        }
    }

    private static class State {
        long weight;
        List<Integer> indices;

        State(long weight, List<Integer> indices) {
            this.weight = weight;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        Interval[] sorted = new Interval[n];

        for (int i = 0; i < n; i++) {
            List<Integer> interval = intervals.get(i);
            sorted[i] = new Interval(interval.get(0), interval.get(1), interval.get(2), i);
        }

        // Sort intervals primarily by start time 'l'
        Arrays.sort(sorted, Comparator.comparingInt(a -> a.l));

        int[] starts = new int[n];
        for (int i = 0; i < n; i++) {
            starts[i] = sorted[i].l;
        }

        // Precompute next non-overlapping index for each sorted interval using binary search
        int[] nextPos = new int[n];
        for (int i = 0; i < n; i++) {
            nextPos[i] = binarySearch(starts, sorted[i].r);
        }

        // dp[i][k] stores the optimal State taking at most k intervals from suffix starting at index i
        State[][] dp = new State[n + 1][5];
        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                dp[i][k] = new State(0, new ArrayList<>());
            }
        }

        // Fill DP table right-to-left
        for (int i = n - 1; i >= 0; i--) {
            int nxt = nextPos[i];
            int currIdx = sorted[i].origIndex;
            long currW = sorted[i].weight;

            for (int k = 1; k <= 4; k++) {
                // Option 1: Skip current interval
                State skip = dp[i + 1][k];

                // Option 2: Include current interval
                State nxtState = dp[nxt][k - 1];
                long takeWeight = currW + nxtState.weight;
                List<Integer> takeIndices = new ArrayList<>(nxtState.indices);
                
                // Keep indices sorted to maintain proper lexicographical representation
                int insertPos = Collections.binarySearch(takeIndices, currIdx);
                if (insertPos < 0) insertPos = -insertPos - 1;
                takeIndices.add(insertPos, currIdx);

                State take = new State(takeWeight, takeIndices);

                // Choose the state with higher weight, or lexicographically smaller indices on tie
                dp[i][k] = compareStates(skip, take) >= 0 ? skip : take;
            }
        }

        // Convert the best indices list to int array
        List<Integer> resultList = dp[0][4].indices;
        int[] result = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }

        return result;
    }

    // Binary search to find smallest index with start time > target (upper_bound)
    private int binarySearch(int[] starts, int target) {
        int low = 0, high = starts.length;
        while (low < high) {
            int mid = (low + high) >>> 1;
            if (starts[mid] > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    // Returns > 0 if s1 is strictly better, < 0 if s2 is strictly better, 0 if equal
    private int compareStates(State s1, State s2) {
        if (s1.weight != s2.weight) {
            return Long.compare(s1.weight, s2.weight);
        }

        // Tie-breaker: Lexicographically smaller list of indices wins
        int len1 = s1.indices.size();
        int len2 = s2.indices.size();
        int minLen = Math.min(len1, len2);

        for (int i = 0; i < minLen; i++) {
            int cmp = Integer.compare(s1.indices.get(i), s2.indices.get(i));
            if (cmp != 0) {
                return -cmp; // smaller index is preferred
            }
        }

        return -Integer.compare(len1, len2);
    }
}