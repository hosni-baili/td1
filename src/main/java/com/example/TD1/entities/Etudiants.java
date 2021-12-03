package com.example.TD1.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Etudiants {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long numE;
	
	private String nomE;
	
	private int niveau;
	
	private Date dateEntree;
	
	private float moyenne;

	@ManyToOne
	@JoinColumn(name = "idSpecialite")
	private Specialite specialite;
	
	@ManyToOne
	@JoinColumn(name = "idDepartement")
	private Departement departement;

}
