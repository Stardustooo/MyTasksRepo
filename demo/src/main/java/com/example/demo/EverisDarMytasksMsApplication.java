package com.example.demo;

//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//		  @Entity
@SpringBootApplication
public class EverisDarMytasksMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EverisDarMytasksMsApplication.class, args);
		
		/*
		  @Id
		  @GeneratedValue
		  private Integer ID;
		  
		  @Column(nullable=false)
		  private String DESCRIPTION;
		  
		  @Column(nullable=false)
		  private String STATUS;		  		  	
		*/
		
	}

}
