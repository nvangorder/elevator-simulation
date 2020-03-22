import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/*
 * This program will take an input file and use it to simulate an
 * workplace using an elevator throughout the course of a day.
 */

public class ElevatorSimulation 
{
  public static void main(String[] args)
  {
    
    //user file inputs
    System.out.println("File Location: ");
    Scanner input = new Scanner(System.in);
    
    
    String filelocation = input.nextLine();
    
    //initializing the queues and stacks that will be used to manipulate the passengers
    Queue elevatorQueue = new Queue();
    elevatorQueue.setQueue(ingestFile(filelocation));
    Stack elevatorStack = new Stack();
    Elevator elevator = new Elevator();   
    fillStack(elevatorQueue, elevatorStack, elevator);

    //this section was used to simulate a second elevator
//    Stack elevatorTwoStack = new Stack();
//    Elevator elevatorTwo = new Elevator();
//    elevatorTwo.setFloor(5);
//    fillStack(elevatorQueue, elevatorTwoStack, elevatorTwo);
    
    while(elevatorQueue.getFront() <= elevatorQueue.size())
    {
      moveElevator(elevatorQueue, elevatorStack, elevator);
//      moveElevator(elevatorQueue, elevatorTwoStack, elevatorTwo);
    } 
    totals(elevatorQueue, elevator);
    
  }
  
  public static void totals(Queue elevatorQueue, Elevator elevator)
  {
    int tookStairs = 0;
    
    for(int person = 0 ; person < elevatorQueue.size() ; person++)
    {
      tookStairs = tookStairs + elevatorQueue.getPassenger(person).getTookStairs();
    }
    int totalPassengers = elevatorQueue.size()-tookStairs;
    System.out.println("----------------------------------------------------------");
    System.out.println(totalPassengers+" total Passengers");
    System.out.println(tookStairs+" people took stairs");
    System.out.println(elevator.getEmpty()+" times the elevator was empty");
    System.out.println(elevator.getFull()+" times the elevator was full");
    
  }
  
  //this method will be used to move the elevator to a new floor and recycle it's passengers
  public static void moveElevator(Queue elevatorQueue, Stack elevatorStack, Elevator elevator)
  {
    //moves the elevator to the next floor
    elevator.setFloor(elevator.getNextFloor());
    //cycles people out and then back onto the elevator if this isn't their floor
    recycleElevator(elevatorQueue, elevatorStack, elevator);
    //fills any available space in the elevator with people waiting on the floor
    fillStack(elevatorQueue, elevatorStack, elevator);
    
    //this if statement is used to change direction if the elevator needs to turn around
    if(elevator.getFloor() == elevator.getNextFloor())
    {
      //changes direction
      elevator.changeDirection();
      //cycles people out and then back into the elevator if this isn't their floor
      recycleElevator(elevatorQueue, elevatorStack, elevator);
      //rechecks floor, as the direction has changed
      elevator.setFloor(elevator.getNextFloor());
      //one last cycle of people to ensure we're heading to the right floor
      recycleElevator(elevatorQueue, elevatorStack, elevator);
      //fills any available space in the elevator with people waiting on the floor
      fillStack(elevatorQueue, elevatorStack, elevator);
    }
    if(elevatorStack.getTop() == -1)
    {
      System.out.println("The Elevator is empty.");
      elevator.emptyElevator();
    }
    
  }
  
