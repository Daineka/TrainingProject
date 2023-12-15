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
            throw new RuntimeException(e);
        }
    }
}

class WaitAndNotify{
    public void produce(){
        synchronized (this){
            try {
                for (int i = 0; i < 1000; i++) {
                    System.out.print("Hello ");
                    wait();
                    notify();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void consume(){
        try {
            synchronized (this){
                Thread.sleep(10);

                for (int i = 0; i < 1000; i++) {
                    System.out.println("world!");
                    notify();
                    wait();
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

