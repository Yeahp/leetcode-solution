package demo;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class ListTest {

    public void rr(String s) {
        int i = 0;
        if (i == 1) {
            return ;
        }
        System.out.println("we");
    }
    public static void main(String[] args) {
        //String regionRequestId = "MemberId" + "OpenId" + "unionId" + System.nanoTime();
        //String requestId = DESUtils.md5(regionRequestId);
//        char c[] = new char[]{'a','b','c','d'};
//        int i[] = new int[]{1,2,3,4,5,6};
//        String s = new String(c, 0, 3);
//        String ss = new String(i, 0, 5);
//        System.out.println(s);
//        System.out.println(ss);
//        System.out.println(StaticTest.i);
//        StringBuilder sb = new StringBuilder();
//        ListTest listTest = new ListTest();
//        listTest.rr("we");
//        ToString toString = new ToString("we");
//        System.out.println(toString);
//        float f = 1.2f;
//        System.out.println(new BigDecimal(1 / (1 + Math.exp(-2.505))).setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue());
//        System.out.println(System.nanoTime());
//        System.out.println(System.currentTimeMillis());
        try {
            String test = "if i miss you .";
            byte btest[] = test.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btest);
            byte md[] = mdInst.digest();
            int len = btest.length;

            for (int i = 0; i < len; i++) {
                System.out.println(String.valueOf(btest[i]) + " : " + String.valueOf(md[i]));
            }
            char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
            System.out.println(btest[0] >>> 4 & 0xf);
            // 把密文转换成十六进制的字符串形式
//            int j = md.length;
//            char str[] = new char[j * 2];
//            int k = 0;
//            for (int i = 0; i < j; i++) {
//                byte byte0 = md[i];
//                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
//                str[k++] = hexDigits[byte0 & 0xf];
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
