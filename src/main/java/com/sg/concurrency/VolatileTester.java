package com.sg.concurrency;

/**
 * Created by sachin on 01/10/14.
 */
public class VolatileTester {

    public static void main(String[] args) {
        final NumberCounter numberCounter = new NumberCounter();

        new Thread(new Runnable() {
            @Override
            public void run() {
                numberCounter.inc();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(numberCounter.val());
            }
        }, "t2").start();
    }

    static class NumberCounter {
        volatile int i = 0;

        void inc() {
            i++;
        }

        void dec() {
            i++;
        }

        int val() {
            return i;
        }
    }
}