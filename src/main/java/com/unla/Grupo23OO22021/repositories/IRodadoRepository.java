package com.unla.Grupo23OO22021.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unla.Grupo23OO22021.entities.Rodado;

@Repository("rodadoRepository")
public interface IRodadoRepository extends JpaRepository<Rodado, Long> {
	
	public abstract Rodado findById(long id);
	
	@Query("SELECT r FROM Rodado r WHERE r.dominio = :dominio")
	public abstract Rodado findByDominio(@Param("dominio") String dominio);


}
