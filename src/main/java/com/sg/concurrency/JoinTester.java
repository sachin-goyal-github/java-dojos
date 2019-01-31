package com.sg.concurrency;

/**
 * Created by sachin on 30/09/14.
 */
public class JoinTester {
    public static void main(String[] args) {
        final Thread hundredNumberPrinter = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 100; i++) {
                    System.out.println("Printing number: " + i + " of 10,000");
                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        final Thread oneNumberPrinter = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    hundredNumberPrinter.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Printing Single random number" + Math.random());
            }
        });

        oneNumberPrinter.start();
        hundredNumberPrinter.start();
    }
}


//Main thread is just a normal thread. But the JVM won't shutdown if there is any thread running
//only ofcourse all the running threads are demons then JVM will shutdown.
// So to keep in mind that main thread runs independently and it may finish before other threads
// but that doesn't mean that JVM will shutdown just after main thread was finished.
class MainJoinTester {
    public static void main(String[] args) throws InterruptedException {
        final Thread hundredNumberPrinter = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 100; i++) {
                    System.out.println("Printing number: " + i + " of 10,000");
                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        final Thread oneNumberPrinter = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    hundredNumberPrinter.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Printing Single random number" + Math.random());
            }
        });

        oneNumberPrinter.start();
        hundredNumberPrinter.start();
        oneNumberPrinter.join();
        hundredNumberPrinter.join();
        System.out.println("Finished the main thread");
    }
}
