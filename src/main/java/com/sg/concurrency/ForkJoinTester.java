package com.sg.concurrency;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Created by sachin on 05/10/14.
 */
public class ForkJoinTester {
    public static void main(String[] args) {
        final ForkJoinPool pool = new ForkJoinPool();
        final FolderProcessor sachinRootProcessor = new FolderProcessor("/sachin_root");
        pool.execute(sachinRootProcessor);

        System.out.println(sachinRootProcessor.join());
        pool.shutdown();
    }
}

class FolderProcessor extends RecursiveTask<List<String>> {

    private final String folderPath;

    FolderProcessor(String folderPath) {
        this.folderPath = folderPath;
    }

    @Override
    protected List<String> compute() {
        final List<String> allFileNames = new ArrayList<String>();
        final List<FolderProcessor> subTasks = new ArrayList<FolderProcessor>();

        final File file = new File(folderPath);
        final File[] children = file.listFiles();

        if (children != null && children.length > 0) {
            for (final File child: children) {
                if (child.isDirectory()) {
                    final FolderProcessor subTask = new FolderProcessor(child.getAbsolutePath());
                    subTask.fork();
                    subTasks.add(subTask);
                } else {
                    allFileNames.add(child.getAbsolutePath());
                }
            }
        }

        for (final FolderProcessor subTask: subTasks) {
            allFileNames.addAll(subTask.join());
        }

        System.out.println(Thread.currentThread().getName() + " " + allFileNames);
        return allFileNames;
    }

}
