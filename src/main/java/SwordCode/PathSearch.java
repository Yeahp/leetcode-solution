package SwordCode;

public class PathSearch {

    // 判断一个字符数组，是否存在一条包含给定字符串所有字符的路径
    public static boolean arrayPathContainString(char[][] arr, int row, int col, String s) {
        //if (s == null || s.length() ==0) return true;
        //if (arr == null) return false;
        return _firstElementSelect(arr, 0, 0, row, col, s);
    }
    private static boolean _firstElementSelect(char[][] arr, int startRow, int startCol, int row, int col, String s) {
        if (startRow >= row || startCol >= col) return false;
        if (arr[startRow][startCol] == s.charAt(0)) return _arrayPathCheck(arr,startRow, startCol, row, col, s);
        return _firstElementSelect(arr, startRow + 1, startCol, row, col, s)
                || _firstElementSelect(arr, startRow, startCol + 1, row, col, s);
    }
    private static boolean _arrayPathCheck(char[][] arr, int startRow, int startCol, int row, int col, String subStr) {
        if (subStr.length() > 0) {
            if (startRow >= row || startCol >= col) return false;
            if (subStr.length() == 1) return arr[startRow][startCol] == subStr.charAt(0);
            return arr[startRow][startCol] == subStr.charAt(0)
                    && _arrayPathCheck(arr, startRow + 1, startCol, row, col, subStr.substring(1))
                    && _arrayPathCheck(arr, startRow, startCol + 1, row, col, subStr.substring(1));
        }
        return true;
    }


    // 有 m*n 方格，开始时机器人坐标 (0,0)，每次左、右、上、下移动
    // 但不能进入行坐标和列坐标和大于 k 的格子
    // 例如：当 k=18，机器人可以进入 (35,37)， 因为 3+5+3+7=18
    // 不能进入 (35,38)，因为 3+5+3+8=19>18

}
