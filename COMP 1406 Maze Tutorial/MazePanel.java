import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class MazePanel extends JPanel
{
    private CellButton [][] buttons;
    private Maze maze;
    private ActionListener listener;

    public MazePanel (Maze maze)
    {
        this.maze = maze;
        int rows = maze.getRows ();
        int columns = maze.getColumns ();
        buttons = new CellButton [columns][rows];

        GridLayout layout = new GridLayout (rows, columns, 0, 0);
        setLayout (layout);

        for (int j = 0; j < rows; j++)
        {
            for (int i = 0; i < columns; i++)
            {
                buttons [i][j] = new CellButton (maze.getCell (i, j), maze);
                add (buttons [i][j]);
            }
        }
    }

    public void setListener (ActionListener listener)
    {
        this.listener = listener;
        int rows = maze.getRows ();
        int columns = maze.getColumns ();

        for (int i = 0; i < columns; i++)
        {
            for (int j = 0; j < rows; j++)
            {
                buttons [i][j].addActionListener (listener);
            }
        }
    }

    public void enableCellListener ()
    {
        int rows = maze.getRows ();
        int columns = maze.getColumns ();

        for (int i = 0; i < columns; i++)
        {
            for (int j = 0; j < rows; j++)
            {
                buttons [i][j].addActionListener (listener);
            }
        }
    }

    public void disableCellListener ()
    {
        int rows = maze.getRows ();
        int columns = maze.getColumns ();

        for (int i = 0; i < columns; i++)
        {
            for (int j = 0; j < rows; j++)
            {
                buttons [i][j].removeActionListener (listener);
            }
        }
    }

    public void update ()
    {
        for (int i = 0; i < maze.getColumns (); i++)
        {
            for (int j = 0; j < maze.getRows (); j++)
            {
                buttons [i][j].update ();
            }
        }
    }
}