package com.sg.concurrency;

/**
 * Created by sachin on 25/09/14.
 */
public class ThreadTester {
    public static void main(String[] args) throws InterruptedException {

        new Thread(new NamePrinter("Sachin")).start();
        new Thread(new NamePrinter("Puja")).start();
        Thread.sleep(200);

        new Thread(new NamePrinter("Aditi")).start();
        new Thread(new NamePrinter("Manju")).start();
        new Thread(new NamePrinter("Roop")).start();

        new AddressPrinter("Edinburgh").start();
        new AddressPrinter("London").start();
        Thread.sleep(100);

        new AddressPrinter("Agra").start();
        new AddressPrinter("Mathura").start();
        Thread.sleep(100);

        new AddressPrinter("York").start();
        new AddressPrinter("LA").start();

        Thread.sleep(500);

        System.out.println(Thread.currentThread().isDaemon());
        System.out.println(Thread.currentThread().getName());
        System.out.println("Finished");
    }
}



