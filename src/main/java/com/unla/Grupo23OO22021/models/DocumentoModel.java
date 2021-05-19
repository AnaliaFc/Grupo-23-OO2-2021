package com.unla.Grupo23OO22021.models;


public class DocumentoModel {
	private long idDocumento;
	private String tipo;
	public DocumentoModel(long idDocumento, String tipo) {
		super();
		this.idDocumento = idDocumento;
		this.tipo = tipo;
	}
	public DocumentoModel(String tipo) {
		super();
		this.tipo = tipo;
	}
	public long getIdDocumento() {
		return idDocumento;
	}
	public void setIdDocumento(long idDocumento) {
		this.idDocumento = idDocumento;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	
	
}
