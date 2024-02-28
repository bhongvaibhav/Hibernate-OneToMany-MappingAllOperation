package com.tybsc.hibernate5_OneToManyAllop;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Certificate 
{
	@Id @GeneratedValue
	private int id;
	
	private String name;

	public Certificate() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Certificate(String name) {
		super();
		this.name = name;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Certificate [id=" + id + ", name=" + name + "]";
	}
	
	

}
