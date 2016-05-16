package edu.mum.ezstore.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

 
@Entity
public class UserCredentials implements Serializable{
	
	private static final long serialVersionUID = 21217L;

	@Id
	@Column(nullable = false, unique = true)
 	String username;
	
	@Column(nullable = false)
	String password;
	
	Boolean enabled;

	@OneToOne(mappedBy="userCredentials", cascade = CascadeType.PERSIST) 
 	private User user;
	
 	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
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
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
 
 	
}
