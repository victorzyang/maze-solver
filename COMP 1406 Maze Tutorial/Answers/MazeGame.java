import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class CellListener implements ActionListener
{
    private Maze maze;
    private MazeGameView view;

    public CellListener (Maze maze, MazeGameView view)
    {
        this.maze = maze;
        this.view = view;
    }

    public void actionPerformed (ActionEvent event)
    {
        CellButton button = (CellButton) event.getSource ();
        button.getCell ().toggleWall ();
        maze.reset ();
        view.getStatus ().setText ("Press \"Solve\" to solve the maze.");
        view.enableSolveButton ();
        view.update ();
    }
}



class ButtonListener implements ActionListener
{
    private Maze maze;
    private MazeGameView view;

    public ButtonListener (Maze maze, MazeGameView view)
    {
        this.maze = maze;
        this.view = view;
    }

    public void actionPerformed (ActionEvent event)
    {
        view.disableSolveButton ();
        maze.reset (); //clear any visited settings
        maze.setNeighbours (); //determine neighbours of each cell
        maze.solve (); //solve the maze
        view.update (); //redraw the maze with the path if one was found
        
    }
}

public class MazeGame extends JFrame
{
    private Maze maze; // this is the model
    private MazeGameView view;
    private ActionListener cellListener = null;
    private ActionListener buttonListener = null;

    public MazeGame (Maze maze)
    {
        this.maze = maze;
        view = new MazeGameView (maze);
    }

    public Maze getModel ()
    {
        return maze;
    }

    public MazeGameView getView ()
    {
        return view;
    }

    public ActionListener getCellListener ()
    {
        if (cellListener == null)
        {
            cellListener = new CellListener (maze, getView ());
        }

        return cellListener;
    }


    public ActionListener getButtonListener ()
    {
        if (buttonListener == null)
        {
            buttonListener = new ButtonListener (maze, getView ());
        }

        return buttonListener;
    }

    public void setListeners ()
    {
        view.setCellListener (getCellListener ());
        view.setButtonListener (getButtonListener ());
    }

    public static void main (String [] args)
    {
        JFrame frame = new JFrame ("Maze Game");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        MazeGame controller = new MazeGame (new Maze (15, 15));
        controller.setListeners ();
        frame.getContentPane ().add (controller.getView ());
        frame.setSize (640, 480);
        frame.setVisible (true);
    }
}