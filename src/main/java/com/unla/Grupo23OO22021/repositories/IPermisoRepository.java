package com.unla.Grupo23OO22021.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.Grupo23OO22021.entities.Permiso;

//TODO: Permiso debe ser abstracta
@Repository("permisoRepository")
public interface IPermisoRepository extends JpaRepository<Permiso, Integer> {
	public abstract Permiso findById(int id);
}
