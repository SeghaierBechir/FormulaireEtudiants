package demo.sec;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("select username as principal,password as credentials,active from users where username=?")
		.authoritiesByUsernameQuery("select username as principal,role as role from users_roles where username=?")
		.rolePrefix("ROLE_")
		.passwordEncoder(new MessageDigestPasswordEncoder("MD5"));
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.formLogin().loginPage("/login");
		http.authorizeRequests().antMatchers("/Etudiant/index").hasRole("USER");
		http.authorizeRequests().antMatchers("/Etudiant/form","/Etudiant/edit","/Etudiant/supprimer","/Etudiant/updateEtudiant","/Etudiant/saveEtudiant").hasRole("ADMIN");
		http.exceptionHandling().accessDeniedPage("/403");
	}

}
