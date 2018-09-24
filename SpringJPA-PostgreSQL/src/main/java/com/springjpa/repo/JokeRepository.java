package com.springjpa.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.springjpa.model.Category;
import com.springjpa.model.Joke;

public interface JokeRepository extends PagingAndSortingRepository<Joke, Long>{
	
	Joke findById(Long id);
	
	List<Joke> findAll();
	
	List<Joke> findByCategory(Category category);
	
	@Modifying
	@Query("UPDATE Joke j SET j.likes = j.likes+1, j.subtract = j.subtract+1 WHERE j.id=?1")
	void likeJoke(Long id);
	
	@Modifying
	@Query("UPDATE Joke j SET j.dislikes = j.dislikes+1, j.subtract = j.subtract-1 WHERE j.id=?1")
	void dislikeJoke(Long id);
	
}
