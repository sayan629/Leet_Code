import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, Integer> dp = new HashMap<>();
        int maxLen = 1;

        for (int num : nums) {
            int lenPlusOne = dp.getOrDefault(num, 0) + 1;
            int lenSame = dp.getOrDefault(num - 1, 0) + 1;

            dp.put(num + 1, Math.max(dp.getOrDefault(num + 1, 0), lenPlusOne));
            dp.put(num, Math.max(dp.getOrDefault(num, 0), lenSame));

            maxLen = Math.max(maxLen, Math.max(dp.get(num), dp.get(num + 1)));
        }

        return maxLen;
    }
}