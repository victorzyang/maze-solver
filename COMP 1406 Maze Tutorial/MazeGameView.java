import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MazeGameView extends JPanel
{
    private Maze maze;
    private MazePanel mazePanel;
    private JButton solveButton = new JButton ("Solve");
    private JTextField status = new JTextField ("Press \"Solve\" to solve the maze.");
    private ActionListener buttonListener;

    public MazeGameView (int rows, int columns)
    {
        this (new Maze (rows, columns));
    }

    public MazeGameView (Maze maze)
    {
        this.maze = maze;
        mazePanel = new MazePanel (maze);
        initialize ();
    }

    public void initialize ()
    {
        JPanel buttonPanel = new JPanel ();
        setLayout (new BorderLayout ());
        add (BorderLayout.CENTER, mazePanel);
        add (BorderLayout.EAST, buttonPanel);
        add (BorderLayout.SOUTH, status);
        status.setEditable (false);
        buttonPanel.setLayout (new BoxLayout (buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add (solveButton);
        update ();
    }

    public void setCellListener (ActionListener cellListener)
    {
        mazePanel.setListener (cellListener);
    }

    public void enableCellListener ()
    {
        mazePanel.enableCellListener ();
    }

    public void disableCellListener ()
    {
        mazePanel.disableCellListener ();
    }





    public JTextField getStatus ()
    {
        return status;
    }

    public void setButtonListener (ActionListener buttonListener)
    {
        this.buttonListener = buttonListener;
        solveButton.addActionListener (buttonListener);
    }

    public void enableButtonListener ()
    {
        solveButton.addActionListener (buttonListener);
    }

    public void disableButtonListener ()
    {
        solveButton.removeActionListener (buttonListener);
    }

    public void enableListeners ()
    {
        enableCellListener ();
        enableButtonListener ();
    }

    public void disableListeners ()
    {
        disableCellListener ();
        disableButtonListener ();
    }

    public void enableSolveButton ()
    {
        solveButton.setEnabled (true);
    }

    public void disableSolveButton ()
    {
        solveButton.setEnabled (false);
    }

    public void update ()
    {
        disableListeners ();
        mazePanel.update ();
        enableListeners ();
    }
}