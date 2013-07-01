package org.example;

import java.util.List;
import java.util.LinkedList;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.OneToMany;

@Entity
@Table( name = "QUESTIONSETS" )
public class QuestionSet {
	//private List<Question> questions;
	private String owner;
	private int id;
	
	@Id
	public int getId() {return id;}
	
	public String getOwner() {return owner;}
}

