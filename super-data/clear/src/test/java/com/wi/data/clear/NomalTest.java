package com.wi.data.clear;

import java.util.Random;

public class NomalTest {

    private static   int stop=10;

    public static void main(String[] args){

    }


    public static int getSleepTime(int max){
        Random rand = new Random();
        return rand.nextInt(max) + 1;

    }
}
