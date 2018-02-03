package Controller;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

/*
 * Controller Class for transmitting messages between View and Model
 */
public class ViewController {
	String taskName; // This should be linked to some text input in View
	int taskListCounter = 1;
	private todo.model.ToDo myToDoList = new todo.model.ToDo();
	private todo.model.TaskList task_list;
	
	/*
	 * Initialize a new task_list
	 * Must be called first before adding catagories
	 */
	private void task_list_init(String name) {
		if (null == name) {
		task_list = new todo.model.TaskList("List " + taskListCounter, myToDoList);
		} else {
			task_list =  = new todo.model.TaskList("List " + name, myToDoList);
		}
	}
	
	/*
	 * Add new catagory to the existing task_list
	 */
	private void addCategoryPerformed(java.awt.event.ActionEvent evt) {
		if (null == task_list) {
			System.err.println("Error: Catagory added when no task_list initialized");
		} else {
			todo.model.Catagory newCatagory = new todo.model.Catagory (taskName, task_list);
		}
	}
	
	private void 
}
