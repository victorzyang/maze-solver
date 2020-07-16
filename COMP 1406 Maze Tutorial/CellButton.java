import java.awt.Color;
import java.awt.Insets;

import javax.swing.JButton;

public class CellButton extends JButton
{
    Cell cell;
    Maze maze;

    public CellButton (Cell cell, Maze maze)
    {
        this.cell = cell;
        this.maze = maze;
        setMargin (new Insets (0, 0, 0, 0));
    }

    public Cell getCell ()
    {
        return cell;
    }

    public void update ()
    {
    	
    	/*
        if (cell.getID () != -1)
        {
            setText (cell.getID () + "");
        }
        else
        {
            setText ("");
        }
        */
        setText("");
        if (cell.isWall ())
        {
            setBackground (Color.black);
            return;
        }
        else if (cell.equals (maze.getStart()))
        {
            setBackground (Color.red);
            return;
        }
        else if (cell.equals (maze.getFinish()))
        {
            setBackground (Color.blue);
            return;
        }
        else if (cell.inRoute ())
        {
            setBackground (Color.green);
            return;
        }
        else
        {
            setBackground (Color.pink);
            return;
        }
    }
}