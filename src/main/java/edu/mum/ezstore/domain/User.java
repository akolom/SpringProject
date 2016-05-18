package edu.mum.ezstore.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 21212L;

	private long id;

	@NotEmpty
	private String firstName;

	@NotEmpty
	private String lastName;

	@Min(value = 0, message="{MinValidation}")
	private int age;
	private char gender;

	@Valid
	@NotEmpty
	private List<Address> address = new ArrayList<>();

	private List<Item> itemSet = new ArrayList<>();
	
	@Valid
	@NotNull
	private UserCredentials userCredentials;
	private Set<Comment> comments = new HashSet<>();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonManagedReference
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> addressSet) {
		this.address = addressSet;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "seller")
	public List<Item> getItemSet() {
		return itemSet;
	}

	public void setItemSet(List<Item> itemSet) {
		this.itemSet = itemSet;
	}

	@JsonBackReference
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "usrCred_id")
	public UserCredentials getUserCredentials() {
		return userCredentials;
	}

	public void setUserCredentials(UserCredentials userCredentials) {
		this.userCredentials = userCredentials;
	}

	@JsonManagedReference
	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "toUser")
	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> commentSet) {
		this.comments = commentSet;
	}
}
