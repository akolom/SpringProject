package edu.mum.ezstore.domain;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

 
@Entity
public class UserCredentials implements Serializable{
	
	private static final long serialVersionUID = 21217L;
	
	@Size(min=5, message="{Size.character.validation}")
 	String username;
	
	@Size(min=6, message="{Size.character.validation}")
	String password;
	
	Boolean enabled;

	@NotEmpty
	private String authority;

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
	
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
 
 	
}
