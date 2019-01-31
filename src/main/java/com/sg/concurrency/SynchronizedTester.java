package com.sg.concurrency; /**
 * Created by sachin on 01/10/14.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * If you have a lock on an object (i.e. a thread is executing a synchronized method), then that method or block or
 * any other synchronised method or block can not be executed by any other thread at the same time.
 * <p/>
 * If a thread is executing a static synchronized method or block then the lock is on Class object, what it means is that
 * that method or block or any other statically synchronised method or block can not be executed by any other thread
 * at the same time.
 */
public class SynchronizedTester {

    public static void main(String[] args) {
        final NumberCounter numberCounter = new NumberCounter();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                numberCounter.printAscending();
//            }
//        }, "t1").start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                numberCounter.printAscending();
//            }
//        }, "t2").start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                numberCounter.printAscending();
            }
        }, "t1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                numberCounter.printDescending();
            }
        }, "t2").start();
    }

    static class NumberCounter {
        synchronized void printAscending() {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " Ascends: " + i);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        synchronized void printDescending() {
            for (int i = 500; i > 100; i -= 100) {
                System.out.println(Thread.currentThread().getName() + " Descends: " + i);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class WaitNotifyTester {
        final List<String> names = new ArrayList<String>();

        void addName(String name) {
            synchronized (names) {
                names.notify();
                System.out.println(Thread.currentThread().getName() + " added " + name);
                names.add(name);
            }
        }

        String removeName() {
            synchronized (names) {
                if (names.size() == 0) {
                    try {
                        names.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                final String name = names.remove(names.size() -1);
                System.out.println(Thread.currentThread().getName() + " removed " + name);
                return name;
            }
        }

        public static void main(String[] args) {
            final WaitNotifyTester waitNotifyTester = new WaitNotifyTester();
            new Thread(new Runnable() {public void run() {waitNotifyTester.addName("Sachin");}}).start();
            new Thread(new Runnable() {public void run() {waitNotifyTester.addName("Aditi");}}).start();

            new Thread(new Runnable() {public void run() {waitNotifyTester.removeName();}}).start();
            new Thread(new Runnable() {public void run() {waitNotifyTester.removeName();}}).start();
            new Thread(new Runnable() {public void run() {waitNotifyTester.removeName();}}).start();
            new Thread(new Runnable() {public void run() {waitNotifyTester.removeName();}}).start();
            new Thread(new Runnable() {public void run() {waitNotifyTester.removeName();}}).start();
            new Thread(new Runnable() {public void run() {waitNotifyTester.removeName();}}).start();

            new Thread(new Runnable() {public void run() {waitNotifyTester.addName("Puja");}}).start();
            new Thread(new Runnable() {public void run() {waitNotifyTester.addName("David");}}).start();
            new Thread(new Runnable() {public void run() {waitNotifyTester.addName("Richard");}}).start();
            new Thread(new Runnable() {public void run() {waitNotifyTester.addName("Jason");}}).start();
        }
    }
}