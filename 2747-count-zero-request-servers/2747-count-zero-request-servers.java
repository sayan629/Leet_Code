import java.util.Arrays;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = queries.length;
        int[][] sortedQueries = new int[m][2];
        for (int i = 0; i < m; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));
        Arrays.sort(sortedQueries, (a, b) -> Integer.compare(a[0], b[0]));
        int[] ans = new int[m];
        int[] cnt = new int[n + 1];
        int activeServers = 0;
        
        int left = 0;
        int right = 0;
        int numLogs = logs.length;
        
        for (int i = 0; i < m; i++) {
            int qTime = sortedQueries[i][0];
            int qIdx = sortedQueries[i][1];
            int startTime = qTime - x;
            while (right < numLogs && logs[right][1] <= qTime) {
                int server = logs[right][0];
                if (cnt[server] == 0) {
                    activeServers++;
                }
                cnt[server]++;
                right++;
            }
        
            while (left < numLogs && logs[left][1] < startTime) {
                int server = logs[left][0];
                cnt[server]--;
                if (cnt[server] == 0) {
                    activeServers--;
                }
                left++;
            }
            
            ans[qIdx] = n - activeServers;
        }
        
        return ans;
    }
}