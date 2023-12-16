package com.Daineka.multithreading;

public class WaitAndNotifyTest {
    public static void main(String[] args) {
        WaitAndNotify waitAndNotify = new WaitAndNotify();

        Thread thread1 = new Thread(waitAndNotify::produce);
        Thread thread2 = new Thread(waitAndNotify::consume);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class WaitAndNotify {
    private boolean run = true;

    public void produce() {
        synchronized (this) {
            try {
                for (int i = 0; i < 10; i++) {
                    while (!run) {
                        wait();
                    }
                    System.out.print("Hello ");
                    run = false;
                    notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void consume() {
        try {
            synchronized (this) {
                for (int i = 0; i < 10; i++) {
                    while (run) {
                        wait();
                    }
                    System.out.println("world!");
                    run = true;
                    notify();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

