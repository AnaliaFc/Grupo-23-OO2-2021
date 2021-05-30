package com.unla.Grupo23OO22021.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.Grupo23OO22021.entities.Lugar;

@Repository("lugarRepository")
public interface ILugarRepository extends JpaRepository<Lugar, Long> {
	
	public abstract Lugar findByIdLugar(long id);
	
	public abstract Lugar findByLugarAndCodigoPostal(String lugar, String codigoPostal);
}
