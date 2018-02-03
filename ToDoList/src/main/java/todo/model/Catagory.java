/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package todo.model;
import java.util.*;
import java.sql.Date;

// line 27 "../../modle.ump"
public class Catagory
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Catagory Attributes
  private String name;

  //Catagory Associations
  private List<Task> tasks;
  private List<Catagory> subcatagory;
  private TaskList taskList;
  private Catagory parentCatagory;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Catagory(String aName, TaskList aTaskList)
  {
    name = aName;
    tasks = new ArrayList<Task>();
    subcatagory = new ArrayList<Catagory>();
    boolean didAddTaskList = setTaskList(aTaskList);
    if (!didAddTaskList)
    {
      throw new RuntimeException("Unable to create catagory due to taskList");
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

  public Task getTask(int index)
  {
    Task aTask = tasks.get(index);
    return aTask;
  }

  public List<Task> getTasks()
  {
    List<Task> newTasks = Collections.unmodifiableList(tasks);
    return newTasks;
  }

  public int numberOfTasks()
  {
    int number = tasks.size();
    return number;
  }

  public boolean hasTasks()
  {
    boolean has = tasks.size() > 0;
    return has;
  }

  public int indexOfTask(Task aTask)
  {
    int index = tasks.indexOf(aTask);
    return index;
  }

  public Catagory getSubcatagory(int index)
  {
    Catagory aSubcatagory = subcatagory.get(index);
    return aSubcatagory;
  }

  public List<Catagory> getSubcatagory()
  {
    List<Catagory> newSubcatagory = Collections.unmodifiableList(subcatagory);
    return newSubcatagory;
  }

  public int numberOfSubcatagory()
  {
    int number = subcatagory.size();
    return number;
  }

  public boolean hasSubcatagory()
  {
    boolean has = subcatagory.size() > 0;
    return has;
  }

  public int indexOfSubcatagory(Catagory aSubcatagory)
  {
    int index = subcatagory.indexOf(aSubcatagory);
    return index;
  }

  public TaskList getTaskList()
  {
    return taskList;
  }

  public Catagory getParentCatagory()
  {
    return parentCatagory;
  }

  public boolean hasParentCatagory()
  {
    boolean has = parentCatagory != null;
    return has;
  }

  public static int minimumNumberOfTasks()
  {
    return 0;
  }

  public Task addTask(String aName, Date aEndDate, int aPriority, String aDescriprion)
  {
    return new Task(aName, aEndDate, aPriority, aDescriprion, this);
  }

  public boolean addTask(Task aTask)
  {
    boolean wasAdded = false;
    if (tasks.contains(aTask)) { return false; }
    Catagory existingCatagory = aTask.getCatagory();
    boolean isNewCatagory = existingCatagory != null && !this.equals(existingCatagory);
    if (isNewCatagory)
    {
      aTask.setCatagory(this);
    }
    else
    {
      tasks.add(aTask);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTask(Task aTask)
  {
    boolean wasRemoved = false;
    //Unable to remove aTask, as it must always have a catagory
    if (!this.equals(aTask.getCatagory()))
    {
      tasks.remove(aTask);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addTaskAt(Task aTask, int index)
  {  
    boolean wasAdded = false;
    if(addTask(aTask))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTasks()) { index = numberOfTasks() - 1; }
      tasks.remove(aTask);
      tasks.add(index, aTask);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTaskAt(Task aTask, int index)
  {
    boolean wasAdded = false;
    if(tasks.contains(aTask))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTasks()) { index = numberOfTasks() - 1; }
      tasks.remove(aTask);
      tasks.add(index, aTask);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTaskAt(aTask, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfSubcatagory()
  {
    return 0;
  }

  public boolean addSubcatagory(Catagory aSubcatagory)
  {
    boolean wasAdded = false;
    if (subcatagory.contains(aSubcatagory)) { return false; }
    Catagory existingParentCatagory = aSubcatagory.getParentCatagory();
    if (existingParentCatagory == null)
    {
      aSubcatagory.setParentCatagory(this);
    }
    else if (!this.equals(existingParentCatagory))
    {
      existingParentCatagory.removeSubcatagory(aSubcatagory);
      addSubcatagory(aSubcatagory);
    }
    else
    {
      subcatagory.add(aSubcatagory);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSubcatagory(Catagory aSubcatagory)
  {
    boolean wasRemoved = false;
    if (subcatagory.contains(aSubcatagory))
    {
      subcatagory.remove(aSubcatagory);
      aSubcatagory.setParentCatagory(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addSubcatagoryAt(Catagory aSubcatagory, int index)
  {  
    boolean wasAdded = false;
    if(addSubcatagory(aSubcatagory))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSubcatagory()) { index = numberOfSubcatagory() - 1; }
      subcatagory.remove(aSubcatagory);
      subcatagory.add(index, aSubcatagory);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSubcatagoryAt(Catagory aSubcatagory, int index)
  {
    boolean wasAdded = false;
    if(subcatagory.contains(aSubcatagory))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSubcatagory()) { index = numberOfSubcatagory() - 1; }
      subcatagory.remove(aSubcatagory);
      subcatagory.add(index, aSubcatagory);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSubcatagoryAt(aSubcatagory, index);
    }
    return wasAdded;
  }

  public boolean setTaskList(TaskList aTaskList)
  {
    boolean wasSet = false;
    if (aTaskList == null)
    {
      return wasSet;
    }

    TaskList existingTaskList = taskList;
    taskList = aTaskList;
    if (existingTaskList != null && !existingTaskList.equals(aTaskList))
    {
      existingTaskList.removeCatagory(this);
    }
    taskList.addCatagory(this);
    wasSet = true;
    return wasSet;
  }

  public boolean setParentCatagory(Catagory aParentCatagory)
  {
    boolean wasSet = false;
    Catagory existingParentCatagory = parentCatagory;
    parentCatagory = aParentCatagory;
    if (existingParentCatagory != null && !existingParentCatagory.equals(aParentCatagory))
    {
      existingParentCatagory.removeSubcatagory(this);
    }
    if (aParentCatagory != null)
    {
      aParentCatagory.addSubcatagory(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (tasks.size() > 0)
    {
      Task aTask = tasks.get(tasks.size() - 1);
      aTask.delete();
      tasks.remove(aTask);
    }
    
    while( !subcatagory.isEmpty() )
    {
      subcatagory.get(0).setParentCatagory(null);
    }
    TaskList placeholderTaskList = taskList;
    this.taskList = null;
    placeholderTaskList.removeCatagory(this);
    if (parentCatagory != null)
    {
      Catagory placeholderParentCatagory = parentCatagory;
      this.parentCatagory = null;
      placeholderParentCatagory.removeSubcatagory(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "taskList = "+(getTaskList()!=null?Integer.toHexString(System.identityHashCode(getTaskList())):"null");
  }
}