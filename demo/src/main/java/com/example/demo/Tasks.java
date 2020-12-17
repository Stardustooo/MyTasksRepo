package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tasks {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="description", length=256, nullable=false, unique=false)
	private String description;
	
	@Column(name="status", length=128, nullable=false, unique=false)
	private String status;
	
	public Tasks() {}
	
	public Tasks(Long id, String description, String status) {
		
		this.id = id;
		this.description = description;
		this.status = status;	
		
	}
	
	public Tasks(String description, String status) {
		
		this.description = description;
		this.status = status;	
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	
}
