package com.unla.Grupo23OO22021.repositories;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unla.Grupo23OO22021.entities.Permiso;
import com.unla.Grupo23OO22021.entities.PermisoDiario;
import com.unla.Grupo23OO22021.entities.PermisoPeriodo;

@Repository("permisoRepository")
public interface IPermisoRepository extends JpaRepository<Permiso, Integer> {
	public abstract Permiso findById(int id);
	
	@Query(value = "SELECT * FROM permisoperiodo pr "
			+ "INNER JOIN permiso p ON p.id_permiso=pr.id_permiso "
			+ "WHERE "
			+ "p.fecha BETWEEN ?1 and ?2 "
			+ "OR "
			+ "DATE_ADD(p.fecha, INTERVAL pr.cant_dias DAY) BETWEEN ?1 and ?2" , nativeQuery = true)
	public abstract List<PermisoPeriodo> findByFecha(LocalDate inicio, LocalDate fin);
	
	public abstract List<PermisoDiario> findByFechaBetween(LocalDate inicio, LocalDate fin);
}
