package todo.controller;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Date;
import java.time.LocalTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import todo.service.InvalidInputException;
import todo.model.Category;
import todo.model.Task;
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
	 
	@PostMapping(value = { "/tasks/{name}", "/tasks/{name}/" })
	public Task setTask(
			@PathVariable ("name") String name,
			@RequestParam Date endDate,
			@RequestParam String priority,
			@RequestParam String category
		) throws InvalidInputException {
		int p = Integer.parseInt(priority);
		return service.addTask(name, endDate, p, category);
	}
}
