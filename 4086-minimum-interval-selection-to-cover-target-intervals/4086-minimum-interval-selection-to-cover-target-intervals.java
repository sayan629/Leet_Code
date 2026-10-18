import java.util.*;

class Solution {
    public int minSelection(int[][] availableIntervals, int[][] targetIntervals) {
        Arrays.sort(availableIntervals, (a, b) -> Integer.compare(a[0], b[0]));
        Arrays.sort(targetIntervals, (a, b) -> Integer.compare(a[0], b[0]));

        int i = 0;
        int ans = 0;
        int covered = -1;
        for (int[] target : targetIntervals) {
            int left = target[0];
            int right = target[1];
            if (covered >= right) {
                continue;
            }
            if (covered < left) {
                int farthest = covered;
                while (i < availableIntervals.length &&
                       availableIntervals[i][0] <= left) {

                    farthest = Math.max(
                        farthest,
                        availableIntervals[i][1]
                    );
                    i++;
                }

                if (farthest < left) {
                    return -1;
                }

                covered = farthest;
                ans++;
            }
            while (covered < right) {
                int farthest = covered;
                while (i < availableIntervals.length &&
                       availableIntervals[i][0] <= covered) {
                    farthest = Math.max(
                        farthest,
                        availableIntervals[i][1]
                    );
                    i++;
                }

                if (farthest == covered) {
                    return -1;
                }
               covered = farthest;
                ans++;
            }
        }
        return ans;
    }
}