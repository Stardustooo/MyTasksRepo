package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	public List <Tasks> findAll(){
		var allTask = taskRepository.findAll();
		return (List<Tasks>) allTask;
	}
	
	public Tasks saveByEntity(Tasks entity) {
		
		return taskRepository.save(entity);
	}
	
	public Optional<Tasks> findById(long id) {
		
		return taskRepository.findById(id);
	}
	
	public void deleteTask(Long id) {
		taskRepository.deleteById(id);
	}
	
	public String updateTask(Long id, Tasks task) {
		
		if(taskRepository.findById(id).isPresent()) {
			Tasks old = taskRepository.findById(id).get();		
			old.setDescription(task.getDescription());
			old.setStatus(task.getStatus());
			taskRepository.save(old);
			return "Update correcto";
		}
		return "Update erróneo";
	}
	
	
}
