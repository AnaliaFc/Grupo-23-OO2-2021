package com.unla.Grupo23OO22021.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unla.Grupo23OO22021.entities.Usuario;


@Repository("usuarioRepository")
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
	
	public abstract Usuario findById(long id);
	
	@Query("SELECT u FROM Usuario u WHERE dni = (:dni)")
	public abstract Usuario findByDocumento(int dni);

		
}
