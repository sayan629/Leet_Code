class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int neededRights = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                // If we need an odd number of ')', it means the previous '(' 
                // only got one ')'. We must complete it with an inserted ')'.
                if (neededRights % 2 != 0) {
                    insertions++;
                    neededRights--; // Complete the pending pair
                }
                neededRights += 2;
            } else { // c == ')'
                neededRights--;
                // If neededRights becomes -1, we encountered a ')' without a matching '('
                if (neededRights == -1) {
                    insertions++;     // Insert '('
                    neededRights += 2; // '(' balances two ')'
                }
            }
        }

        return insertions + neededRights;
    }
}