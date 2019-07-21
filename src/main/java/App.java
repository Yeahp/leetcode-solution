import java.util.Arrays;
import java.util.BitSet;

public class App {

    private static char charAt(String text, int pos) {
        pos = pos % text.length();
        return text.charAt(pos);
    }

    private static int compare(char a, char b, boolean greater) {
        if (a == b)
            return 0;
        return (a < b) ^ greater ? -1 : 1;
    }

    private static String bestRotation(String text, boolean greatest) {
        if (text.length() < 2)
            return text;

        final int n = text.length() * 2;
        int k = 0;
        int i = 0, j = 1;
        while (i + k < n && j + k < n) {
            final char a = charAt(text, i+k);
            final char b = charAt(text, j+k);
            final int comp = compare(a, b, greatest);
            if (comp == 0) {
                k++;
            } else if (comp > 0) {
                i += k+1;
                if (i <= j )
                    i = j + 1;
                k = 0;
            } else {
                j += k+1;
                if (j <= i)
                    j = i + 1;
                k = 0;
            }
        }
        final int pos = i < j ? i : j;
        return text.substring(pos) + text.substring(0, pos);
    }

    public static String[] generateSubsets(String inputString) {
        final int length = inputString.length();
        final int size = (int) Math.pow(2, length);
        final BitSet[] sets = new BitSet[size];
        final String[] output = new String[size];

        for (int i = 0; i < size; i++) {
            final BitSet set = new BitSet(size);
            final StringBuilder builder = new StringBuilder();
            if (i > 0) {
                for (int j = length - 1; j >= 0; j--) {
                    if (j == length - 1) {
                        if (i % 2 != 0)
                            set.set(j, true);
                    } else {
                        boolean prev = sets[i - 1].get(j);
                        boolean next = true;
                        for (int k = j + 1; k < length; k++) {
                            next = next && sets[i - 1].get(k);
                        }
                        if (next)
                            prev = !prev;
                        set.set(j, prev);
                    }
                    if (set.get(j))
                        builder.append(inputString.charAt(j));
                }
            }
            sets[i] = set;
            output[i] = builder.toString();
        }
        return output;
    }

    public static String[] _generateSubsets(String inputString) {
        final int length = inputString.length();
        final int size = (int) Math.pow(2, length);
        final String[] output = new String[size];

        for (int i = 0; i < size; i++) {
            String is = Integer.toBinaryString(i);
            int _size = is.length();
            StringBuilder sb = new StringBuilder();
            for (int j = length - _size; j < length; j++) {
                if (is.charAt(j + _size - length) == '1') {
                    sb.append(inputString.charAt(j));
                }
            }
            output[i] = sb.toString();
        }
        return output;
    }

    public static void main(String[] args) {
        String str = "abcd";
        System.out.println(Arrays.toString(App._generateSubsets(str)));
        //BitSet bs = new BitSet(2);
        //System.out.println(bs);
        //StringBuilder sb = new StringBuilder();
        //System.out.println(sb.toString());
        //System.out.println(false ^ true);
    }
}
