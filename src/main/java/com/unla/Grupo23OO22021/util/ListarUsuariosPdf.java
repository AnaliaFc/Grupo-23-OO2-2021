package com.unla.Grupo23OO22021.util;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.unla.Grupo23OO22021.models.UsuarioModel;

@Component("usuario/index")
public class ListarUsuariosPdf extends AbstractPdfView {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PdfMetodos pdfMetodos = new PdfMetodos();
		
		List<UsuarioModel> usuarios = (List<UsuarioModel>) model.get("usuarios");
		
		document.setPageSize(PageSize.A4.rotate());
		document.open();
		
		document.addTitle("lista-de-usuarios-"+LocalDate.now());
		
		PdfPTable tablaPerfiles = new PdfPTable(7);
		tablaPerfiles.setWidthPercentage(90);
		
		String encabezados[]= {"TIPO DE DOCUMENTO","DOCUMENTO", "NOMBRE", "APELLIDO", "EMAIL", "USERNAME", "PERFIL"};
		for (String encabezado : encabezados) {
			tablaPerfiles.addCell(new PdfPCell(new Phrase(encabezado, pdfMetodos.getFuenteDeLaPrimeraFila())));				
		}
		
		for (UsuarioModel usuarioModel : usuarios) {
			tablaPerfiles.addCell(usuarioModel.getTipoDocumento().name());
			tablaPerfiles.addCell(String.valueOf(usuarioModel.getDni()));
			tablaPerfiles.addCell(usuarioModel.getNombre());
			tablaPerfiles.addCell(usuarioModel.getApellido());
			tablaPerfiles.addCell(usuarioModel.getEmail());
			tablaPerfiles.addCell(usuarioModel.getUsername());
			tablaPerfiles.addCell(usuarioModel.getPerfil().getTipo().replace("ROLE_", ""));
		}
		PdfPTable info = pdfMetodos.crearTablaInfo();
		info.setWidthPercentage(90);
		
		PdfPTable titulo = pdfMetodos.crearTablaTitulo("LISTADO DE USUARIOS");
		titulo.setWidthPercentage(90);
		titulo.setSpacingAfter(10);
		
		document.add(info);
		document.add(titulo);
		document.add(tablaPerfiles);
		
	}
	
}
