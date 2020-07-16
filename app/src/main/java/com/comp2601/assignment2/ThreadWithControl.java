package com.comp2601.assignment2;

import android.util.Log;

public class ThreadWithControl extends Thread{ //ThreadWithControl inherits from Thread class which allows use for built in methods

    private static final String TAG =  MainActivity.class.getSimpleName();

    ButtonCell buttonCell;
    int startX, startY;

    public ThreadWithControl(ButtonCell buttonCell, int startX, int startY){

        this.startX = startX;
        this.startY = startY;
        this.buttonCell = buttonCell;

        Log.i(TAG, "Everything is initialized in Thread class");
    }

    public void run() {

        Log.i(TAG, "Now calling recursive helper");

        buttonCell.solveRecursiveHelper(startX, startY); //thread calls the recursive solver method

    }

}
