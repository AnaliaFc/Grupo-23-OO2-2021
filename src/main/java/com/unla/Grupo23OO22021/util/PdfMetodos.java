package com.unla.Grupo23OO22021.util;

import java.awt.Color;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public class PdfMetodos {
	private Font fuenteDelTitulo;
	private Font fuenteDeLaPrimeraFila;
	
	public PdfPTable crearTablaTitulo(String titulo) {
		PdfPTable tablaTitulo = new PdfPTable(1);
		PdfPCell celda = new PdfPCell(new Phrase("LISTADO DE PERFILES",fuenteDelTitulo));
		celda.setBorder(0);
		celda.setBackgroundColor(Color.LIGHT_GRAY);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(30);
		tablaTitulo.addCell(celda);
		tablaTitulo.setSpacingBefore(10);
		
		return tablaTitulo;
	}
	
	public PdfPTable crearTablaInfo() {
		PdfPTable tiempo = new PdfPTable(1);
		tiempo.addCell("Se genero el dia "+LocalDate.now() +" en este horario "+LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm")));
		return tiempo;
	}

	public PdfMetodos() {
		fuenteDelTitulo=FontFactory.getFont("Helvetica",16, Color.BLACK);
		fuenteDeLaPrimeraFila = FontFactory.getFont("Helvetica",12, Font.BOLD, Color.BLACK);
	}

	public Font getFuenteDeLaPrimeraFila() {
		return fuenteDeLaPrimeraFila;
	}

	
	
	
	
}
