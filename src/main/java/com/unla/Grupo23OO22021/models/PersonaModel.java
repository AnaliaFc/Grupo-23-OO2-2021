package com.unla.Grupo23OO22021.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PersonaModel {
	protected long idPersona;
	
	@NotNull(message="EL DNI NO PUEDE ESTAR VACIO")
	@Min(value=8,message="EL DNI ES INCORRECTO")
	protected int dni;
	
	@NotNull(message="EL NOMBRE NO PUEDE ESTAR VACIO")
	@Size(min=3,max=20, message="EL NOMBRE DEBE TENER ENTRE 3 Y 20 CARACTERES")
	protected String nombre;
	
	@NotNull(message="EL APELLIDO NO PUEDE ESTAR VACIO")
	@Size(min=3,max=20, message="EL APELLIDO DEBE TENER ENTRE 3 Y 20 CARACTERES")
	protected String apellido;
	
	public PersonaModel() {}
	
	public PersonaModel(long idPersona, @Min(6) int dni, @Size(min = 3, max = 20) String nombre,
			@Size(min = 3, max = 20) String apellido) {
		super();
		this.idPersona = idPersona;
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public PersonaModel(@Min(6) int dni, @Size(min = 3, max = 20) String nombre,
			@Size(min = 3, max = 20) String apellido) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(long idPersona) {
		this.idPersona = idPersona;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	@Override
	public String toString() {
		return "PersonaModel [idPersona=" + idPersona + ", dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido
				+ "]";
	}
	
	
}
