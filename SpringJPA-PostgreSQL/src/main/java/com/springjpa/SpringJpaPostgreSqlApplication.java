package com.springjpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.springjpa.repo.CategoryRepository;
import com.springjpa.repo.JokeRepository;

@SpringBootApplication
public class SpringJpaPostgreSqlApplication implements CommandLineRunner{

	@Autowired
	CategoryRepository repository;
	
	@Autowired
	JokeRepository repository2;
	
	public static void main(String[] args){
		SpringApplication.run(SpringJpaPostgreSqlApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {	
	}
}
