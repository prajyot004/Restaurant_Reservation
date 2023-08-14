package com.Project.HotelReservationSystem;

import javax.persistence.*;

	@Entity
public class Customer {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private Long contactNumber;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}
	public Customer(String name, String email, Long contactNumber) {
		super();
		this.name = name;
		this.email = email;
		this.contactNumber = contactNumber;
	}
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	
    

}
