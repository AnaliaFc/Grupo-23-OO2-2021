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
	
	@Query(value = "SELECT * FROM permisoperiodo pp "
			+ "INNER JOIN permiso p ON p.id_permiso=pp.id_permiso "
			+ "INNER JOIN permisoxlugar pxl ON p.id_permiso=pxl.fk_permiso "
			+ "INNER JOIN lugar l ON pxl.fk_lugar=l.id_lugar "
			+ "INNER JOIN persona per ON per.id_persona=p.id_persona "
			+ "INNER JOIN rodado r ON r.id_rodado=pp.id_rodado "
			+ "WHERE "
			+ "p.fecha BETWEEN ?1 and ?2 "
			+ "OR "
			+ "DATE_ADD(p.fecha, INTERVAL pp.cant_dias DAY) BETWEEN ?1 and ?2" , nativeQuery = true)
	public abstract List<PermisoPeriodo> findByFecha(LocalDate inicio, LocalDate fin);
	
	@Query("SELECT pd FROM PermisoDiario pd "
			+ "inner join fetch pd.persona "
			+ "WHERE pd.fecha BETWEEN :inicio AND :fin")
	public abstract List<PermisoDiario> findByFechaBetween(LocalDate inicio, LocalDate fin);
	
	
}
