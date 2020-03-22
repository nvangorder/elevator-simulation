
/*
 * This is the stack class which will be used to 
 * simulate the passengers within the elevator
 */
public class Stack 
{
  private int top = -1;
  private int capacity = 5;
  private Passenger[] elevatorStack = new Passenger[capacity];
  
  //boolean returns if the stack is empty
  public boolean isEmpty()
  {
    if(top < 0)
    {
      return true;
    }
    else
    {
      return false;
    }
  }
  
  //returns true if the elevator is full
  public boolean isfull()
  {
    if(elevatorStack.length-1 == top)
    {
      return true;
    }
    else
    {
      return false;
    }
  }
  
  //places a variable into the stack
  public void push(Passenger passanger)
  {
    if(top >= 4)
    {
    }
    else
    {
      top = top+1;
      elevatorStack[top] = passanger;
    }
  }
  
  //removes a variable from the stack
  public Passenger pop()
  {
    Passenger holderPassenger = elevatorStack[top];
    top = top-1; 
    return holderPassenger;
  }
  
  //returns the top variable from the stack
  public Passenger peek()
  {
    return elevatorStack[top];
  }
  
  public int getTop()
  {
    return top;
  }
}
