package com.shopping.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "admin_table")
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AdminID", unique = true, nullable = false)
	private int AdminID;

	@Column(name = "firstName")
	private String firstName;
	
	@Column(name = "lastName")
	private String lastName;
	
	@Column(name = "adminName")
	private String adminName;

	@Column(name = "adminPassword")
	private String adminPassword;

	// Books
	// One-way relationship，don't need "mappedBy" New Refresh
	@OneToMany(cascade = { CascadeType.ALL })
	private Set<Book> Books = new HashSet<Book>();
	// Foods
	@OneToMany(cascade = { CascadeType.ALL })
	private Set<Food> Foods = new HashSet<Food>();
	// General
	@OneToMany(cascade = { CascadeType.ALL })
	private Set<Product> Generals = new HashSet<Product>();

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
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

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public Set<Book> getBooks() {
		return Books;
	}

	public void setBooks(Set<Book> Books) {
		this.Books = Books;
	}

	public Set<Food> getFoods() {
		return Foods;
	}

	public void setFoods(Set<Food> Foods) {
		this.Foods = Foods;
	}

	public Set<Product> getGenerals() {
		return Generals;
	}

	public void setGenerals(Set<Product> Generals) {
		this.Generals = Generals;
	}

	public Admin() {
	}
	
	
}
