package todo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import todo.model.Category;
import todo.model.TaskList;
import todo.persistence.PersistenceXStream;

@Service
public class ToDoService {
	TaskList list;
	
	
	public void setTaskList(TaskList todo) {
		list = todo;
	}
	
	public Category addCategory(String name) {
		System.out.print(list + "asdawwawwae");
		return list.addCategory(name);
		
	}
}
