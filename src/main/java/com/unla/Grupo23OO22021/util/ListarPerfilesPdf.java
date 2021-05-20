package com.unla.Grupo23OO22021.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.unla.Grupo23OO22021.entities.Perfil;

@Component("prueba/imprimir-perfil")
public class ListarPerfilesPdf extends AbstractPdfView{

	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<Perfil> perfils = (List<Perfil>) model.get("perfiles");
		
		PdfPTable tablaPerfiles = new PdfPTable(2);
		
		perfils.forEach(perfil ->{
			tablaPerfiles.addCell(String.valueOf(perfil.getIdPerfil()));
			tablaPerfiles.addCell(perfil.getTipo());
		});
		
		document.add(tablaPerfiles);
	}

}
