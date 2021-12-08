package com.example.TD1.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.TD1.DAO.DepartementRepository;
import com.example.TD1.entities.Departement;
import com.example.TD1.entities.Etudiants;
import com.example.TD1.entities.Specialite;

@Controller
@RequestMapping(value="/departement/")
public class DepartementController {
	
	@Autowired
	private DepartementRepository departementRepository;

	@GetMapping("list")
	public String listDepartement(Model model) {
		model.addAttribute("departements",departementRepository.findAll());
		return "departement/listDepartement";
	}
	
	@GetMapping("add")
	public String add(Model model) {
		Departement departement=new Departement();
		model.addAttribute("departement",departement);
		return "departement/addDepartement";
	}
	
	@PostMapping("add")
	public String addDepar(@Valid Departement departement,
			BindingResult result,
			Model model) {
		
		if (result.hasErrors()) {
			
			return "departement/addDepartement";
		}

		departementRepository.save(departement);
		
		return "redirect:list";
	}
	
	
	@GetMapping("edit/{idDepartement}")
	public String departForm(@PathVariable("idDepartement") long idDepartement, Model model) {
		Departement departement = departementRepository.findById(idDepartement)
				.orElseThrow(() -> new IllegalArgumentException("Invalid departement id:" + idDepartement));

		
		model.addAttribute("departement",departement);
		return "departement/editDepartement";
	}
	

	@PostMapping("edit")
	public String updateDepartement(@Valid Departement departement,
			BindingResult result,
			Model model) {
		
		if (result.hasErrors()) {
			Departement departement1 = departementRepository.findById(departement.getIdDepartement())
					.orElseThrow(() -> new IllegalArgumentException("Invalid departement id:" + departement.getIdDepartement()));
			model.addAttribute("departement",departement1);
			return "departement/editDepartement";
		}
		
		departementRepository.save(departement);
		
		return "redirect:list";
	}
	
	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Long id, Model model) {
		Departement departement = departementRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid departement Id:" + id));
		
		departementRepository.delete(departement);
		return "redirect:../list";
	}
}

