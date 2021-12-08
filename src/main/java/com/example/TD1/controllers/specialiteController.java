package com.example.TD1.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.TD1.DAO.SpecialiteRepository;
import com.example.TD1.entities.Specialite;

@Controller
@RequestMapping(value="/specialite/")
public class specialiteController {

	@Autowired
	private SpecialiteRepository specialiteRepository;

	@GetMapping("list")
	public String listSpecialite(Model model) {
		model.addAttribute("specialites",specialiteRepository.findAll());
		return "specialite/listSpecialite";
	}
	
	@GetMapping("add")
	public String add(Model model) {
		Specialite specialite=new Specialite();
		model.addAttribute("specialite",specialite);
		return "specialite/addSpecialite";
	}
	
	@PostMapping("add")
	public String addSpecia(@Valid Specialite specialite,
			BindingResult result,
			Model model) {
		
		if (result.hasErrors()) {
			
			return "specialite/addSpecialite";
		}

		specialiteRepository.save(specialite);
		
		return "redirect:list";
	}
	
	
	@GetMapping("edit/{idSpecialite}")
	public String dspeciliteForm(@PathVariable("idSpecialite") long idSpecialite, Model model) {
		Specialite specialite = specialiteRepository.findById(idSpecialite)
				.orElseThrow(() -> new IllegalArgumentException("Invalid specialite id:" + idSpecialite));

		
		model.addAttribute("specialite",specialite);
		return "specialite/editSpecialite";
	}
	

	@PostMapping("edit")
	public String updateSpecialite(@Valid Specialite specialite,
			BindingResult result,
			Model model) {
		
		if (result.hasErrors()) {
			Specialite specialite1 = specialiteRepository.findById(specialite.getIdSpecialite())
					.orElseThrow(() -> new IllegalArgumentException("Invalid specilite id:" + specialite.getIdSpecialite()));
			model.addAttribute("specialite",specialite1);
			return "specialite/editSpecialite";
		}
		
		specialiteRepository.save(specialite);
		
		return "redirect:list";
	}
	
	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Long id, Model model) {
		Specialite specialite = specialiteRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid departement Id:" + id));
		
		specialiteRepository.delete(specialite);
		return "redirect:../list";
	}
}


