import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }
        int[][] dist = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        dist[0][1] = 0;
        pq.offer(new int[]{0, 0, 1});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int d = current[0];
            int u = current[1];
            int count = current[2];
            if (u == n - 1) {
                return d;
            }

            if (d > dist[u][count]) {
                continue;
            }

            for (int[] neighbor : graph.get(u)) {
                int v = neighbor[0];
                int weight = neighbor[1];

                int newCount = (labels.charAt(u) == labels.charAt(v)) ? count + 1 : 1;

                if (newCount <= k) {
                    if (d + weight < dist[v][newCount]) {
                        dist[v][newCount] = d + weight;
                        pq.offer(new int[]{dist[v][newCount], v, newCount});
                    }
                }
            }
        }

        return -1;
    }
}