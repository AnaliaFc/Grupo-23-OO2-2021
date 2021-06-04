package com.unla.Grupo23OO22021.helpers;

public class ViewRouteHelper {
	
	// ERROR
	public final static String ERROR_403="error/403";
	
	//GIT VISUALIZADOR
	public static final String QR_WEBVIEW = "https://ezequiel-de-la-fuente.github.io/visualizador-de-permisos/?";
	
	// HOME
	public final static String HOME_INDEX = "home/index";
	public final static String HOME_LOGIN = "home/login";
	public final static String HOME_LOGOUT = "home/logout";
	public final static String HOME_ABOUT_US = "home/about-us";
	public final static String HOME_USER = "home/user";
		
	// PERFIL
	public final static String FORM = "viewsPerfil/form";
	public final static String PERFILES = "viewsPerfil/listaPerfiles";
	
	//PERMISOS
	public final static String ROUTE_PERMISOS = "permiso/listar";
	public final static String PERMISO_FORM_PERIODO = "permiso/form-periodo";
	public final static String PERMISO_FORM_DIA="permiso/form-dia";
	
	//PERSONA
	public final static String PERSONA_FORM = "persona/form";
	
	//QR
	public static final String QR_GENERADO = "QRv/qrgenerado";
	public static final String QR_CODE_IMAGE_PATH = "./src/main/resources/static/qr/QRCode.png";

	// REDIRECT
	public final static String USUARIO_ROOT = "redirect:/usuario";
	public final static String ROUTE_PERFILES = "redirect:/perfil/listar";
	public final static String HOME_ROUTE = "redirect:/";
		
	//RODADO
	public final static String RODADO_FORM = "rodado/form";
	
	// USUARIO
	public final static String USUARIO_INDEX = "usuario/index";
	public final static String USUARIO_FORM = "usuario/form";
	
}
