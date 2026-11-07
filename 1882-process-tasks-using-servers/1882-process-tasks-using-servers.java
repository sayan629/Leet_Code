import java.util.PriorityQueue;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        
        PriorityQueue<int[]> freeServers = new PriorityQueue<>(
            (a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1])
        );
        
        PriorityQueue<int[]> busyServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            if (a[1] != b[1]) return Integer.compare(a[1], b[1]);
            return Integer.compare(a[2], b[2]);
        });
        
        for (int i = 0; i < n; i++) {
            freeServers.offer(new int[]{servers[i], i});
        }
        
        int[] ans = new int[m];
        int currTime = 0;
        
        for (int j = 0; j < m; j++) {
            currTime = Math.max(currTime, j);
            
            if (freeServers.isEmpty()) {
                currTime = busyServers.peek()[0];
            }
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= currTime) {
                int[] server = busyServers.poll();
                freeServers.offer(new int[]{server[1], server[2]});
            }
            
            int[] bestServer = freeServers.poll();
            ans[j] = bestServer[1];
            
            busyServers.offer(new int[]{currTime + tasks[j], bestServer[0], bestServer[1]});
        }
        return ans;
    }
}