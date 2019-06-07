package com.example.util;

public class RandomNumber {
    public static int getNumber(){
        return (int) ((Math.random() * 9 + 1) * 100000);
    }
}
