/*
 * this class will keep track of what direction the elevator
 * is going, and what floor it is currently on
 */

public class Elevator 
{
  private int direction = 1;
  private int floor = 1;
  private int nextFloor = 1;
  private int empty = 0;
  private int full = 0;
  
  //get-add methods for full
  public void fullElevator()
  {
    full++;
  }
  public int getFull()
  {
    return full;
  }
  
  //get-add methods for empty
  public void emptyElevator()
  {
    empty++;
  }
  public int getEmpty()
  {
    return empty;
  }
  
  //changedirection-get method for direction
  public void changeDirection()
  {
    if(direction == 1)
    {
      direction = direction-1;
    }
    else
    {
      direction = direction+1;
    }
  }
  public int getDirection()
  {
    return direction;
  }
  
  //set-get method for floor
  public void setFloor(int floor)
  {
    this.floor = floor;
  }
  public int getFloor()
  {
    return this.floor;
  }
  
  
  //this method will decide what floor to go to next
  public void setNextFloor(int proposedFloor)
  {
//    System.out.println(proposedFloor+" compared to exiting "+floor);
    if(nextFloor == floor && direction == 1 && proposedFloor > floor)
    {
      nextFloor = proposedFloor;
    }
    if(nextFloor == floor && direction == 0 && proposedFloor < floor)
    {
      nextFloor = proposedFloor;
    }
    if(proposedFloor > floor && direction == 1 && proposedFloor < nextFloor)
    {
      nextFloor = proposedFloor;
    }
    if(proposedFloor < floor && direction == 0 && proposedFloor > nextFloor)
    {
      nextFloor = proposedFloor;
    }
  }
  public int getNextFloor()
  {
    return this.nextFloor;
  }
}
