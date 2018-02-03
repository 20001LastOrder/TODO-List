/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package todo.model;

// line 3 "../../modle.ump"
public class ToDo
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ToDo Attributes
  private int tasksComplete;
  private int tasksPassedDueDate;

  //ToDo Associations
  private TaskList taskList;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ToDo()
  {
    tasksComplete = 0;
    tasksPassedDueDate = 0;
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

  public TaskList getTaskList()
  {
    return taskList;
  }

  public boolean hasTaskList()
  {
    boolean has = taskList != null;
    return has;
  }

  public boolean setTaskList(TaskList aNewTaskList)
  {
    boolean wasSet = false;
    if (taskList != null && !taskList.equals(aNewTaskList) && equals(taskList.getToDo()))
    {
      //Unable to setTaskList, as existing taskList would become an orphan
      return wasSet;
    }

    taskList = aNewTaskList;
    ToDo anOldToDo = aNewTaskList != null ? aNewTaskList.getToDo() : null;

    if (!this.equals(anOldToDo))
    {
      if (anOldToDo != null)
      {
        anOldToDo.taskList = null;
      }
      if (taskList != null)
      {
        taskList.setToDo(this);
      }
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    TaskList existingTaskList = taskList;
    taskList = null;
    if (existingTaskList != null)
    {
      existingTaskList.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "tasksComplete" + ":" + getTasksComplete()+ "," +
            "tasksPassedDueDate" + ":" + getTasksPassedDueDate()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "taskList = "+(getTaskList()!=null?Integer.toHexString(System.identityHashCode(getTaskList())):"null");
  }
}