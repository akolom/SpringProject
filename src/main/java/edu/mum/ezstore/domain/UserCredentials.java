package edu.mum.ezstore.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

 
@Entity
public class UserCredentials implements Serializable{
	
	private static final long serialVersionUID = 21217L;
	
 	String username;
	
	String password;
	
	Boolean enabled;
	
	private String authorithy;

//	private User user;
	
	@Id
	@Column(nullable = false, unique = true)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(nullable = false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
//	@OneToOne(mappedBy="userCredentials", cascade = CascadeType.PERSIST) 
//	public User getUser() {
//		return user;
//	}
//	public void setUser(User user) {
//		this.user = user;
//	}
	public String getAuthorithy() {
		return authorithy;
	}
	public void setAuthorithy(String authorithy) {
		this.authorithy = authorithy;
	}
 
 	
}
