package todo.controller;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import todo.model.Category;
import todo.model.TaskList;
import todo.service.ToDoService;
/*
 * Controller Class for transmitting messages between View and Model
 */
@RestController
public class ViewController {
	String taskName; // This should be linked to some text input in View
	int taskListCounter = 1;
	//private todo.model.ToDo myToDoList = new todo.model.ToDo();
	private todo.model.TaskList task_list;
	@Autowired
	ToDoService service;
	
	@RequestMapping("/")
	public String index() {
		return "Event registration application root. Web-based frontend is a TODO. Use the REST API to manage events and participants.\n";
	}
	
	@PostMapping(value = { "/categories/{name}", "/categories/{name}/" })
	public Category setCategory(@PathVariable("name") String name) {
		Category c = service.addCategory(name);
		return c;
	}
	 /* Initialize a new task_list
	 * Must be called first before adding catagories
	 */
	/*private void task_list_init(String name) {
		if (null == name) {
		task_list = new todo.model.TaskList("List " + taskListCounter, myToDoList);
		} else {
			task_list =  = new todo.model.TaskList("List " + name, myToDoList);
		}
	}
	
	/*
	 * Add new catagory to the existing task_list
	 
	private void addCategoryPerformed(java.awt.event.ActionEvent evt) {
		if (null == task_list) {
			System.err.println("Error: Catagory added when no task_list initialized");
		} else {
			todo.model.Catagory newCatagory = new todo.model.Catagory (taskName, task_list);
		}
	}
	
	private void */
}
