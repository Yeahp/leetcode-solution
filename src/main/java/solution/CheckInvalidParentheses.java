package solution;

import java.util.*;

public class CheckInvalidParentheses {

    private Set<String> vis = new HashSet<String>();
    private Queue<String> queue = new LinkedList<String>();

    public List<String> removeInvalidParentheses(String s) {
        queue.offer(s);
        vis.add(s);
        boolean flag = false;
        List<String> ans = new ArrayList<String>();
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            if (flag) check(cur, ans);
            else flag = checkLeft(cur, ans) || checkRight(cur, ans);
        }
        if (ans.size() == 0) ans.add("");
        return new ArrayList<String>(ans);
    }

    private void check(String s, List<String> ans){  //查看是否正确
        Stack<Character> st = new Stack<Character>();
        for(char c : s.toCharArray()) {
            if (c == '(') st.push(c);
            if (c == ')') {
                if(st.isEmpty()) return;
                st.pop();
            }
        }
        if(st.isEmpty()){
            ans.add(s);
        }
    }

    private boolean checkLeft(String s, List<String> ans) { //检查左边
        //delete right
        Stack<Character> st = new Stack<Character>();
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(') {
                st.push(s.charAt(i));
            } else if (s.charAt(i)==')') {
                if(st.isEmpty()) {
                    for(int j = i; j >= 0; --j) { //删除不符合的')'  多种情况
                        if (s.charAt(j) == ')') {
                            String s1 = s.substring(0, j) + s.substring(j + 1);
                            if (!vis.contains(s1)) {
                                vis.add(s1);
                                queue.offer(s1);
                            }
                        }
                    }
                    return false;
                } else {
                    st.pop();
                }
            }
        }
        if (st.isEmpty()) {
            ans.add(s);
            return true;
        }
        return false;
    }

    private boolean checkRight(String s,List<String> ans){ //检查右边
        //delete right
        Stack<Character> st = new Stack<Character>();
        st.clear();
        for (int i = s.length()-1; i >= 0; --i) {
            if (s.charAt(i) == ')') {
                st.push(s.charAt(i));
            } else if (s.charAt(i) == '(') {
                if (st.isEmpty()) {
                    for (int j = i; j < s.length(); ++j) {
                        if (s.charAt(j) == '(') {  //删除不符合的'(' 多种情况
                            String s1 = s.substring(0, j) + s.substring( j + 1);
                            if (!vis.contains(s1)) {
                                vis.add(s1);
                                queue.add(s1);
                            }
                        }
                    }
                    return false;
                } else {
                    st.pop();
                }
            }
        }
        if (st.isEmpty()) {
            ans.add(s);
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        CheckInvalidParentheses c = new CheckInvalidParentheses();
//        for (String item : c.removeInvalidParentheses("(a)())()")) {
//            System.out.println(item);
//        }
        String s = "asdsed";
        System.out.println(s.substring(0, 2) + s.substring(2 + 1));
    }

}
