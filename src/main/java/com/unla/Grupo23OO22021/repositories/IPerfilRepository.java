package com.unla.Grupo23OO22021.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unla.Grupo23OO22021.entities.Perfil;

@Repository("perfilRepository")
public interface IPerfilRepository extends JpaRepository<Perfil, Long> {

    public abstract Perfil findById(long id);
	
	@Query("SELECT p FROM Perfil p WHERE tipo = (:tipo)")
	public abstract Perfil findByTipo(String tipo);

}
