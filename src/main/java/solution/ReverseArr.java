package solution;

public class ReverseArr {

    // reverse an array
    public char[] reverseArr(char[] arr) {
        int i = 0;
        int j = arr.length - 1;
        while (i < j) {
            char tmp = arr[j];
            arr[j] = arr[i];
            arr[i] = arr[j];
            i++;
            j--;
        }
        return arr;
    }

}
