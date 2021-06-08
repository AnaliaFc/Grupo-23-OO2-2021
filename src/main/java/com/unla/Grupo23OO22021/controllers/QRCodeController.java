package com.unla.Grupo23OO22021.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.unla.Grupo23OO22021.helpers.ViewRouteHelper;
import com.unla.Grupo23OO22021.models.PermisoDiarioModel;
import com.unla.Grupo23OO22021.models.PermisoModel;
import com.unla.Grupo23OO22021.models.PermisoPeriodoModel;
import com.unla.Grupo23OO22021.services.implementation.PermisoService;
import com.unla.Grupo23OO22021.util.QRCodeGenerator;

@RestController
public class QRCodeController {
	
	@Autowired
	@Qualifier("permisoService")
	private PermisoService permisoService;
	
	@GetMapping("/verqr")
	public ModelAndView mostrarQR() {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.QR_GENERADO);
		return modelAndView;
	}

	
    @GetMapping("/generarydescargarQR/{idPermiso}")
		public ModelAndView generarYver(@PathVariable int idPermiso, RedirectAttributes redirAttrs)throws Exception {
			        
    				String url = (ViewRouteHelper.QR_WEBVIEW+permisoService.generarUrlQR(idPermiso)).replaceAll("\\s+","%20"); 
    				
    			   	QRCodeGenerator.generateQRCodeImage(url,400, 400, ViewRouteHelper.QR_CODE_IMAGE_PATH);
    	 	    	try {
    	 	    	    Thread.sleep(4 * 1000);//Le da tiempo a la imagen para que actualice
    	 	    	} catch (InterruptedException ie) {
    	 	    	    Thread.currentThread().interrupt();
    	 	    	}
    	 	    	
    	 	    	redirAttrs.addFlashAttribute("qr", "qr");
    		        return new ModelAndView("redirect:/"+ViewRouteHelper.ROUTE_PERMISOS);
    }

    
}