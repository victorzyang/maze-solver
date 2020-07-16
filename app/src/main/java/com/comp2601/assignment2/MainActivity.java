package com.comp2601.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import androidx.core.content.ContextCompat;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG =  MainActivity.class.getSimpleName();

    // Scale of the game (number of rows and columns)
    private  static final int NUM_ROWS = 13;
    private  static final int NUM_COLS = 10;

    private  Button solveMazeButton;

    Button buttons[][] = new Button[NUM_ROWS][NUM_COLS]; //add buttons to this array

    //have another double array of ints to determine whether buttons should become start, destination, wall or blank cells
    String buttonCells[][] = new String[NUM_ROWS][NUM_COLS];

    //coordinates of the starting cell
    int startX = 0;
    int startY = 0;

    Button startButton;

    boolean startCellSelected = false;
    boolean destinationCellSelected = false;

    ThreadWithControl mThread; //ThreadWithControl object, this is a thread

    ButtonCell buttonCell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Adding buttons with UI Threads
        TableLayout gameLayout = (TableLayout) findViewById(R.id.gameTable); //layout of the maze

        for(int r = 0; r < NUM_ROWS; r++){

            TableRow tableRow = new TableRow(MainActivity.this); //create a new table row
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f
            ));
            gameLayout.addView(tableRow); //add table row to gameLayout

            for(int c = 0; c < NUM_COLS; c++){ //add a new button in each column of the row
                /* If I wanted to add more cells per row, I would increase
                the number of iterations here to the desired amount of cells.

                For example, if I wanted to add two more cells, I would change the for loop
                range to NUM_COLS+2 */

                final Button button = new Button(MainActivity.this); //create a new button

                button.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.empty));
                button.setText("E"); //initialize each button to empty

                final int finalC = c;
                final int finalR = r;

                button.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        if(startCellSelected == false){ //if no start cell has been selected yet
                            if(buttonCells[finalR][finalC] == "destination"){ //if clicked cell is the destination cell
                                button.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.empty));
                                button.setText("E");
                                destinationCellSelected = false;
                                buttonCells[finalR][finalC] = "empty"; //set the cell to an empty cell
                            }else{
                                button.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.start));
                                button.setText("S");
                                startCellSelected = true;
                                buttonCells[finalR][finalC] = "start"; //set the cell to the start cell
                                startX = finalR;
                                startY = finalC;
                                startButton = buttons[finalR][finalC];
                            }
                        }else if(destinationCellSelected == false){ //if no destination cell has been selected yet
                            if(buttonCells[finalR][finalC] == "start"){ //if clicked cell is the start cell
                                button.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.empty));
                                button.setText("E");
                                startCellSelected = false;
                                buttonCells[finalR][finalC] = "empty"; //set the cell to an empty cell
                            }else{
                                button.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.destination));
                                button.setText("D");
                                destinationCellSelected = true;
                                buttonCells[finalR][finalC] = "destination"; //set the cell to the destination cell
                            }
                        }else{
                            if(buttonCells[finalR][finalC] == "empty"){ //if clicked cell is an empty cell
                                button.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.wall));
                                button.setText("W");
                                buttonCells[finalR][finalC] = "wall"; //set the cell to a wall cell
                            }else{
                                if(buttonCells[finalR][finalC] == "start"){ //if clicked cell is the start cell
                                    startCellSelected = false;
                                }else if(buttonCells[finalR][finalC] == "destination"){ //if clicked cell is the destination cell
                                    destinationCellSelected = false;
                                }

                                button.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.empty));
                                button.setText("E");
                                buttonCells[finalR][finalC] = "empty"; //set the cell to an empty cell
                            }
                        }

                    }
                });

                TableRow.LayoutParams params = new TableRow.LayoutParams(
                        TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.MATCH_PARENT,
                        1.0f
                );

                params.setMargins(4,4,4,4);
                button.setLayoutParams(params);
                button.setPadding(2,2,2,2);

                tableRow.addView(button); //adds button to table row
                buttons[r][c] = button; //adds button to buttons array
                buttonCells[r][c] = "empty"; //initialize each button in array to empty
            }

        }

        solveMazeButton = (Button) findViewById(R.id.button_solve_maze);
        solveMazeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Solve Button clicked");

                for(int r = 0; r < NUM_ROWS; r++){
                    for(int c = 0; c < NUM_COLS; c++){
                        buttons[r][c].setEnabled(false); //disables all buttons
                    }
                }

                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR); //disables screen orientation

                buttonCell = new ButtonCell(buttons, buttonCells, NUM_ROWS, NUM_COLS, startX, startY, MainActivity.this);

                Log.i(TAG, "Starting X is " + startX + " and starting Y is " + startY);

                //pass the two arrays to ThreadWithControl
                mThread = new ThreadWithControl(buttonCell, startX, startY);

                mThread.start(); //'start()' is a built in Thread method

            }
        });

    }
}
