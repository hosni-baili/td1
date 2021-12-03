package com.example.TD1.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.example.TD1.entities.Departement;

@RepositoryRestResource
public interface DepartementRepository extends JpaRepository<Departement,Long>{
	
	
}
