package com.comp2601.assignment2;

import android.graphics.Color;
import android.util.Log;
import android.widget.Button;

import java.util.Random;

public class ButtonCell extends Thread{

    private static final String TAG =  MainActivity.class.getSimpleName();

    Button[][] buttons;
    String[][] buttonCells;
    int numRows;
    int numCols;
    int current_col;
    int current_row;
    boolean destinationReached;
    MainActivity mainActivity;

    boolean[][] visited; //determines if a button cell has been visited or not

    private Random mRandom = new Random(); //needed for experiments

    ButtonCell(Button[][] buttons, String[][] buttonCells, int numRows, int numCols, int startX, int startY, MainActivity mainActivity){ //constructor
        this.numRows = numRows;
        this.numCols = numCols;
        this.current_col = startX;
        this.current_row = startY;
        this.buttons = new Button[numRows][numCols];
        this.buttons = buttons;
        this.buttonCells = new String[numRows][numCols];
        this.buttonCells = buttonCells;

        this.visited = new boolean[numRows][numCols];
        for(int r = 0; r < numRows; r++){
            for(int c = 0; c < numCols; c++){
                visited[r][c] = false; //initialize everything in the visited array to false
            }
        }
        this.destinationReached = false; //determines if a possible path exists or not

        this.mainActivity = mainActivity;
    }

    private boolean isValid(int row, int column) { //checks if location is valid
        if(row<0 || column<0 || row>=numRows || column>=numCols) {
            return false;
        }
        return true;
    }

    //have a recursive method
    public void solveRecursiveHelper(int row, int column){
        if(destinationReached == true){
            return;
        }else if(visited[row][column]==true || buttonCells[row][column]=="wall"){ //don't continue a search path on an already visited cell or a wall cell
            return;
        }else if(buttonCells[row][column]=="destination"){ //if destination is reached
            destinationReached = true; //end the search path once destination is reached
            Log.i(TAG, "Destination reached");
            return;
        }else{
            Log.i(TAG, "Now on cell: row " + row + ", column " + column);

            Log.i(TAG, "Current row is " + current_row + " and current column is " + current_col);

            visited[row][column] = true; //mark current cell as visited

            Log.i(TAG, "Marked cell row " + row + ", column " + column + " as visited");

            if(isValid(row-1, column)==true && visited[row-1][column]==false && buttonCells[row-1][column]!="wall" && destinationReached==false){ //first checks the cell above
                if(buttonCells[row-1][column] == "empty"){
                    updateUI(row-1, column);
                }
                delay();
                solveRecursiveHelper(row-1, column);
            }

            if(isValid(row+1, column)==true && visited[row+1][column]==false && buttonCells[row+1][column]!="wall" && destinationReached==false){ //then check the cell below
                if(buttonCells[row+1][column] == "empty"){
                    updateUI(row+1, column);
                }
                delay();
                solveRecursiveHelper(row+1, column);
            }

            if(isValid(row, column-1)==true && visited[row][column-1]==false && buttonCells[row][column-1]!="wall" && destinationReached==false){ //then check the cell to the left
                if(buttonCells[row][column-1] == "empty"){
                    updateUI(row, column-1);
                }
                delay();
                solveRecursiveHelper(row, column-1);
            }

            if(isValid(row, column+1)==true && visited[row][column+1]==false && buttonCells[row][column+1]!="wall" && destinationReached==false){ //then check the cell to the right
                if(buttonCells[row][column+1] == "empty"){
                    updateUI(row, column+1);
                }
                delay();
                solveRecursiveHelper(row, column+1);
            }

        }
    }

    private void updateUI(final int row, final int column){
        //update the UI on main thread
        mainActivity.runOnUiThread(new Runnable(){
            @Override
            public void run(){
                Log.i(TAG, "... Running main thread ...");
                if(mainActivity.buttonCells[row][column]!="start"){
                    mainActivity.buttons[row][column].setBackgroundColor(Color.YELLOW); //change the color of a visited cell to yellow
                    Log.i(TAG, "Cell row " + row + ", column " + column + " displayed as visited");
                }
            }
        });
    }

    private void delay(){ //causes the current thread to "sleep"
        try{
            Thread.sleep(mRandom.nextInt(90)+10); //delay time is really small like this; we can change it to delay for longer periods of time
        }catch (java.lang.InterruptedException e){

        }
    }
}
