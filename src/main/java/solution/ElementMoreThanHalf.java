package solution;

public class ElementMoreThanHalf {

    public Character getEleOccurMoreThanHalf(char[] s) {
        if (s.length < 1) return null;
        else if (s.length <= 2) return s[0];
        else {
            int cnt = 1;
            char tmp = s[0];
            for (int i = 1; i < s.length; i++) {
                if (s[i] != tmp) {
                    if (cnt > 1) {
                        cnt--;
                    } else {
                        cnt = 1;
                        tmp = s[i];
                    }
                } else {
                    cnt++;
                }
            }
            return tmp;
        }
    }

    public static void main(String[] args) {
        char[] s = {'a', 'b', 'a', 'c', 'a', 'd', 'a', 'e', 'a'};
        ElementMoreThanHalf e = new ElementMoreThanHalf();
        System.out.println(e.getEleOccurMoreThanHalf(s));
    }
}
