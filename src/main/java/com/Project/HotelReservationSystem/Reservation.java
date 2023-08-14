package com.Project.HotelReservationSystem;
import javax.persistence.*;

@Entity
public class Reservation {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Hotel restaurant;

    private String reservationDate;

    private String reservationTime;

    private int numberOfGuests;

    private String tablePreference;

    private String specialRequests;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Hotel getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Hotel restaurant) {
		this.restaurant = restaurant;
	}

	

	public String getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}

	public String getReservationTime() {
		return reservationTime;
	}

	public void setReservationTime(String reservationTime) {
		this.reservationTime = reservationTime;
	}

	public int getNumberOfGuests() {
		return numberOfGuests;
	}

	public void setNumberOfGuests(int numberOfGuests) {
		this.numberOfGuests = numberOfGuests;
	}

	public String getTablePreference() {
		return tablePreference;
	}

	public void setTablePreference(String tablePreference) {
		this.tablePreference = tablePreference;
	}

	public String getSpecialRequests() {
		return specialRequests;
	}

	public void setSpecialRequests(String specialRequests) {
		this.specialRequests = specialRequests;
	}

	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    

}
