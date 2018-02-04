/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package todo.model;
import java.sql.Date;
import java.util.*;

// line 11 "../../modle.ump"
public class Task
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Task Attributes
  private String name;
  private Date endDate;
  private int priority;
  private boolean complite;
  private boolean passedDueDate;
  private String descriprion;
  private String location;
  private String note;

  //Task Associations
  private List<Task> subtasks;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Task(String aName, Date aEndDate, int aPriority)
  {
    name = aName;
    endDate = aEndDate;
    priority = aPriority;
    complite = false;
    passedDueDate = false;
    descriprion = null;
    location = null;
    note = null;
    subtasks = new ArrayList<Task>();
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

  public boolean setEndDate(Date aEndDate)
  {
    boolean wasSet = false;
    endDate = aEndDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setPriority(int aPriority)
  {
    boolean wasSet = false;
    priority = aPriority;
    wasSet = true;
    return wasSet;
  }

  public boolean setComplite(boolean aComplite)
  {
    boolean wasSet = false;
    complite = aComplite;
    wasSet = true;
    return wasSet;
  }

  public boolean setPassedDueDate(boolean aPassedDueDate)
  {
    boolean wasSet = false;
    passedDueDate = aPassedDueDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setDescriprion(String aDescriprion)
  {
    boolean wasSet = false;
    descriprion = aDescriprion;
    wasSet = true;
    return wasSet;
  }

  public boolean setLocation(String aLocation)
  {
    boolean wasSet = false;
    location = aLocation;
    wasSet = true;
    return wasSet;
  }

  public boolean setNote(String aNote)
  {
    boolean wasSet = false;
    note = aNote;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public Date getEndDate()
  {
    return endDate;
  }

  public int getPriority()
  {
    return priority;
  }

  public boolean getComplite()
  {
    return complite;
  }

  public boolean getPassedDueDate()
  {
    return passedDueDate;
  }

  public String getDescriprion()
  {
    return descriprion;
  }

  public String getLocation()
  {
    return location;
  }

  public String getNote()
  {
    return note;
  }

  public Task getSubtask(int index)
  {
    Task aSubtask = subtasks.get(index);
    return aSubtask;
  }

  public List<Task> getSubtasks()
  {
    List<Task> newSubtasks = Collections.unmodifiableList(subtasks);
    return newSubtasks;
  }

  public int numberOfSubtasks()
  {
    int number = subtasks.size();
    return number;
  }

  public boolean hasSubtasks()
  {
    boolean has = subtasks.size() > 0;
    return has;
  }

  public int indexOfSubtask(Task aSubtask)
  {
    int index = subtasks.indexOf(aSubtask);
    return index;
  }

  public static int minimumNumberOfSubtasks()
  {
    return 0;
  }

  public boolean addSubtask(Task aSubtask)
  {
    boolean wasAdded = false;
    if (subtasks.contains(aSubtask)) { return false; }
    subtasks.add(aSubtask);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSubtask(Task aSubtask)
  {
    boolean wasRemoved = false;
    if (subtasks.contains(aSubtask))
    {
      subtasks.remove(aSubtask);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addSubtaskAt(Task aSubtask, int index)
  {  
    boolean wasAdded = false;
    if(addSubtask(aSubtask))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSubtasks()) { index = numberOfSubtasks() - 1; }
      subtasks.remove(aSubtask);
      subtasks.add(index, aSubtask);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSubtaskAt(Task aSubtask, int index)
  {
    boolean wasAdded = false;
    if(subtasks.contains(aSubtask))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSubtasks()) { index = numberOfSubtasks() - 1; }
      subtasks.remove(aSubtask);
      subtasks.add(index, aSubtask);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSubtaskAt(aSubtask, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    subtasks.clear();
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "priority" + ":" + getPriority()+ "," +
            "complite" + ":" + getComplite()+ "," +
            "passedDueDate" + ":" + getPassedDueDate()+ "," +
            "descriprion" + ":" + getDescriprion()+ "," +
            "location" + ":" + getLocation()+ "," +
            "note" + ":" + getNote()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "endDate" + "=" + (getEndDate() != null ? !getEndDate().equals(this)  ? getEndDate().toString().replaceAll("  ","    ") : "this" : "null");
  }
}