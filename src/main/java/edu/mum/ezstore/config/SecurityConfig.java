package edu.mum.ezstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import edu.mum.ezstore.security.CustomBasicAuthFilter;
import edu.mum.ezstore.security.RestAuthenticationAccessDeniedHandler;
import edu.mum.ezstore.security.RestAuthenticationEntryPoint;

/**
 * @author Sam
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private RestAuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private RestAuthenticationAccessDeniedHandler restAuthenticationAccessDeniedHandler;
	
	@Autowired
	private CustomBasicAuthFilter customBasicAuthFilter;

	@Autowired
	@Qualifier("userDetailsService")
	private UserDetailsService customUserDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/webjars/**"); // #3
	}
	
	/* You cannot set an authentication success handler for BASIC authentication. 
	 * You can, however, extend BasicAuthenticationFilter and override 
	 * onSuccessfulAuthentication and onUnsuccessfulAuthentication method:
	 * http://stackoverflow.com/questions/16734537/spring-security-3-http-basic-authentication-success-handler
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/api/**")
			.csrf().disable()			
			.addFilterBefore(customBasicAuthFilter, UsernamePasswordAuthenticationFilter.class)
			.exceptionHandling()
				.authenticationEntryPoint(authenticationEntryPoint)
				.accessDeniedHandler(restAuthenticationAccessDeniedHandler)
			.and()
			.authorizeRequests()
			.antMatchers("/api/admin/**").access("hasRole('ROLE_ADMIN')")
			.anyRequest().authenticated()
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.httpBasic();
	}

	/* create bean for AuthenticationManager for autowired in CustomBasicAuthFilter
	 * since the AuthenticationManagerBuilder didn't create AuthenticationManager bean
	 * http://stackoverflow.com/questions/21633555/how-to-inject-authenticationmanager-using-java-configuration-in-a-custom-filter
	 */
	@Bean(name="myAuthenticationManager")
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
       return super.authenticationManagerBean();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
}
