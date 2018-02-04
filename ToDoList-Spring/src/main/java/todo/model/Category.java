/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package todo.model;
import java.util.*;
import java.sql.Date;

// line 23 "../../modle.ump"
public class Category
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Category Attributes
  private String name;

  //Category Associations
  private List<Task> tasks;
  private List<Category> subcategory;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Category(String aName)
  {
    name = aName;
    tasks = new ArrayList<Task>();
    subcategory = new ArrayList<Category>();
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

  public Category getSubcategory(int index)
  {
    Category aSubcategory = subcategory.get(index);
    return aSubcategory;
  }

  public List<Category> getSubcategory()
  {
    List<Category> newSubcategory = Collections.unmodifiableList(subcategory);
    return newSubcategory;
  }

  public int numberOfSubcategory()
  {
    int number = subcategory.size();
    return number;
  }

  public boolean hasSubcategory()
  {
    boolean has = subcategory.size() > 0;
    return has;
  }

  public int indexOfSubcategory(Category aSubcategory)
  {
    int index = subcategory.indexOf(aSubcategory);
    return index;
  }

  public static int minimumNumberOfTasks()
  {
    return 0;
  }

  public boolean addTask(Task aTask)
  {
    boolean wasAdded = false;
    if (tasks.contains(aTask)) { return false; }
    tasks.add(aTask);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTask(Task aTask)
  {
    boolean wasRemoved = false;
    if (tasks.contains(aTask))
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

  public static int minimumNumberOfSubcategory()
  {
    return 0;
  }

  public boolean addSubcategory(Category aSubcategory)
  {
    boolean wasAdded = false;
    if (subcategory.contains(aSubcategory)) { return false; }
    subcategory.add(aSubcategory);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSubcategory(Category aSubcategory)
  {
    boolean wasRemoved = false;
    if (subcategory.contains(aSubcategory))
    {
      subcategory.remove(aSubcategory);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addSubcategoryAt(Category aSubcategory, int index)
  {  
    boolean wasAdded = false;
    if(addSubcategory(aSubcategory))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSubcategory()) { index = numberOfSubcategory() - 1; }
      subcategory.remove(aSubcategory);
      subcategory.add(index, aSubcategory);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSubcategoryAt(Category aSubcategory, int index)
  {
    boolean wasAdded = false;
    if(subcategory.contains(aSubcategory))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSubcategory()) { index = numberOfSubcategory() - 1; }
      subcategory.remove(aSubcategory);
      subcategory.add(index, aSubcategory);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSubcategoryAt(aSubcategory, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    tasks.clear();
    subcategory.clear();
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]";
  }
}