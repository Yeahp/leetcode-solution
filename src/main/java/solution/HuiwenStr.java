package solution;

public class HuiwenStr {

    // judget whether a string is a huiwen one
    public boolean isHuiwenStr(String s) {
        int len = s.length();
        if (len == 0) return true;
        int i = 0;
        int j = len - 1;
        while (i < j) {
            if (!Character.isLetterOrDigit(s.charAt(i))) {
                i++;
            } else if (!Character.isLetterOrDigit(s.charAt(j))) {
                j--;
            } else {
                if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) return false;
                i++;
                j--;
            }
        }
        return true;
    }

}
