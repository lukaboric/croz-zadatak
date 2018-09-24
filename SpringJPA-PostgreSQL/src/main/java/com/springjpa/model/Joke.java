package com.springjpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "joke")
public class Joke implements Serializable {

	private static final long serialVersionUID = -3009157732242241606L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long id;

	@Column(name = "content")
	@NotBlank
	public String content;
	
	@Column(name = "likes")
	public int likes;
	
	@Column(name = "dislikes")
	public int dislikes;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	@NotNull
	public Category category;
	
	@Column(name = "subtract")
	public int subtract;
		
	
	public Joke() {
	}

	public Joke(String content, int likes, int dislikes, Category category) {
		this.content = content;
		this.likes = likes;
		this.dislikes = dislikes;
		this.category = category;
		this.subtract = likes - dislikes;
	}

	@Override
	public String toString() {
		return String.format("Joke[id=%d, content='%s', likes=%d, dislikes=%d, category_id='%s']", id, content, likes, dislikes, category.toString());
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public int getSubstractLikesDislikes() {
		subtract = likes - dislikes;
		return subtract;
	}
}
