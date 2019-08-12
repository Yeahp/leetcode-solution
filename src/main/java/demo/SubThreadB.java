package demo;

public class SubThreadB implements Runnable {
    private static int ticket = 200;

    public SubThreadB() {
        super();
    }

//    @Override
//    public void run() {
//        while(true) {
//            synchronized (this) {
//                if (ticket > 0) {
//                    System.out.println(Thread.currentThread().getName() + ": " + ticket--);
//                } else {
//                    break;
//                }
//            }
//        }
//    }

    public void run() {
        while(true) {
            show();
        }
    }

    public synchronized void show(){
        if (ticket > 0) {
            System.out.println(Thread.currentThread().getName() + ": " + ticket--);
        } else {
        }
    }
}
