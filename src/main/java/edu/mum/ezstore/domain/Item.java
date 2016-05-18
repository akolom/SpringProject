package edu.mum.ezstore.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Item implements Serializable {
	private static final long serialVersionUID = 21216L;

	private long id;
	@NotEmpty(message="{NotEmpty}")
	private String name;
	@NotEmpty (message="{NotEmpty}")
	private String description;

	private double price;
	private User seller;
	private Set<Category> categories = new HashSet<Category>();
	private ItemOrder itemOrder;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "seller_id")
	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	@JsonBackReference
	@ManyToMany
	@JoinTable( name="Item_Category", joinColumns={@JoinColumn(name="Item_Id")},
			inverseJoinColumns={ @JoinColumn(name="Category_Id")} )
	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	public ItemOrder getItemOrder() {
		return itemOrder;
	}

	public void setItemOrder(ItemOrder itemOrder) {
		this.itemOrder = itemOrder;
	}
}
