package com.springjpa.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.transaction.annotation.Transactional;

import com.springjpa.PagerModel;
import com.springjpa.model.Category;
import com.springjpa.model.Joke;
import com.springjpa.repo.CategoryRepository;
import com.springjpa.repo.JokeRepository;

@Controller
public class WebController {
	
	@Autowired
	CategoryRepository repository_category;
	
	@Autowired
	JokeRepository repository_joke;
	
	List<Category> categories = new ArrayList<Category>();
	
	private static final int BUTTONS_TO_SHOW = 3;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 10;
    private static final int[] PAGE_SIZES = {10};
		
	@GetMapping("/")
	public ModelAndView findAll(@RequestParam("pageSize") Optional<Integer> pageSize, @RequestParam("page") Optional<Integer> page){
			
		ModelAndView modelAndView = new ModelAndView("index");
				
		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
                    
        Page<Joke> jokes = repository_joke.findAll(new PageRequest(evalPage, evalPageSize, Sort.Direction.DESC, "subtract"));
        PagerModel pager = new PagerModel(jokes.getTotalPages(), jokes.getNumber(), BUTTONS_TO_SHOW);

        modelAndView.addObject("jokes", jokes);
        modelAndView.addObject("selectedPageSize", evalPageSize);
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        modelAndView.addObject("pager", pager);
		
		return modelAndView;
	}
	
	@Transactional
	@PostMapping("/jokes/{id}/like")
	public ModelAndView likeJoke(@PathVariable("id") Long id, Model model){
		
		System.out.println("joke id: "+id);
		Joke j = repository_joke.findById(id);
		
		repository_joke.likeJoke(j.id);
		categories = repository_category.findAll();
		List<Joke> jokes = repository_joke.findAll();
		
		model.addAttribute("jokes", jokes);
		
	    return new ModelAndView("redirect:/");
	}
	
	@Transactional
	@PostMapping("/jokes/{id}/dislike")
	public ModelAndView dislikeJoke(@PathVariable("id") Long id, Model model){
		
		System.out.println("joke id: "+id);
		Joke j = repository_joke.findById(id);
		
		repository_joke.dislikeJoke(j.id);
		
		categories = repository_category.findAll();
		List<Joke> jokes = repository_joke.findAll();
		
		model.addAttribute("jokes", jokes);
		
	    return new ModelAndView("redirect:/");
	}
	
	@GetMapping("/new")
    public String jokeForm(Model model) {
		
		categories = repository_category.findAll();
		
		model.addAttribute("categories", categories);
		model.addAttribute("empty_joke", new Joke());
        
        return "form";
    }
	
	@PostMapping("/new")
    public String jokeSubmit(@ModelAttribute("empty_joke") @Valid Joke new_joke, BindingResult bindingResult, Model model) {
		
		List<Category> categories = new ArrayList<Category>();
        categories = repository_category.findAll();
        
        List<Joke> jokes = new ArrayList<Joke>();
        jokes = repository_joke.findAll();
        
        model.addAttribute("categories", categories);
        model.addAttribute("jokes", jokes);
        model.addAttribute("update_joke", new Joke());
		
		if (bindingResult.hasErrors()) {
            return "form";
        }

		Joke joke_temp = new Joke(new_joke.content, 0, 0, new_joke.category);
		
		repository_category.save(new_joke.category);
        repository_joke.save(joke_temp);
        
        return "form";
    }
}

