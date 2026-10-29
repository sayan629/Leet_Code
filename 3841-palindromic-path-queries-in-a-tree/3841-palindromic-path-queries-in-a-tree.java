class Solution {
    private int timer = 0;
    private int LOG;
    private int[] tin, tout;
    private int[][] up;
    private int[] nodeMask;
    private FenwickTree bit;

    static class FenwickTree {
        int n;
        int[] tree;

        FenwickTree(int n) {
            this.n = n;
            this.tree = new int[n + 1];
        }

        void add(int idx, int val) {
            for (idx++; idx <= n; idx += idx & -idx) {
                tree[idx] ^= val;
            }
        }

        int query(int idx) {
            int sum = 0;
            for (idx++; idx > 0; idx -= idx & -idx) {
                sum ^= tree[idx];
            }
            return sum;
        }
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        LOG = 1;
        while ((1 << LOG) <= n) LOG++;

        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        tin = new int[n];
        tout = new int[n];
        up = new int[n][LOG];
        nodeMask = new int[n];
        for (int i = 0; i < n; i++) {
            nodeMask[i] = 1 << (s.charAt(i) - 'a');
        }

        dfs(0, 0, adj);

        bit = new FenwickTree(n + 1);
        for (int i = 0; i < n; i++) {
            bit.add(tin[i], nodeMask[i]);
            if (tout[i] + 1 < n) {
                bit.add(tout[i] + 1, nodeMask[i]);
            }
        }

        List<Boolean> ans = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                int oldMask = nodeMask[u];
                int newMask = 1 << (c - 'a');
                int diff = oldMask ^ newMask;

                nodeMask[u] = newMask;
                bit.add(tin[u], diff);
                if (tout[u] + 1 < n) {
                    bit.add(tout[u] + 1, diff);
                }
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int lca = getLCA(u, v);
                int pathU = bit.query(tin[u]);
                int pathV = bit.query(tin[v]);

                int pathMask = pathU ^ pathV ^ nodeMask[lca];

                // Check if pathMask has at most one bit set
                ans.add((pathMask & (pathMask - 1)) == 0);
            }
        }

        return ans;
    }

    private void dfs(int u, int p, List<Integer>[] adj) {
        tin[u] = timer++;
        up[u][0] = p;
        for (int i = 1; i < LOG; i++) {
            up[u][i] = up[up[u][i - 1]][i - 1];
        }

        for (int v : adj[u]) {
            if (v != p) {
                dfs(v, u, adj);
            }
        }
        tout[u] = timer - 1;
    }

    private boolean isAncestor(int u, int v) {
        return tin[u] <= tin[v] && tout[u] >= tout[v];
    }

    private int getLCA(int u, int v) {
        if (isAncestor(u, v)) return u;
        if (isAncestor(v, u)) return v;

        for (int i = LOG - 1; i >= 0; i--) {
            if (!isAncestor(up[u][i], v)) {
                u = up[u][i];
            }
        }
        return up[u][0];
    }
}