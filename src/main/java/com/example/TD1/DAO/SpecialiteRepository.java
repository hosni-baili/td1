package com.example.TD1.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.TD1.entities.Departement;
import com.example.TD1.entities.Specialite;

@RepositoryRestResource
public interface SpecialiteRepository extends JpaRepository<Specialite,Long>{

}
