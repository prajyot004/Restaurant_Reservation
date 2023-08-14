package com.Project.HotelReservationSystem;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 * Hello world!
 *
 */
public class App {
	public static Scanner sc = new Scanner(System.in);

//	Add new customer in data base
	public static Customer addCustomer(Session ss,Transaction t) {
		String name, email;
		Long contact;
		if(t.isActive() == false) {
			t = ss.beginTransaction();
		}
		Scanner sc1 = new Scanner(System.in);
		System.out.println("Enter the Customer Name:");
		name = sc1.nextLine();
		System.out.println("Enter Email of Customer:");
		email = sc1.nextLine();
		System.out.println("Enter the Contact Number:");
		contact = sc1.nextLong();

		Customer c1 = new Customer(name, email, contact);
		ss.save(c1);
		t.commit();
		System.out.println("************* User "+name+" Registered successfully ****************");

		return c1;
	}

//	Add new Hotel in Data base
	public static Hotel addHotel(Session ss,Transaction t) {
		Scanner sc1 = new Scanner(System.in);
		if(t.isActive() == false) {
			t = ss.beginTransaction();
		}
		String name, address, contactDetails, CuisineType, hotelTiming;
		System.out.println("Enter name of Hotel:");
		name = sc1.nextLine();
		System.out.println("Enter Address of Hotel:");
		address = sc1.nextLine();
		System.out.println("Enter Contact Detail of Hotel:");
		contactDetails = sc1.nextLine();
		System.out.println("Enter CuisineType of Hotel:");
		CuisineType = sc1.nextLine();
		System.out.println("Enter Timing of Hotel:");
		hotelTiming = sc1.nextLine();


		Hotel h1 = new Hotel(name, address, contactDetails, CuisineType, hotelTiming);
		ss.save(h1);
		t.commit();
		System.out.println("************* Hotel "+name+" Registered successfully ****************");
		return h1;
	}

// function for	making reservation
	public static void MakeReservation(Session ss, Hotel hotel, Customer customer,Transaction t) {
		Reservation r1 = new Reservation();
		if(t.isActive() == false) {
			t = ss.beginTransaction();
		}
		r1.setCustomer(customer);
		r1.setRestaurant(hotel);
		Scanner s1 = new Scanner(System.in);
		System.out.println("Enter the date of Reservation");
		String date = s1.nextLine();
		System.out.println("Enter the Time");
		String Time = s1.nextLine();
		System.out.println("Enter Table preference:- Big / small / medium");
		String tablePreference = s1.nextLine();
		System.out.println("Enter if any special request ");
		String specialRequest = s1.nextLine();
		System.out.println("Enter the Number of guests");
		int numberOfguests = s1.nextInt();
		r1.setReservationDate(date);
		r1.setReservationTime(Time);
		r1.setNumberOfGuests(numberOfguests);
		r1.setTablePreference(tablePreference);
		r1.setSpecialRequests(specialRequest);
		ss.save(r1);
		t.commit();
	}

