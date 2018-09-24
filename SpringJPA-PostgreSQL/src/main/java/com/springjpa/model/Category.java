package com.springjpa.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category implements Serializable {

	private static final long serialVersionUID = -3009157732242241606L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long id;

	@Column(name = "name")
	public String name;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	private List<Joke> jokes;
	
	protected Category() {
	}

	public Category(String name) {
		this.name = name;		
	}
	
	public long getId() {
        return id;
    }
	
	public List<Joke> getJokes() {
        return jokes;
    }

	@Override
	public String toString() {
		return String.format(name);
	}
}
