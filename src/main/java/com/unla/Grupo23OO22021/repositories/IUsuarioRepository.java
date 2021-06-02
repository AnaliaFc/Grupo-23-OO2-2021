package com.unla.Grupo23OO22021.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unla.Grupo23OO22021.entities.Usuario;


@Repository("usuarioRepository")
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
	
	public abstract Usuario findById(long id);
	
	@Query(value= "SELECT * from persona p inner join usuario u on p.id_persona=u.id_persona where dni =:dni",nativeQuery=true)
	public abstract Usuario findByDocumento(@Param("dni")int dni);
	
	@Query("SELECT u FROM Usuario u inner join fetch u.perfil where username = (:username)")
	public abstract Usuario findByUsernameWithPerfil(String username);
}