  //this method will move the elevator to the next level, remove people from the 
  //elevator and refill the elevator
  public static void recycleElevator(Queue elevatorQueue, Stack elevatorStack, Elevator elevator)
  {
    //to method variables that will be used to hold the passengers
    //and identify the stack size
    int stackSize = elevatorStack.getTop();
    int pushedPassengers = 0;
    Stack temperaryStack = new Stack();
    
    //for loop that removes people from the elevator stack to the holder stack
    for(int person = 0 ; person <= stackSize ; person++)
    {
      System.out.println(elevatorStack.peek().getName()+" temperarily exited the elevator.");
      temperaryStack.push(elevatorStack.pop());
      pushedPassengers++;
    }
    
    //for loop that checks if this is the persons floor, and either replaces them
    //or removes them from the elevator
    for(int person = 0 ; person < pushedPassengers ; person++)
    {
      if(temperaryStack.peek().getExitFloor() != elevator.getFloor())
      {
        //tracks how many temperary exits the person has had
        temperaryStack.peek().additionalTempExit();
        System.out.println(temperaryStack.peek().getName()+" entered the elevator.");
        elevatorStack.push(temperaryStack.pop());
        elevator.setNextFloor(elevatorStack.peek().getExitFloor());
      }
      else
      {
        System.out.println
        (
          temperaryStack.peek().getName()+" exited the elevator."
          +" Temperary exits: "+temperaryStack.peek().getTempExit()
        );
        temperaryStack.pop();
      }
    }
    
    try 
    {
      elevator.setNextFloor(elevatorQueue.peek().getEntryFloor());  
    }
    catch(Exception except)
    {
      
    }
  }
  
  //this method will take people who are in queue and put them into the elevator
  public static void fillStack(Queue elevatorQueue, Stack elevatorStack, Elevator elevator)
  { 
    
    //an if statement to avoid an array out of bounds exception
    if(elevatorQueue.getFront() <= elevatorQueue.size()-1)
    {
      //this while loop keeps adding people onto the elevator until it's full
      while(elevatorQueue.peek().getEntryFloor() == elevator.getFloor())
      {
        //forces people to take the stairs if the elevator is full
        if(elevatorStack.isfull() == true)
        {
          System.out.println("The elevator is full.");
          elevator.fullElevator();
          System.out.println(elevatorQueue.peek().getName()+" had to take the stairs.");
          elevatorQueue.peek().setTookStairs();
          elevatorQueue.pop();
        }
        else
        {
          System.out.println(elevatorQueue.peek().getName()+" entered the elevator."
          +" They plan on exiting on floor: "+elevatorQueue.peek().getExitFloor());
          elevator.setNextFloor(elevatorQueue.peek().getExitFloor());
          elevatorStack.push(elevatorQueue.pop()); 
        }
        if(elevatorQueue.getFront() >= elevatorQueue.size()-1)
        {
          break;
        }
      }
    }
    else
    {
      //this last add front closes out the initial while loop, basically shutting everything down
      elevatorQueue.addFront();
    }
  } 
  
  //This method returns the number of rows in the file
  public static int getRows(String filelocation)
  {
    int lines = 0;
    
    //try block to cycle through the file and find the number of lines present
    try 
    {
      //open file
      FileReader filein = new FileReader(filelocation);     
      BufferedReader bufferin = new BufferedReader(filein);
      //cycle through file
      while(bufferin.readLine() != null) lines++;
      //close file
      bufferin.close();
      filein.close();
      throw new IOException("Error retrieving row count");
    }
    catch(IOException except)
    {
      except.getMessage();
    }
    return lines;
  }
  
  //this method cycles through the file and pulls each row into the Passenger array
  public static Passenger[] ingestFile(String filelocation)
  {
    
    Passenger[] passengerList = new Passenger[getRows(filelocation)];
    //try block sorting through the file to create a string array
    try 
    {
      //opens file
      FileReader filein = new FileReader(filelocation);     
      BufferedReader bufferin = new BufferedReader(filein);
      int linenumber = 0;
      String stringline;
      //loops through the file
      while((stringline = bufferin.readLine()) != null)
      {
        //creating a holder passenger who will have it's variables filled using the string
        Passenger holderPassenger = new Passenger();
        holderPassenger.setName(stringline.substring(0, stringline.length()-4).trim());
        holderPassenger.setEntryFloor
        (Integer.parseInt(stringline.substring(stringline.length()-3, stringline.length()-2).trim()));
        holderPassenger.setExitFloor(Integer.parseInt(stringline.substring(stringline.length()-2).trim()));
        passengerList[linenumber] = holderPassenger;
        linenumber++;
      }
      //closes file
      bufferin.close();
      filein.close();
      throw new IOException("Error creating intitial string array");
    }
    catch(IOException except)
    {
      except.getMessage();
    }
    
    return passengerList;
  }
}
