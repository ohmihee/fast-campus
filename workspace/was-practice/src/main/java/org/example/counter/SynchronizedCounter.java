package org.example.counter;

public class SynchronizedCounter implements Runnable{
    private int count;
    public void increment() {
        count++;
    }
    public void decrement(){
        count--;
    }
    public int getValue(){
        return count;
    }
    @Override
    public void run() {
        synchronized (this) {
            this.increment();
            System.out.println("Value for Thread After increment"+ Thread.currentThread().getName() + " " + this.getValue() );
            this.decrement();
            System.out.println("Value for Thread at last " +Thread.currentThread().getName() + " " +this.getValue());
        }
    }
}
