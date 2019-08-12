package demo;

public class TestMultiThread {
    public static void main(String[] args) {
        //SubThread subThread = new SubThread();
        //subThread.start();

        SubThreadB stb = new SubThreadB();
        Thread stbt1 = new Thread(stb);
        stbt1.setName("Thead-1");

        Thread stbt2 = new Thread(stb);
        stbt2.setName("Thead-2");

        Thread stbt3 = new Thread(stb);
        stbt3.setName("Thread-3");

        stbt1.start();
        stbt2.start();
        stbt3.start();
    }
}

