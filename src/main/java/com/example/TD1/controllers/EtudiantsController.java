package com.example.TD1.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
import javax.validation.Valid;

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
//	@GetMapping("/delete/{etudiantId}")
//	public void deleteEtudiant(@PathVariable Long etudiantId) {
//		etudiantsRepository.deleteById(etudiantId);
//	}
//	
//	@DeleteMapping("")
//	public void deleteAllEtudiant(@PathVariable Long etudiantId) {
//		etudiantsRepository.deleteById(etudiantId);
//	}
	
	@GetMapping("list")
	public String listEtudiant(Model model) {
		model.addAttribute("etudiants",etudiantsRepository.findAll());
		return "etudiant/etudiant";
	}
	
	@GetMapping("add")
	public String add(Model model) {
		Etudiants etudiant=new Etudiants();
		List<Departement> departements= departementRepository.findAll();
		List<Specialite> specialites= specialiteRepository.findAll();
		
		model.addAttribute("etudiant",etudiant);
		model.addAttribute("departements",departements);
		model.addAttribute("specialites",specialites);
		
		return "etudiant/addEtudiant";
	}
	
	@PostMapping("add")
	public String addetu(@Valid Etudiants etudiant,
			@RequestParam(name = "departementId", required = false) Long idDepartement,BindingResult result,
			@RequestParam(name = "specialiteId", required = false) Long idSpecialite,
			Model model) {
		if (result.hasErrors()) {
			List<Departement> departements= departementRepository.findAll();
			List<Specialite> specialites= specialiteRepository.findAll();
			model.addAttribute("specialites",specialites);
			model.addAttribute("departements",departements);
			return "etudiant/addEtudiant";
		}

		Departement departement = departementRepository.findById(idDepartement)
				.orElseThrow(() -> new IllegalArgumentException("Invalid departement:" + idDepartement));
		etudiant.setDepartement(departement);
		
		Specialite specialite = specialiteRepository.findById(idSpecialite)
				.orElseThrow(() -> new IllegalArgumentException("Invalid specialite:" + idSpecialite));
		etudiant.setSpecialite(specialite);
		
		System.out.println(etudiant.getDateEntree());

		etudiantsRepository.save(etudiant);
		
		return "redirect:list";
	}
	
	
	@GetMapping("edit/{idEtudiant}")
	public String etudForm(@PathVariable("idEtudiant") long idEtudiant, Model model) {
		Etudiants etudiant = etudiantsRepository.findById(idEtudiant)
				.orElseThrow(() -> new IllegalArgumentException("Invalid etudiant id:" + idEtudiant));

		List<Departement> departements= departementRepository.findAll();
		List<Specialite> specialites= specialiteRepository.findAll();
		
		model.addAttribute("etudiant",etudiant);
		model.addAttribute("specialites",specialites);
		model.addAttribute("departements",departements);
		model.addAttribute("specialiteId",etudiant.getSpecialite().getIdSpecialite());
		model.addAttribute("departementId",etudiant.getDepartement().getIdDepartement());


		return "etudiant/editEtudiant";
	}

	@PostMapping("edit")
	public String updateEtudiant(@Valid Etudiants etudiant,
			@RequestParam(name = "departementId", required = false) Long idDepartement,BindingResult result,
			@RequestParam(name = "specialiteId", required = false) Long idSpecialite,
			Model model) {
		if (result.hasErrors()) {
			List<Departement> departements= departementRepository.findAll();
			List<Specialite> specialites= specialiteRepository.findAll();
			model.addAttribute("specialites",specialites);
			model.addAttribute("departements",departements);
			return "etudiant/addEtudiant";
		}
		
		Departement departement = departementRepository.findById(idDepartement)
				.orElseThrow(() -> new IllegalArgumentException("Invalid departement:" + idDepartement));
		etudiant.setDepartement(departement);
		
		Specialite specialite = specialiteRepository.findById(idSpecialite)
				.orElseThrow(() -> new IllegalArgumentException("Invalid specialite:" + idSpecialite));
		etudiant.setSpecialite(specialite);;

		etudiantsRepository.save(etudiant);
		
		return "redirect:list";
	}
	
	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Long id, Model model) {
		Etudiants etudiant = etudiantsRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid etudiant Id:" + id));
		
		etudiantsRepository.delete(etudiant);
		return "redirect:../list";
	}
}
