package com.sg.concurrency;

import java.util.concurrent.*;

/**
 * Created by sachin on 25/09/14.
 */
public class ExecutorTester {
    static class SimpleExecutorTester {
        public static void main(String[] args) {
            final ExecutorService executorService = Executors.newFixedThreadPool(10);
            executorService.submit(new NamePrinter("Sachin1"));
            executorService.submit(new NamePrinter("Sachin2"));
            executorService.submit(new NamePrinter("Sachin3"));
            executorService.submit(new NamePrinter("Sachin4"));
            executorService.submit(new NamePrinter("Sachin5"));
            executorService.submit(new NamePrinter("Sachin6"));
            executorService.submit(new NamePrinter("Sachin7"));
            executorService.submit(new NamePrinter("Sachin8"));
            executorService.submit(new NamePrinter("Sachin9"));
            executorService.submit(new NamePrinter("Sachin10"));

            
            System.out.println("Finished");
            executorService.shutdown();
        }
    }


    static class NameJoiner implements Callable<String> {
        private final String firstName;
        private final String lastName;

        NameJoiner(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
        @Override
        public String call() throws Exception {
            System.out.println(Thread.currentThread().getName() + " joining names");
            return firstName  + " " + lastName;
        }
    }

    static class ExecutorWithCallableTester {

        public static void main(String[] args) throws ExecutionException, InterruptedException {
            final ExecutorService executorService = Executors.newFixedThreadPool(10);

            final Future<String> name1 = executorService.submit(new NameJoiner("Sachin", "Goyal"));
            final Future<String> name2 = executorService.submit(new NameJoiner("Aditi", "Goyal"));
            final Future<String> name3 = executorService.submit(new NameJoiner("Puja", "Goyal"));

            System.out.println(name1.get());
            System.out.println(name2.get());
            System.out.println(name3.get());

            System.out.println("Finished");

            executorService.shutdown();
        }
    }
}
