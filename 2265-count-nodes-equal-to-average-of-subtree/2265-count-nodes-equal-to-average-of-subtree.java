class Solution {

    int matchingNodes = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return matchingNodes;
    }

    // Returns {sum of subtree, number of nodes in subtree}
    private int[] dfs(TreeNode root) {

        if (root == null) {
            return new int[]{0, 0};
        }

        int[] left = dfs(root.left);
        int[] right = dfs(root.right);

        int totalSum = root.val + left[0] + right[0];
        int totalCount = 1 + left[1] + right[1];

        int average = totalSum / totalCount;

        if (average == root.val) {
            matchingNodes++;
        }

        return new int[]{totalSum, totalCount};
    }
}