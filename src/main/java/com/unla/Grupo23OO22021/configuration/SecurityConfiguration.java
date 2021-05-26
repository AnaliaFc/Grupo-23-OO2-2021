package com.unla.Grupo23OO22021.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.unla.Grupo23OO22021.services.implementation.UsuarioService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	@Qualifier("usuarioService")
	private UsuarioService usuarioService;
	
	@Autowired
	private void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/css/*", "/imgs/*", "/js/*", "/vendor/bootstrap/css/*", "/vendor/jquery/*", "/vendor/bootstrap/js/*").permitAll()
				.antMatchers("/usuario").authenticated()
				.antMatchers("/usuario/new").hasRole("ADMIN")
				.antMatchers("/usuario/crear").hasRole("ADMIN")
				.antMatchers("/usuario/update").hasRole("ADMIN")
				.antMatchers("/usuario/delete/*").hasRole("ADMIN")
				.antMatchers("/usuario?format=pdf").hasRole("AUDITOR")
				
				.antMatchers("/perfil").authenticated()
				.antMatchers("/perfil/listar").authenticated()
				.antMatchers("/perfil/editar/*").hasRole("ADMIN")
				.antMatchers("/perfil/new").hasRole("ADMIN")
				.antMatchers("/perfil/editar/*").hasRole("ADMIN")
				.antMatchers("/perfil/save").hasRole("ADMIN")
				.antMatchers("/perfil/eliminar/*").hasRole("ADMIN")
				.antMatchers("/perfil/listar?format=pdf").hasRole("AUDITOR")
			.and()
				.formLogin().loginPage("/login").loginProcessingUrl("/loginprocess")
				.usernameParameter("username").passwordParameter("password")
				.defaultSuccessUrl("/loginsuccess").permitAll()
			.and()
				.logout().logoutUrl("/logout").logoutSuccessUrl("/logout").permitAll();;
	}
}
