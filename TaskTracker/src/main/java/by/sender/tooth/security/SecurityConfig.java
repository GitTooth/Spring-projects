package by.sender.tooth.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;
	
	@Autowired 
	private BCryptPasswordEncoder bcryptEncoder;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.jdbcAuthentication()
			.dataSource(dataSource)
			.passwordEncoder(bcryptEncoder);
	}

	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/login").permitAll()
				.antMatchers("/registration/**").permitAll()
				.antMatchers("/admin").hasRole("MANAGER")
					.anyRequest().authenticated()
					.and()
			.formLogin()
				.loginPage("/login")
				.and()
			.httpBasic();
		
	}
}
