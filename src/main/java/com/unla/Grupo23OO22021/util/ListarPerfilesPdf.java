package com.unla.Grupo23OO22021.util;

import java.awt.Color;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.unla.Grupo23OO22021.models.PerfilModel;

@Component("viewsPerfil/listaPerfiles")
public class ListarPerfilesPdf extends AbstractPdfView {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PdfMetodos pdfMetodos = new PdfMetodos();
		List<PerfilModel> perfils = (List<PerfilModel>) model.get("perfiles");

		document.setPageSize(PageSize.A4.rotate());
		document.open();
		document.addTitle("lista-de-perfiles-" + LocalDate.now());

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		for (GrantedAuthority grantedAuthority : user.getAuthorities()) {
			if (!grantedAuthority.getAuthority().contains("AUDITOR")) {
				response.sendRedirect("../error/403");
			}
		}

		PdfPTable tablaPerfiles = new PdfPTable(1);
		PdfPCell columnaTipo = new PdfPCell(new Phrase("TIPO", pdfMetodos.getFuenteDeLaPrimeraFila()));
		columnaTipo.setBackgroundColor(Color.LIGHT_GRAY);
		tablaPerfiles.addCell(columnaTipo);

		perfils.forEach(perfil -> {
			tablaPerfiles.addCell(perfil.getTipo());
		});

		PdfPTable info = pdfMetodos.crearTablaInfo();
		info.setWidthPercentage(90);

		PdfPTable titulo = pdfMetodos.crearTablaTitulo("LISTADO DE PERFILES");
		titulo.setWidthPercentage(90);
		titulo.setSpacingAfter(10);

		tablaPerfiles.setWidthPercentage(90);

		document.add(info);
		document.add(titulo);
		document.add(tablaPerfiles);
	}

}
