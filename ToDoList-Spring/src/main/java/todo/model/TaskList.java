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
  private List<Category> categories;
  private ToDo toDo;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TaskList(String aName, ToDo aToDo)
  {
    name = aName;
    categories = new ArrayList<Category>();
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

  public ToDo getToDo()
  {
    return toDo;
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
    while (categories.size() > 0)
    {
      Category aCategory = categories.get(categories.size() - 1);
      aCategory.delete();
      categories.remove(aCategory);
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