	public static void main(String[] args) {
		Configuration configuration = new Configuration().configure().addAnnotatedClass(Customer.class)
				.addAnnotatedClass(Hotel.class).addAnnotatedClass(Reservation.class);
		SessionFactory sf = configuration.buildSessionFactory();
		Session ss = sf.openSession();
		Transaction t = ss.beginTransaction();
		int menuChoice = 1;
		while (menuChoice != 2) {
			System.out.println("Main Menu");
			System.out.println("Enter the Choise as follows");
			System.out.println("1.Register new Customer");
			System.out.println("2.Register new Hotel");
			System.out.println("3.Already a Customer You want To make Reservations ");
			System.out.println("4.Admin Menu ");
			int userChoise = sc.nextInt();

			switch (userChoise) {
			case 1:
				Customer c = addCustomer(ss,t);
				break;
			case 2:
				addHotel(ss,t);
				break;
			case 3:
				Boolean flag = false;
				Customer currentCustomer = null;
				while (flag == false) {
					Scanner newSc = new Scanner(System.in);
					System.out.println("Enter Your Registered Phone number: ");
					Long phoneNo = newSc.nextLong();
					Query query = ss.createQuery("from Customer");
					List<Customer> customerList = query.list();
					for (Customer customer : customerList) {
//				System.out.println("Id :"+customer.getId());
//				System.out.println("Name "+customer.getName());
//				System.out.println("Email :"+customer.getEmail());
//				System.out.println("Contact Number :"+customer.getContactNumber());
//				System.out.println("------------------------------------------------------------------");
				Long temp = customer.getContactNumber();
						if (temp.equals(phoneNo)) {
							currentCustomer = customer;
							flag = true;
							break;
						}
					}

					if (flag == false) {
						System.out.println("Your Phone number is not Registered.");
						System.out.println("Press 1 To register Yourself:");
						System.out.println("Press 0 For exit");
						int f1 = sc.nextInt();
						if (f1 == 1) {
							currentCustomer =  addCustomer(ss,t);
							flag = true;
						}
						else if (f1 == 0) {
							menuChoice = 2;
							break;
						}
					}
				}

				if (flag == true)
					{
						System.out.println("Select the Id of Hotel ");
						Query hq = ss.createQuery("from Hotel");
						List<Hotel> hotelList = hq.list();
						for (Hotel hotel : hotelList) {

							System.out.println("Id : " + hotel.getId());
							System.out.println("Hotel Name: " + hotel.getName());
							System.out.println("Address: " + hotel.getAddress());
							System.out.println("Contact Details: " + hotel.getContactDetails());
							System.out.println("CuisineType: " + hotel.getCuisineType());
							System.out.println("Hotel Timing: " + hotel.getTiming());
							System.out.println("-----------------------------------------------------------------");

						}

						System.out.println("Select The ID of hotel to make reservation");
						int hotelId = sc.nextInt();
						Hotel HotelToReserve;
						for (Hotel hotel : hotelList) {
							if (hotelId == hotel.getId()) {
								HotelToReserve = hotel;
					            MakeReservation(ss, HotelToReserve, currentCustomer, t);
					            System.out.println("************ Reservation Successfull in Hotel "+hotel.getName()+" ************* ");
								break;
							}
						}
					}
				break;
			case 4:
				System.out.println("Enter the Password");
				Scanner sc1 = new Scanner(System.in);
				String password = sc1.nextLine();
				if(password.equals("admin1234")) {
					System.out.println("1.Show all customers");
					System.out.println("2.Show all Registed Hotels");
					System.out.println("3.Show all Reservation");
					
				    int adminChice = sc1.nextInt();
					if(adminChice == 1){
						Query query = ss.createQuery("from Customer");
						List<Customer> customerList = query.list();
						for(Customer cus:customerList) {
							System.out.println("Id: "+cus.getId());
							System.out.println("Name: "+cus.getName());
							System.out.println("Email: "+cus.getEmail());
							System.out.println("ContactNo: "+cus.getContactNumber());
							System.out.println("----------------------------------------------------------------");
						}
					}else if(adminChice == 2) {
						Query hq = ss.createQuery("from Hotel");
						List<Hotel> hotelList = hq.list();
						for (Hotel hotel : hotelList) {

							System.out.println("Id : " + hotel.getId());
							System.out.println("Hotel Name: " + hotel.getName());
							System.out.println("Address: " + hotel.getAddress());
							System.out.println("Contact Details: " + hotel.getContactDetails());
							System.out.println("CuisineType: " + hotel.getCuisineType());
							System.out.println("Hotel Timing: " + hotel.getTiming());
							System.out.println("-----------------------------------------------------------------");

						}
					}else if(adminChice == 3) {
						Query rs = ss.createQuery("from Reservation");
						List<Reservation> ReservationList = rs.list();
						System.out.println("--------------------------------------------------------------------------");
						for(Reservation res:ReservationList) {
							System.out.println("Reservation ID: "+res.getId());
							System.out.println("Customer Name: "+res.getCustomer().getName());
							System.out.println("Restaurent Name: "+res.getRestaurant().getName());
							System.out.println("Reservation Date: "+res.getReservationDate());
							System.out.println("Number of Guests: "+res.getNumberOfGuests());
							System.out.println("Reservation Time: "+res.getReservationTime());
							System.out.println("Table Preference: "+res.getTablePreference());
							System.out.println("Special Request: "+(res.getSpecialRequests().length() > 1 ? res.getSpecialRequests() : "none"));
							System.out.println("----------------------------------------------------------------------");
						}
					}
				
				}else {
					System.out.println("********* Incorrect Password ***************");
					break;
				}
				break;
			default:
				break;
			}
			
			System.out.println("Enter 1 for Main menu");
			System.out.println("Enter 2 for exit");
			menuChoice = sc.nextInt();
		}
	}
}
