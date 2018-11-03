package com.restful.webservices.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonFilter("PayerFilter")
//@JsonIgnoreProperties(value="id")
    @Entity
public class Payer {

    	@Id
    	@GeneratedValue
	private Integer id;
	//@JsonIgnore to ignore the field
	private String name;
	
	public Payer() {
		
	}

	public Payer(int i, String name) {
		this.id = i;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
