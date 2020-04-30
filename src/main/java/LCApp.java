import java.util.Random;

/**
 * @Author erpeng.qi
 * @Date 2020/4/14 11:57
 */
public class LCApp {

    // 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2
    // 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))
    public float middle(int[] num1, int m, int[] num2, int n) {
        int ms = 0;  // 用于指示第一个数组的下标
        int ns = 0;  // 用于指示第二个数组的下标

        return 0.0f;
    }

    public static boolean test1() {
        float a = 0.125f;
        double b = 0.125d;
        return a - b == 0.0;  // true
    }

    public static boolean test2() {
        double c = 0.8;
        double d = 0.7;
        double e = 0.6;
        return c - d == d - e;  // false
    }

    public static void test3() {
        System.out.println(1.0 / 0); // Infinity
    }

    public static void test4() {
        System.out.println(0.0 / 0); // NaN
    }

    public void f(String s) {
        System.out.println("string");
    }
    public void f(Integer i) {
        System.out.println("integer");
    }
    public void f(double i) {
        System.out.println("double");
    }


    public static void main(String[] args) {
        LCApp app = new LCApp();
    }



}
