/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package todo.model;
import java.util.*;

// line 10 "../../modle.ump"
public class TaskList
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TaskList Attributes
  private String name;

  //TaskList Associations
  private List<Catagory> catagories;
  private ToDo toDo;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TaskList(String aName, ToDo aToDo)
  {
    name = aName;
    catagories = new ArrayList<Catagory>();
    boolean didAddToDo = setToDo(aToDo);
    if (!didAddToDo)
    {
      throw new RuntimeException("Unable to create taskList due to toDo");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public Catagory getCatagory(int index)
  {
    Catagory aCatagory = catagories.get(index);
    return aCatagory;
  }

  public List<Catagory> getCatagories()
  {
    List<Catagory> newCatagories = Collections.unmodifiableList(catagories);
    return newCatagories;
  }

  public int numberOfCatagories()
  {
    int number = catagories.size();
    return number;
  }

  public boolean hasCatagories()
  {
    boolean has = catagories.size() > 0;
    return has;
  }

  public int indexOfCatagory(Catagory aCatagory)
  {
    int index = catagories.indexOf(aCatagory);
    return index;
  }

  public ToDo getToDo()
  {
    return toDo;
  }

  public static int minimumNumberOfCatagories()
  {
    return 0;
  }

  public Catagory addCatagory(String aName)
  {
    return new Catagory(aName, this);
  }

  public boolean addCatagory(Catagory aCatagory)
  {
    boolean wasAdded = false;
    if (catagories.contains(aCatagory)) { return false; }
    TaskList existingTaskList = aCatagory.getTaskList();
    boolean isNewTaskList = existingTaskList != null && !this.equals(existingTaskList);
    if (isNewTaskList)
    {
      aCatagory.setTaskList(this);
    }
    else
    {
      catagories.add(aCatagory);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCatagory(Catagory aCatagory)
  {
    boolean wasRemoved = false;
    //Unable to remove aCatagory, as it must always have a taskList
    if (!this.equals(aCatagory.getTaskList()))
    {
      catagories.remove(aCatagory);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addCatagoryAt(Catagory aCatagory, int index)
  {  
    boolean wasAdded = false;
    if(addCatagory(aCatagory))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCatagories()) { index = numberOfCatagories() - 1; }
      catagories.remove(aCatagory);
      catagories.add(index, aCatagory);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCatagoryAt(Catagory aCatagory, int index)
  {
    boolean wasAdded = false;
    if(catagories.contains(aCatagory))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCatagories()) { index = numberOfCatagories() - 1; }
      catagories.remove(aCatagory);
      catagories.add(index, aCatagory);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCatagoryAt(aCatagory, index);
    }
    return wasAdded;
  }

  public boolean setToDo(ToDo aNewToDo)
  {
    boolean wasSet = false;
    if (aNewToDo == null)
    {
      //Unable to setToDo to null, as taskList must always be associated to a toDo
      return wasSet;
    }
    
    TaskList existingTaskList = aNewToDo.getTaskList();
    if (existingTaskList != null && !equals(existingTaskList))
    {
      //Unable to setToDo, the current toDo already has a taskList, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    ToDo anOldToDo = toDo;
    toDo = aNewToDo;
    toDo.setTaskList(this);

    if (anOldToDo != null)
    {
      anOldToDo.setTaskList(null);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (catagories.size() > 0)
    {
      Catagory aCatagory = catagories.get(catagories.size() - 1);
      aCatagory.delete();
      catagories.remove(aCatagory);
    }
    
    ToDo existingToDo = toDo;
    toDo = null;
    if (existingToDo != null)
    {
      existingToDo.setTaskList(null);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "toDo = "+(getToDo()!=null?Integer.toHexString(System.identityHashCode(getToDo())):"null");
  }
}