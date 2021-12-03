package com.example.TD1.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TD1.DAO.DepartementRepository;
import com.example.TD1.DAO.EtudiantsRepository;
import com.example.TD1.DAO.SpecialiteRepository;
import com.example.TD1.entities.Departement;
import com.example.TD1.entities.Etudiants;
import com.example.TD1.entities.Specialite;


@Controller
@RequestMapping(value="/etudiant/")
public class EtudiantsController {
	
	@Autowired
	private EtudiantsRepository etudiantsRepository;
	
	@Autowired
	private SpecialiteRepository specialiteRepository;
	
	@Autowired
	private DepartementRepository departementRepository;
	
//	@GetMapping("Q1")
//	public List<Etudiants> methodeQ1(){
//		return etudiantsRepository.findByEntreeDateDESC();
//	}
//	
//	@GetMapping("Q2/{a}/{b}")
//	public List<Etudiants> methodeQ2(@PathVariable String a,@PathVariable String b){
//		return etudiantsRepository.findByNomEContainingAndNomEContaining(a,b);
//	}
//	
//	@GetMapping("Q3")
//	public List<Departement> methodeQ3(){
//		return etudiantsRepository.departementFoAncienEtudiant();
//	}
//	
//	@GetMapping("Q4")
//	public float methodeQ4(){
//		return etudiantsRepository.moyenneDesMoyennes();
//	}
//	
//	@GetMapping("Q5")
//	public Object[] methodeQ5(){
//		return etudiantsRepository.maxMoyenneByDepartement();
//	}
//	
//	@GetMapping("Q6")
//	public List<Etudiants> methodeQ6(){
//		return etudiantsRepository.meilleurMoyenne();
//	}
//	
//	@GetMapping("Q7")
//	public Object[] methodeQ7(){
//		return etudiantsRepository.nbrEtudiantsByDepatement();
//	}
//	
//	@GetMapping("")
//	public List<Etudiants> getAll(){
//		return etudiantsRepository.findAll();
//	}
//	
//	@GetMapping("{etudiantId}")
//	public Optional<Etudiants> getEtudiant(@PathVariable Long etudiantId){
//		return etudiantsRepository.findById(etudiantId);
//	}
//	
//	@PostMapping("add")
//	public Etudiants addEtudiant(@RequestBody Etudiants etudiant) {
//		return etudiantsRepository.save(etudiant);
//	}
//	
//	@PutMapping("edit")
//	public Etudiants editEtudiant(@RequestBody Etudiants etudiant) {
//		return etudiantsRepository.save(etudiant);
//	}
//	
//	@DeleteMapping("/delete/{etudiantId}")
//	public void deleteEtudiant(@PathVariable Long etudiantId) {
//		etudiantsRepository.deleteById(etudiantId);
//	}
//	
//	@DeleteMapping("")
//	public void deleteAllEtudiant(@PathVariable Long etudiantId) {
//		etudiantsRepository.deleteById(etudiantId);
//	}
	
	@GetMapping("index")
	public String index(Model model) {
		model.addAttribute("etudiants",etudiantsRepository.findAll());
		return "etudiant";
	}
	
	@GetMapping("delete/{etudiantId}")
	public String delete(Model model,@PathVariable Long etudiantId) {
		etudiantsRepository.deleteById(etudiantId);
		return "redirect:../index";
	}
	
	@GetMapping("add")
	public String add(Model model) {
		Etudiants etudiant=new Etudiants();
		model.addAttribute("etudiant",etudiant);
		return "addEtudiant";
	}
	
//	@PostMapping("add")
//	public String add(Model model) {
//		Etudiants etudiant=new Etudiants();
//		model.addAttribute("etudiant",etudiant);
//		return "addEtudiant";
//	}
}
