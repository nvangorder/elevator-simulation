/*
 * This class will be used to store information pertaining to
 * the elevator passengers
 */

public class Passenger 
{
  private String name;
  private int entryFloor;
  private int exitFloor;
  private int tookStairs = 0;
  private int tempExits = 0;
  
  //get-set methods for name
  public void setName(String name)
  {
    this.name = name;
  }
  public String getName()
  {
    return this.name;
  }
  
  //get-set methods for entryfloor
  public void setEntryFloor(int floor)
  {
    this.entryFloor = floor;
  }
  public int getEntryFloor()
  {
    return this.entryFloor;
  }
  
  //get-set methods for exitfloor
  public void setExitFloor(int floor)
  {
    this.exitFloor = floor;
  }
  public int getExitFloor()
  {
    return this.exitFloor;
  }
  
  //get-set methods for tookStairs
  public void setTookStairs()
  {
    this.tookStairs = 1;
  }
  public int getTookStairs()
  {
    return this.tookStairs;
  }
  
  //adjusting tempExit count and get method for tempExit count
  public void additionalTempExit()
  {
    this.tempExits = tempExits+1;
  }
  public int getTempExit()
  {
    return this.tempExits;
  }
  
}
