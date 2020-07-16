import java.util.ArrayList;

public class Cell
{
	//This class represents a cell in the maze
    private final int x; //horizontal grid location of cell in the maze
    private final int y; //vertical grid location of cell in the maze
    
    private boolean isWall = false; //true if the cell is a wall
    
    private ArrayList<Cell> neighbours = new ArrayList<Cell> (); //all the neigbouring cells
    
    private Maze maze; //parent maze this cell belongs to
    
    //variables used during path finding
    private boolean visited = false; //used during recursion to show cell has been processed
    private boolean inRoute = false; //indicates if this cell is on the path or not

    public Cell (int x, int y, Maze maze)
    {
        this.maze = maze;
        this.x = x;
        this.y = y;
    }

    public void setNeighbours ()
    {
    	//set the neighbour cells of this cell
        neighbours = new ArrayList<Cell> ();

        if (x != 0)
        {
            addNeighbour (x-1, y);
        }

        if (y != 0)
        {
            addNeighbour (x, y-1);
        }

        if (x < maze.getColumns () - 1)
        {
            addNeighbour (x+1, y);
        }

        if (y < maze.getRows () - 1)
        {
            addNeighbour (x, y+1);
        }
    }

    private void addNeighbour (int x, int y)
    {
        Cell neighbour = maze.getCell (x, y);
        if (!neighbour.isWall ())
        {
            neighbours.add (neighbour);
        }
    }

    public void reset ()
    {
    	//clear the maze path finding variables so a new path can be found
        neighbours = new ArrayList<Cell> ();
        visited = false;
        inRoute = false;
    }

    public boolean isWall ()
    {
        return isWall;
    }

    public void makeWall ()
    {
        if (this.equals (maze.getStart ()))
        {
            return;
        }

        if (this.equals (maze.getFinish ()))
        {
            return;
        }

        isWall = true;
    }

    public void toggleWall ()
    {
        if (this.equals (maze.getStart ()))
        {
            return;
        }

        if (this.equals (maze.getFinish ()))
        {
            return;
        }

        isWall = !isWall;
    }

    public void breakWall ()
    {
        isWall = false;
    }

    public int getX ()
    {
        return x;
    }

    public int getY ()
    {
        return y;
    }

    public boolean isVisited ()
    {
        return visited;
    }

    public void visited ()
    {
    	//set the cell as visited, or processed
        visited = true;
    }

    public Cell getAnUnvisitedNeighbour ()
    {
    	//This methood returns one of the unvisited neigbours of this cell
    	//if there is one.
    	//This method is useful for recursively searching the maze 
    	
        if (neighbours == null)
        {
            return null;
        }

        if (neighbours.size () == 0)
        {
            return null;
        }

        for (int i = 0; i < neighbours.size (); i++)
        {
            Cell next = (Cell) neighbours.get (i);
            if (!next.isVisited ())
            {
                return next;
            }
        }

        return null;
    }

    
    public boolean inRoute ()
    {
    	//answer whether this cell in on a path from the start of finish cell
        return inRoute;
    }

    public void setInRoute()
    {
    	//set this cell to be on the path from the start cell to the finish cell
        inRoute = true;
    }
 

}