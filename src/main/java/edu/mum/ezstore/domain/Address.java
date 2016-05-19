package edu.mum.ezstore.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Address implements Serializable {

	private static final long serialVersionUID = 21213L;

	private long id;
	
	@NotEmpty (message="{NotEmpty}")
	private String city;
	
	@NotEmpty (message="{NotEmpty}")
	@Size(min = 2, max= 2, message="{StateValiation}")
	private String state;
	
	@NotEmpty (message="{NotEmpty}")
	private String street;

	@Pattern(regexp="^\\d{5}?$",message="{ZipcodeValidation}")
	private String zip;

	private User user;

	public Address() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@JsonBackReference // backward reference, not serialized
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
}
