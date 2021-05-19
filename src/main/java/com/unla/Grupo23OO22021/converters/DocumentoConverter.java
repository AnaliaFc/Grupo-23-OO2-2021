package com.unla.Grupo23OO22021.converters;

import com.unla.Grupo23OO22021.entities.Documento;
import com.unla.Grupo23OO22021.models.DocumentoModel;

public class DocumentoConverter {
	public DocumentoModel entityToModel(Documento documento) {
		return new DocumentoModel(documento.getIdDocumento(), documento.getTipo());
	}
	
	public Documento modelToEntity(DocumentoModel documentoModel) {
		return new Documento(documentoModel.getIdDocumento(), documentoModel.getTipo());
	}
}
