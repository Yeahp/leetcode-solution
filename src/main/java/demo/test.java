import com.sun.javafx.collections.MappingChange;

import java.io.IOException;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.Console;

public class test {
    public static void main(String[] args) throws IOException {
        //String s = "wearetheworld!";
        //System.out.println(s.codePointCount(0, s.length()));
        //System.out.println(s.charAt(13));
        //System.out.println(s.offsetByCodePoints(1,7));
        //System.out.println((int) 'a');
        //System.out.println(s.indexOf(97));
        //StringBuilder s = new StringBuilder();
        //s.append('s');
        //System.out.println(s.insert(0,"we"));
        /*
        Scanner sf = new Scanner(System.in);
        System.out.println("pls input your name");
        String d = sf.next();
        System.out.println(d);
        */
        //Console fg = System.console();
        //String gg = fg.readLine("name:");
        //char[] ff = fg.readPassword("pass");
        //char[] ggg = new char[2];
        //ggg[0] = 's';
        //ggg[1] = 'd';
        //System.out.println(ggg);

        /*
        for (weda s: weda.values()){
            System.out.println(s.toString());
        }
        weda t = weda.FRI;
        switch(t){
            case FRI:
                System.out.println("今天是周五");
                break;
        }

        int ii = 1;
        while(ii<=10){
            System.out.println(ii);
            ii++;
        }

        System.out.println(ii);

        int iii = 1;
        do{
            System.out.println(iii);
            iii++;
        }while(iii<=10);
        System.out.println(iii);

        for(int i = 1; i < 10; i++){
            for(int j =1; j < i+1; j++){
                System.out.print(i + "*" + j + "=" + i * j + "  ");
            }
            System.out.print("\n");
        }
        String[] names = new String[]{"we","er","rt"};
        System.out.print(names.length);
        Map<String, Integer> nameId = new HashMap<String, Integer>();
        nameId.put("we",45);
        System.out.print(names);
        */
        int[] arr = new int[]{3,2,4,1};
        for(int i = 0; i < arr.length - 1; i++){
            for(int j = i+1; j < arr.length; j++){
                if(arr[i] > arr[j]){
                    int tmp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = tmp;
                }
            }
        }
        for(int i=0; i< 4; i++){
            System.out.println(arr[i]);
        }
        String o = "we,we";
        String[] y = o.split(",");


    }
}
