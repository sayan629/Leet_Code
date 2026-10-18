class Solution {
    public int findColoredRoot(int[][] edges, String colors) {
        int n = colors.length();
        int[] indegree = new int[n];
        int[] degree = new int[n];
        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            degree[u]++;
            degree[v]++;
            char cu = colors.charAt(u);
            char cv = colors.charAt(v);
            if (nextColor(cu) == cv) {
                indegree[v]++;
            } else if (nextColor(cv) == cu){
                indegree[u]++;
            } else {
                return -1;
            }
        }
        int root = -1;
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                if (root != -1) {
                    return -1;
                }
                root = i;
            }
        }
        if (root == -1) {
            return -1;
        }
        if (degree[root] > 2) {
            return -1;
        }
        for (int i = 0; i < n; i++) {
            if (i != root && degree[i] > 3) {
                return -1;
            }
        }
        return root;
    }
    private char nextColor(char c) {
        if (c == 'R') return 'B';
        if (c == 'B') return 'W';
        return 'R';
    }
}