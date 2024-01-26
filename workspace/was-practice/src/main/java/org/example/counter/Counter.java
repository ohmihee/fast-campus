package org.example.counter;

import java.sql.SQLOutput;

public class Counter implements Runnable{
    // 잘못된 예시
    // 상태를 갖는 경우
    private int count;
    public void increment() {
        count++;
    };
    public void decrement(){
        count--;
    }
    public int getValue() {
        return count;
    }
    @Override
    public void run() {
        this.increment();
        System.out.println("Value for Thread After increment"+ Thread.currentThread().getName() + " " + this.getValue() ); // 기대값 : 1
        this.decrement();
        System.out.println("Value for Thread at last " +Thread.currentThread().getName() + " " +this.getValue()); // 기대값 : 0

    }
}
