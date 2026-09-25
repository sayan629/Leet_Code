import java.util.*;

class Solution {
    public List<String> braceExpansionII(String expression) {
        Deque<Set<String>> resStack = new ArrayDeque<>();
        Deque<Set<String>> curStack = new ArrayDeque<>();

        Set<String> res = new HashSet<>(); 
        Set<String> cur = new HashSet<>(); 
        cur.add(""); 

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isLetter(c)) {
                
                Set<String> next = new HashSet<>();
                next.add(String.valueOf(c));
                cur = concat(cur, next);
            } else if (c == '{') {
                
                resStack.push(res);
                curStack.push(cur);
                res = new HashSet<>();
                cur = new HashSet<>();
                cur.add("");
            } else if (c == ',') {
                
                res.addAll(cur);
                cur = new HashSet<>();
                cur.add("");
            } else if (c == '}') {
               
                res.addAll(cur);
                Set<String> groupRes = res;

                res = resStack.pop();
                cur = curStack.pop();
                cur = concat(cur, groupRes);
            }
        }

        res.addAll(cur);

        List<String> result = new ArrayList<>(res);
        Collections.sort(result);
        return result;
    }

    private Set<String> concat(Set<String> set1, Set<String> set2) {
        Set<String> result = new HashSet<>();
        for (String s1 : set1) {
            for (String s2 : set2) {
                result.add(s1 + s2);
            }
        }
        return result;
    }
}