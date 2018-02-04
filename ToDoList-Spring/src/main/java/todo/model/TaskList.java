/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package todo.model;
import java.util.*;

// line 5 "../../modle.ump"
public class TaskList
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TaskList Attributes
  private int tasksComplete;
  private int tasksPassedDueDate;

  //TaskList Associations
  private List<Category> categories;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TaskList()
  {
    tasksComplete = 0;
    tasksPassedDueDate = 0;
    categories = new ArrayList<Category>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTasksComplete(int aTasksComplete)
  {
    boolean wasSet = false;
    tasksComplete = aTasksComplete;
    wasSet = true;
    return wasSet;
  }

  public boolean setTasksPassedDueDate(int aTasksPassedDueDate)
  {
    boolean wasSet = false;
    tasksPassedDueDate = aTasksPassedDueDate;
    wasSet = true;
    return wasSet;
  }

  public int getTasksComplete()
  {
    return tasksComplete;
  }

  public int getTasksPassedDueDate()
  {
    return tasksPassedDueDate;
  }

  public Category getCategory(int index)
  {
    Category aCategory = categories.get(index);
    return aCategory;
  }

  public List<Category> getCategories()
  {
    List<Category> newCategories = Collections.unmodifiableList(categories);
    return newCategories;
  }

  public int numberOfCategories()
  {
    int number = categories.size();
    return number;
  }

  public boolean hasCategories()
  {
    boolean has = categories.size() > 0;
    return has;
  }

  public int indexOfCategory(Category aCategory)
  {
    int index = categories.indexOf(aCategory);
    return index;
  }

  public static int minimumNumberOfCategories()
  {
    return 0;
  }

  public Category addCategory(String aName)
  {
    return new Category(aName, this);
  }

  public boolean addCategory(Category aCategory)
  {
    boolean wasAdded = false;
    if (categories.contains(aCategory)) { return false; }
    TaskList existingTaskList = aCategory.getTaskList();
    boolean isNewTaskList = existingTaskList != null && !this.equals(existingTaskList);
    if (isNewTaskList)
    {
      aCategory.setTaskList(this);
    }
    else
    {
      categories.add(aCategory);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCategory(Category aCategory)
  {
    boolean wasRemoved = false;
    //Unable to remove aCategory, as it must always have a taskList
    if (!this.equals(aCategory.getTaskList()))
    {
      categories.remove(aCategory);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addCategoryAt(Category aCategory, int index)
  {  
    boolean wasAdded = false;
    if(addCategory(aCategory))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCategories()) { index = numberOfCategories() - 1; }
      categories.remove(aCategory);
      categories.add(index, aCategory);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCategoryAt(Category aCategory, int index)
  {
    boolean wasAdded = false;
    if(categories.contains(aCategory))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCategories()) { index = numberOfCategories() - 1; }
      categories.remove(aCategory);
      categories.add(index, aCategory);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCategoryAt(aCategory, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (categories.size() > 0)
    {
      Category aCategory = categories.get(categories.size() - 1);
      aCategory.delete();
      categories.remove(aCategory);
    }
    
  }


  public String toString()
  {
    return super.toString() + "["+
            "tasksComplete" + ":" + getTasksComplete()+ "," +
            "tasksPassedDueDate" + ":" + getTasksPassedDueDate()+ "]";
  }
}