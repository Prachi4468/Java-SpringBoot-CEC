package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Modell;
import com.example.demo.repository.Repo;

@RestController
public class Controller {
	@Autowired
	Repo modeldemo;
	@PostMapping("modeldemo/add")
	public String addmodel(Modell mode) {
		try {
			modeldemo.save(mode);
			return "Record Added";
		}
		catch(Exception ex){
			return ex.getMessage();
		}
	}
	
	@GetMapping("/models")
	public Iterable<Modell> getAllModell(){
		return modeldemo.findAll();
	}
	
	@PostMapping("/modeldemo/delete/{id}")
	public String deleteMod(@PathVariable("id")Integer id)
	{	
		modeldemo.deleteById(id);
		return "redirect:/models";
		
	}
	
	
	
	@GetMapping("/modeldemo/edit/{id}")
	public String editCar(@PathVariable("id")Integer id, Modell model)
	{	
		Modell mod =	modeldemo.findById(id).get();
		
		//model.addAttribute("mod",mod);
		return "edit";
	}
	@PostMapping("/modeldemo/edit")
	public String editCar(Modell mod)
	{	
		Integer id=mod.getId();
		String name=mod.getName();
		int age=mod.getAge();
		String email=mod.getEmail();
		Modell modDB =	modeldemo.findById(id).get();
		
		modDB.setName(name);
		modDB.setAge(age);
		modDB.setEmail(email);
		
		modeldemo.save(modDB);
		return "redirect:/models";
	}
}


