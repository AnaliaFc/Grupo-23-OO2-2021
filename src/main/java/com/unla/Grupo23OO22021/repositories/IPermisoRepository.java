package com.unla.Grupo23OO22021.repositories;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unla.Grupo23OO22021.entities.Permiso;
import com.unla.Grupo23OO22021.entities.PermisoDiario;
import com.unla.Grupo23OO22021.entities.PermisoPeriodo;

@Repository("permisoRepository")
public interface IPermisoRepository extends JpaRepository<Permiso, Integer> {
	public abstract Permiso findById(int id);
//	
//	@Query("SELECT pp FROM PermisoPeriodo pp "
//			+ "where pp.fecha between :inicio and :fin "
//			+ "or pp.fecha + pp.cantDias between :inicio and :fin")
	@Query(value = "select * from permiso p "
			+ "inner join permisoperiodo pp on p.id_permiso=pp.id_permiso "
			+ "where p.fecha between ?1 and ?2 "
			+ "or "
			+ "date_add(p.fecha, interval pp.cant_dias day) between ?1 and ?2", nativeQuery = true)
	public abstract List<PermisoPeriodo> findByFecha(@Param("inicio") LocalDate inicio, @Param("fin")LocalDate fin);
	
	@Query("SELECT pd FROM PermisoDiario pd "
			+ "inner join fetch pd.persona "
			+ "WHERE pd.fecha BETWEEN :inicio AND :fin")
	public abstract List<PermisoDiario> findByFechaBetween(LocalDate inicio, LocalDate fin);
	
	@Query(value ="SELECT * FROM PermisoPeriodo pp INNER JOIN rodado r ON r.id_rodado=pp.id_rodado WHERE r.dominio=:dominio", nativeQuery = true)
	public abstract List<PermisoPeriodo> findByDominio(@Param("dominio") String dominio);
	
	
}
