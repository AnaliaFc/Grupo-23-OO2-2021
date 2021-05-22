package com.unla.Grupo23OO22021.enums;

public enum TipoDocumento {
	DNI("DNI"), DocumentoUnico("DocumentoUnico"), LibretaCivica("LibretaCivica"),
	LibreDeEnrolamiento("LibreEnrolamiento"), CedulaDeIdentidad("CedulaIdentidad");
	
	public final String displayName; 

    private TipoDocumento(String displayName) { 
     this.displayName = displayName; 
    } 

    public String toString() { 
     return displayName; 
    } 
}
