package com.unla.Grupo23OO22021.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.unla.Grupo23OO22021.util.QRCodeGenerator;

@RestController
public class QRCodeController {
	
	private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/static/qr/QRCode.png";

	
    @GetMapping("/generarydescargarQR/{codeText}")
		public ModelAndView generarYver(@PathVariable("codeText") String codeText)throws Exception {
			        
    	 	    	QRCodeGenerator.generateQRCodeImage(codeText,400, 400, QR_CODE_IMAGE_PATH);
		return new ModelAndView("/QRv/qrgenerado");
    }

    
    //La dejo por las dudas
    @GetMapping("/genrateQRCode/{codeText}/{width}/{height}")
   	public ResponseEntity<byte[]> generateQRCode(
   			@PathVariable("codeText") String codeText,
   			@PathVariable("width") Integer width,
   			@PathVariable("height") Integer height)
   		    throws Exception {
   		        return ResponseEntity.status(HttpStatus.OK).body(QRCodeGenerator.getQRCodeImage(codeText, width, height));
   		    }
   	
   	/*Metodo original generar y descargar qrcode
   	 @GetMapping("/genrateAndDownloadQRCode/{codeText}/{width}/{height}")
		public void download(
				@PathVariable("codeText") String codeText,
				@PathVariable("width") Integer width,
				@PathVariable("height") Integer height)
			    throws Exception {
			        QRCodeGenerator.generateQRCodeImage(codeText, width, height, QR_CODE_IMAGE_PATH);
			    } */
   	
   	
}