package com.sg.concurrency;

import java.util.Scanner;

/**
 * Created by sachin on 30/09/14.
 */
public class InterruptTester {
    public static void main(String[] args) {
        final Thread printer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (Thread.interrupted()) {
                        System.out.println("Interrupted. Stop printing");
                        break;
                    }
                    System.out.println("Printing something random: " + Math.random());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        });

        printer.start();

        final Thread inputer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("Do you want to stop printing?: Y/N");
                    final Scanner in = new Scanner(System.in);
                    final String input = in.nextLine();
                    if (input.equals("Y")) {
                        printer.interrupt();
                        break;
                    }
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        inputer.start();
    }
}
