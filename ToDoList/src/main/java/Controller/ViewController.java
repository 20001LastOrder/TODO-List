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
	
	private todo.model.TaskList task_list = new todo.model.TaskList("List " + taskListCounter);
	
	private void addCategoryPerformed(java.awt.event.ActionEvent evt) {
		
	}
}
