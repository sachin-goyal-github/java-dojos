package com.sg.concurrency;

import java.io.IOException;

/**
 * Created by sachin on 30/09/14.
 */
public class ProcessBuilderTest {
    public static void main(String[] args) throws IOException {
        final ProcessBuilder processBuilder = new ProcessBuilder("notepad");
        processBuilder.start();
    }
}
