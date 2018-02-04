package todo.service;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import todo.model.Category;
import todo.model.Task;
import todo.model.TaskList;
import todo.persistence.PersistenceXStream;

@Service
public class ToDoService {
	TaskList list;
	
	
	public ToDoService(TaskList todo) {
		list = todo;
	}
	
	public Category addCategory(String name) {
		Category c = new Category(name);
		list.addCategory(c);
		PersistenceXStream.saveToXMLwithXStream(list);
		return c;
		
	}
	
	public Task addTask(String name, Date endDate, int priority, String category) throws InvalidInputException {
		Task newTask = new Task(name, endDate, priority);
		Category c = this.findCategoryByName(category);
		c.addTask(newTask);
		PersistenceXStream.saveToXMLwithXStream(list);
		return newTask;
	}
	
	public Category findCategoryByName(String name)throws InvalidInputException {
		for(Category c : list.getCategories()) {
			System.out.println(c.getName());
			if (c.getName().equals(name)) {
				return c;
			}
		}
		throw new InvalidInputException("cannot find the category");
	}
}
