/*
 * This class will act as a queue, that will
 * hold employees who are currently waiting to get
 * on the elevator
 */
public class Queue 
{
  private Passenger[] queue;
  private int front = 0;
  
  //returns the size of the queue
  public int size()
  {
    return queue.length;
  }
  
  //returns the front index
  public int getFront()
  {
    return this.front;
  }
  public void addFront()
  {
    front++;
  }
  
  public Passenger getPassenger(int index)
  {
    return queue[index];
  }
  
  //this method returns the front element of the queue
  //and then moves the front to the next element
  public Passenger pop()
  {
    Passenger holder = queue[front];
    front=front+1;
    return holder;
  }
  
  //this method returns the front element of the queue
  public Passenger peek()
  {
    return queue[front];
  }
  
  //this method will set the queue equal to a passenger array
  public void setQueue(Passenger[] passenger)
  {
    this.queue = passenger;
  }
}


