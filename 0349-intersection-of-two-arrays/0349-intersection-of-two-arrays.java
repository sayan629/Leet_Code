class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {

        Set<Integer> set = new HashSet<>();
        Set<Integer> res = new LinkedHashSet<>();

        // Store elements of nums1
        for (int num : nums1) {
            set.add(num);
        }

        // Find common elements
        for (int num : nums2) {
            if (set.contains(num)) {
                res.add(num);
            }
        }

        // Convert Set<Integer> to int[]
        int[] ans = new int[res.size()];
        int i = 0;

        for (int num : res) {
            ans[i++] = num;
        }

        return ans;
    }
}