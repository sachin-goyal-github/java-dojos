package com.sg.concurrency;

/**
 * Created by sachin on 25/09/14.
 */
public class NamePrinter implements Runnable {

    private final String name;

    NamePrinter(final String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + " " + Math.random() + " " + Thread.currentThread().getName());
    }
}