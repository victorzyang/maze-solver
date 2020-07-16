public class Maze
{
    private int rows;
    private int columns;

    private Cell [][] cells; //double array of cell that make up the maze
    private Cell start; //cell that represents the start location
    private Cell finish; //cell that represents the finish or destination in the maze

    public Maze (int columns, int rows)
    {
    	//build the maze
        this.rows = rows;
        this.columns = columns;

        cells = new Cell [columns][rows];

        for (int i = 0; i < columns; i++)
        {
            for (int j = 0; j < rows; j++)
            {
                cells [i][j] = new Cell (i, j, this);
            }
        }

        start = getCell (0, 0);
        finish = getCell (columns - 1, rows - 1);

        initialize ();
    }

    public void initialize ()
    {
    	//set some of the cell locations to be walls
        for (int i = 0; i < getColumns (); i++)
        {
            for (int j = 0; j < getRows (); j++)
            {
                if (Math.random () < 0.3)
                {
                    getCell (i, j).makeWall ();
                }
                else
                {
                    getCell (i, j).breakWall ();
                }
            }
        }
    }

    public void reset ()
    {
    	//clear the maze board
        for (int i = 0; i < columns; i++)
        {
            for (int j = 0; j < rows; j++)
            {
                cells [i][j].reset ();
            }
        }
    }

    public void setNeighbours ()
    {
        for (int i = 0; i < columns; i++)
        {
            for (int j = 0; j < rows; j++)
            {
                cells [i][j].setNeighbours ();
            }
        }
    }

    public int getColumns ()
    {
        return columns;
    }

    public int getRows ()
    {
        return rows;
    }

    public Cell getCell (int x, int y)
    {
        if ((x < 0) || (y < 0))
        {
            return null;
        }

        if ((x >= columns) || (y >= rows))
        {
            return null;
        }

        return cells [x][y];
    }

    public Cell getStart ()
    {
    	//answer the starting cell of the maze
        return start;
    }

    public Cell getFinish ()
    {
    	//answer the finish, or destination, cell of the maze
        return finish;
    }

    public boolean solve ()
    {
       //Solve the maze by finding a path from the start cell  to the finish cell using the
       //recursive findPathFrom(Cell aCell) method
       
    	   return findPathFrom (start);
    }

    public boolean findPathFrom (Cell aCell)
    {
    	
    	//Recursively determine if we can reach the finish cell from cell aCell
    	//This method answers whether a route was found, and sets the cells along the
    	//route, but invoking their setInRoute() method, so the route will be shown (in green)
    	//when the maze is drawn
    	
         aCell.visited(); //mark aCell as being visisted (so the problem will get smaller)

        //Basis Case
        //if aCell is the finish cell set it to be in the route and return true (we are done)
        
        //MISSING CODE --basis case




       //END BASIS CASE
        
        //Recursion
          
          Cell next = aCell.getAnUnvisitedNeighbour(); //get an unvisited neighbour of aCell
      
        //Check each of aCell's unvisited neigbours. If you find a neighbour n for which
        //findPathFrom(n) is true, set the neighbour to be in the route and return true (we are done).
        //otherwise check the remaining unvisited neighbours.
        
        //If no unvisited neighbour can be found that allows a path, return false ---there is no path
        //possible.
        
        //MISSING CODE ----recursive case
 
 
 
 
 
 
 
         
        //END ---recursive case


        return false;
        
    }


}