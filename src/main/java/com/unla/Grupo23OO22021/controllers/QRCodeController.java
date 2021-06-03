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
		ModelAndView modelAndView = new ModelAndView("QRv/qrgenerado");
		return modelAndView;
	}

	
    @GetMapping("/generarydescargarQR/{idPermiso}")
		public ModelAndView generarYver(@PathVariable int idPermiso, RedirectAttributes redirAttrs)throws Exception {
			        
    				String codeText = "https://ezequiel-de-la-fuente.github.io/visualizador-de-permisos/?";
    				
    				PermisoModel permiso= permisoService.findById(idPermiso); 
    	    	
    				if(permiso instanceof PermisoPeriodoModel)
    	 	    	{
    					PermisoPeriodoModel pp = (PermisoPeriodoModel) permiso;
    	 	    		codeText = codeText+pp.generarUrl();
    	 	    	}
    				if(permiso instanceof PermisoDiarioModel)
    	 	    	{
    					PermisoDiarioModel pd = (PermisoDiarioModel) permiso;
    	 	    		codeText = codeText+pd.generarUrl();
    	 	    	}
    				codeText=codeText.replaceAll("\\s+","%20");
    	
    	 	    	QRCodeGenerator.generateQRCodeImage(codeText,400, 400, ViewRouteHelper.QR_CODE_IMAGE_PATH);
    	 	    	redirAttrs.addFlashAttribute("qr", "qr");
    		        return new ModelAndView(ViewRouteHelper.HOME_ROUTE);
    }

    
}