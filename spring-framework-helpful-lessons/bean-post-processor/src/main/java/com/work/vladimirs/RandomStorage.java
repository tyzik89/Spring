package com.work.vladimirs;

public class RandomStorage {

    @InjectRandomInt(min = 2, max = 7)
    private int repeat;

    public void doRepeat() {
        for (int i = 0; i < repeat; i++) {
            System.out.println("Repeat #" + i);
        }
    }
}
