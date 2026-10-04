class Solution {
    public boolean checkValidString(String s) {

        int low = 0;
        int high = 0;

        for (char c : s.toCharArray()) {

            if (c == '(') {
                low++;
                high++;
            } 
            else if (c == ')') {
                low--;
                high--;
            } 
            else { // '*'
                low--;
                high++;
            }

            // Minimum balance cannot be negative
            low = Math.max(low, 0);

            // Even maximum balance became negative
            if (high < 0) {
                return false;
            }
        }

        return low == 0;
    }
}