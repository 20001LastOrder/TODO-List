/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package todo.model;
import java.util.*;
import java.sql.Date;

// line 27 "../../modle.ump"
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
  private TaskList taskList;
  private Category parentCatagory;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Category(String aName, TaskList aTaskList)
  {
    name = aName;
    tasks = new ArrayList<Task>();
    subcategory = new ArrayList<Category>();
    boolean didAddTaskList = setTaskList(aTaskList);
    if (!didAddTaskList)
    {
      throw new RuntimeException("Unable to create category due to taskList");
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

  public TaskList getTaskList()
  {
    return taskList;
  }

  public Category getParentCatagory()
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
    Category existingCategory = aTask.getCategory();
    boolean isNewCategory = existingCategory != null && !this.equals(existingCategory);
    if (isNewCategory)
    {
      aTask.setCategory(this);
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
    //Unable to remove aTask, as it must always have a category
    if (!this.equals(aTask.getCategory()))
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
    Category existingParentCatagory = aSubcategory.getParentCatagory();
    if (existingParentCatagory == null)
    {
      aSubcategory.setParentCatagory(this);
    }
    else if (!this.equals(existingParentCatagory))
    {
      existingParentCatagory.removeSubcategory(aSubcategory);
      addSubcategory(aSubcategory);
    }
    else
    {
      subcategory.add(aSubcategory);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSubcategory(Category aSubcategory)
  {
    boolean wasRemoved = false;
    if (subcategory.contains(aSubcategory))
    {
      subcategory.remove(aSubcategory);
      aSubcategory.setParentCatagory(null);
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
      existingTaskList.removeCategory(this);
    }
    taskList.addCategory(this);
    wasSet = true;
    return wasSet;
  }

  public boolean setParentCatagory(Category aParentCatagory)
  {
    boolean wasSet = false;
    Category existingParentCatagory = parentCatagory;
    parentCatagory = aParentCatagory;
    if (existingParentCatagory != null && !existingParentCatagory.equals(aParentCatagory))
    {
      existingParentCatagory.removeSubcategory(this);
    }
    if (aParentCatagory != null)
    {
      aParentCatagory.addSubcategory(this);
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
    
    while( !subcategory.isEmpty() )
    {
      subcategory.get(0).setParentCatagory(null);
    }
    TaskList placeholderTaskList = taskList;
    this.taskList = null;
    placeholderTaskList.removeCategory(this);
    if (parentCatagory != null)
    {
      Category placeholderParentCatagory = parentCatagory;
      this.parentCatagory = null;
      placeholderParentCatagory.removeSubcategory(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "taskList = "+(getTaskList()!=null?Integer.toHexString(System.identityHashCode(getTaskList())):"null");
  }
}