package com.example.TD1.DAO;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.example.TD1.entities.Departement;
import com.example.TD1.entities.Etudiants;

@RepositoryRestResource
public interface EtudiantsRepository extends JpaRepository<Etudiants,Long>{
	
	@RestResource(path="/Q1")
	@Query("select e from Etudiants e where e.departement.nomDepartement='Informatique' ORDER BY e.dateEntree DESC")
	List<Etudiants> findByEntreeDateDESC();
	
	@RestResource(path="/Q2")
	List<Etudiants> findByNomEContainingAndNomEContaining(String L, String M);
	
	@RestResource(path="/Q3")
	@Query("select distinct e.departement from Etudiants e where e.dateEntree IN "
			+ "(select min(e.dateEntree) from Etudiants e)")
	List<Departement> departementFoAncienEtudiant();
	
	@RestResource(path="/Q4")
	@Query("select AVG(e.moyenne) from Etudiants e where e.departement.nomDepartement='Informatique' "
			+ "ORDER BY e.dateEntree DESC")
	float moyenneDesMoyennes();
	
	@RestResource(path="/Q5")
	@Query("select e.nomE,e.departement.nomDepartement from Etudiants e where "
			+ "(e.moyenne,e.departement.idDepartement) In "
			+ "(select MAX(e.moyenne),e.departement.idDepartement from Etudiants e group by e.departement)")
	Object[] maxMoyenneByDepartement();
	
	@RestResource(path="/Q6")
	@Query("select e from Etudiants e where e.moyenne In (select MAX(e.moyenne) from Etudiants e)")
	List<Etudiants> meilleurMoyenne();
	
//	@RestResource(path="/Q7")
//	@Query("select count(e),e.departement.nomDepartement from Etudiants e group by e.departement")
//	Object[] nbrEtudiantsByDepatement();
	
	@RestResource(path="/Q7")
	@Query("select d,count(e) from Departement d right join d.etudiants e group by d")
	Object[] nbrEtudiantsByDepatement();
	
//	@RestResource(path="/Q8")
//	@Query("select count(e) as nbr,e.departement.nomDepartement from Etudiants e where rownum=1 group by e.departement order by nbr")
//	Object[] departementByMinNbrEtudiant();
	
	@RestResource(path="/Q8")
	@Query("select d,count(e) from Departement d left join d.etudiants e group by d having count(e)<= ALL(select count(e) from Departement d left join d.etudiants e group by d)")
	Object[] departementByMinNbrEtudiant();
	
//	@RestResource(path="/Q9")
//	@Query("select count(e.niveau) , e.specialite.libelleSpecialite from Etudiants e group by e.specialite")
//	Object[] niveauBySpecilite();
	
	@RestResource(path="/Q9")
	@Query("select s, e.niveau from Specialite s left join s.etudiants e group by e")
	Object[] niveauBySpecilite();
	
//	@RestResource(path="/Q10")
//	@Query("select count(e) as nbr, e.niveau from Etudiants e group by e.niveau order by nbr DESC")
//	Object[] niveauMaxEtudiants();
	
	@RestResource(path="/Q10")
	@Query("select e.niveau from Etudiants e group by e.niveau having count(e) >= ALL(select count(e) from Etudiants e group by e.niveau)")
	Object[] niveauMaxEtudiants();
	
	@RestResource(path="/Q11")
	@Query("select d,count(e) from Departement d left join d.etudiants e group by d having count(e)>= ALL(select count(e) from Departement d left join d.etudiants e group by d)")
	Departement departementMaxEtudiants();
	
	@RestResource(path="/Q12")
	@Query("select d,count(e) from Departement d left join d.etudiants e group by d having count(e)=0")
	Object[] departementWithNoEtudiant();
	
	@RestResource(path="/Q13")
	@Query("select e.departement from Etudiants e where e.moyenne >= All(select MAX(e.moyenne) from Etudiants e )")
	Departement departementMeilleurEtudiant();
	
	@RestResource(path="/Q14")
	@Query("select e.departement from Etudiants e group by e.departement having AVG(e.moyenne) >= ALL(select AVG(e.moyenne) from Etudiants e group by e.departement)")
	Departement departementMeilleurMoyenneDesMoyennes();
	
	@RestResource(path="/Q15")
	@Query("select d from Departement d left join d.etudiants e group by d having AVG(e.moyenne) >=10")
	List<Departement> departementMoyenneSup10();
	
}
