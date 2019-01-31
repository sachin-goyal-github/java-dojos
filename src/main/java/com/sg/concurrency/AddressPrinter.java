package com.sg.concurrency;

/**
 * Created by sachin on 25/09/14.
 */
public class AddressPrinter extends Thread {
    private final String address;

    AddressPrinter(final String address) {
        this.address = address;
    }

    @Override
    public void run() {
        System.out.println(address + " " + Math.random() + " " + Thread.currentThread().getName());
    }
}
