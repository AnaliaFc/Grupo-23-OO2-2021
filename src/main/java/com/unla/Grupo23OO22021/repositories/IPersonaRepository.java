package com.unla.Grupo23OO22021.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unla.Grupo23OO22021.entities.Persona;

@Repository("personaRepository")
public interface IPersonaRepository extends JpaRepository<Persona, Long>{
		
		public abstract Persona findById(long idPersona);
		
		@Query("SELECT r FROM Persona p WHERE p.dni = :dni")
		public abstract Persona findByDni(@Param("dni") int dni);


	}
