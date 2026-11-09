import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int[] requestCount = new int[k];
        
        PriorityQueue<int[]> busyServers = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        TreeSet<Integer> availableServers = new TreeSet<>();
        
        for (int i = 0; i < k; i++) {
            availableServers.add(i);
        }

        for (int i = 0; i < arrival.length; i++) {
            int time = arrival[i];
            int duration = load[i];

            while (!busyServers.isEmpty() && busyServers.peek()[0] <= time) {
                availableServers.add(busyServers.poll()[1]);
            }

            if (availableServers.isEmpty()) {
                continue;
            }

            Integer assignedServer = availableServers.ceiling(i % k);
            if (assignedServer == null) {
                assignedServer = availableServers.first();
            }

            requestCount[assignedServer]++;
            availableServers.remove(assignedServer);
            busyServers.offer(new int[]{time + duration, assignedServer});
        }

        int maxRequests = 0;
        for (int count : requestCount) {
            maxRequests = Math.max(maxRequests, count);
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (requestCount[i] == maxRequests) {
                result.add(i);
            }
        }

        return result;
    }
}