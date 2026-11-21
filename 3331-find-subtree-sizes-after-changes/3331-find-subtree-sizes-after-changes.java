import java.util.*;

class Solution {
    private List<Integer>[] adj;
    private List<Integer>[] newAdj;
    private int[] lastSeen;
    private int[] newParent;
    private int[] answer;
    private String s;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        this.s = s;
        adj = new ArrayList[n];
        newAdj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
            newAdj[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            adj[parent[i]].add(i);
        }

        lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);
        newParent = Arrays.copyOf(parent, n);

        // Find new parents using DFS on original tree
        dfsFindParents(0);

        // Build the new tree
        for (int i = 1; i < n; i++) {
            newAdj[newParent[i]].add(i);
        }

        // Compute subtree sizes on the new tree
        answer = new int[n];
        dfsComputeSizes(0);

        return answer;
    }

    private void dfsFindParents(int u) {
        int charIdx = s.charAt(u) - 'a';
        int prevAncestor = lastSeen[charIdx];

        if (prevAncestor != -1) {
            newParent[u] = prevAncestor;
        }

        lastSeen[charIdx] = u;
        for (int v : adj[u]) {
            dfsFindParents(v);
        }
        lastSeen[charIdx] = prevAncestor; // Backtrack
    }

    private int dfsComputeSizes(int u) {
        int size = 1;
        for (int v : newAdj[u]) {
            size += dfsComputeSizes(v);
        }
        answer[u] = size;
        return size;
    }
}