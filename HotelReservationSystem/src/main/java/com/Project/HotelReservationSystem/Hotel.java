package com.Project.HotelReservationSystem;

import javax.persistence.*;

@Entity
public class Hotel {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String contactDetails;
    private String cuisineType;
    private String Timing;
    
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactDetails() {
		return contactDetails;
	}
	public void setContactDetails(String contactDetails) {
		this.contactDetails = contactDetails;
	}
	public String getCuisineType() {
		return cuisineType;
	}
	public void setCuisineType(String cuisineType) {
		this.cuisineType = cuisineType;
	}

	public Hotel(String name, String address, String contactDetails, String cuisineType,String HotelTiming) {
		super();
		this.name = name;
		this.address = address;
		this.contactDetails = contactDetails;
		this.cuisineType = cuisineType;
		this.Timing = HotelTiming;
	}

	public String getTiming() {
		return Timing;
	}

	public void setTiming(String timing) {
		Timing = timing;
	}

	public Hotel() {
		super();
		// TODO Auto-generated constructor stub
	}  
	
	
	
	
	
    
}
