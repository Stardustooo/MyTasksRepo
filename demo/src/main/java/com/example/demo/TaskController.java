package com.example.demo;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class TaskController {


	@Autowired
	private TaskService taskService;
	
	@RequestMapping(value="/tasks", method=RequestMethod.GET)
	public List<Tasks> allTask() {
		return taskService.findAll();
	}
	
	@PostMapping(value = "/tasks/create", consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Tasks createTask(@RequestBody Tasks task) {
		return taskService.saveByEntity(task);
	}
	
	@PutMapping(path="/tasks/update/{id}", consumes="application/json", produces = "application/json")
	public void update(@PathVariable("id") Long id, @RequestBody Tasks task) {
		taskService.updateTask(id, task);
	}
	
	@DeleteMapping("/tasks/delete/{id}")
	public void delete(@PathVariable String id) {
		Long taskId = Long.parseLong(id); 
		taskService.deleteTask(taskId);
	}
	
	@RequestMapping("/tasks/{id}")
	public Optional<Tasks> findById(@PathVariable Long id) {	
		return taskService.findById(id);
	}

	
}
