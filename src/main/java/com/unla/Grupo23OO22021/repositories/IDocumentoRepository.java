package com.unla.Grupo23OO22021.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.Grupo23OO22021.entities.Documento;
@Repository("documentoRepository")
public interface IDocumentoRepository extends JpaRepository<Documento, Long>{
	
}
