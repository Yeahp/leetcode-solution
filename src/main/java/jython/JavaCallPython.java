package jython;

import org.python.util.PythonInterpreter;
import java.io.*;

public class JavaCallPython {

    /*
    * this method uses python interpreter
    * but this interpreter only contains built-in modules
    * */
    public void runPython() {
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.exec("import pandas as pd; a=[5,2,3,9,4,0]; print(sorted(a));");
    }

    /*
    * this method uses python interpreter installed in local system
    * thus java code can use everything that python has
    * */
    public void _runPython() throws IOException, InterruptedException {
        String exe = "/Users/hello/Anaconda/anaconda3/bin/python3.6";
        String command = "/Users/hello/IdeaProjects/leetcode-solution/src/main/resources/cal.py";
        //ClassLoader.getSystemResourceAsStream("cal.py");
        //String pathorg = getClass().getClassLoader().getResource("cal.py").toString().split(":")[1];
        String num1 = "1";
        String num2 = "2";
        String[] cmdArr = new String[]{exe, command, num1, num2};
        Process process = Runtime.getRuntime().exec(cmdArr);
        InputStream is = process.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        String str = dis.readLine();
        process.waitFor();
        System.out.println(str);
        /*
        Process proc;
        proc = Runtime.getRuntime().exec("python3.6 /Users/hello/IdeaProjects/leetcode-solution/src/main/resources/cal.py 1 2");// 执行py文件
        BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        String line = null;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
        in.close();
        proc.waitFor();
        */
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        JavaCallPython jcp = new JavaCallPython();
//        try {
//            jcp.runPython();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        // the following statement will return the path of python script
        //String pathorg = jcp.getClass().getClassLoader().getResource("cal.py").toString().split(":")[1];
        //System.out.println(pathorg);
        jcp._runPython();
    }
}
