package javaEEproject.movieTheater.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javaEEproject.movieTheater.utils.UnpaidReservation;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Reservation
 * 
 */
@Entity
@Table(name = "Reservation")
public class Reservation implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private User user;

	@ManyToOne
	private Movie movie;

	@OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
	private List<Ticket> tickets = new ArrayList<Ticket>();
	
	private boolean isUsed;

	public Reservation() {
	}

	public Reservation(User user, Movie movie) {
		this.user = user;
		this.movie = movie;
	}
	
	public Reservation(UnpaidReservation unpaidReservation) {
		this.user = unpaidReservation.getUser();
		this.movie = unpaidReservation.getMovie();
		this.tickets = unpaidReservation.getReservedTickets();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public List<Ticket> getReservedTickets() {
		return this.tickets;
	}

	public void addTicket(Ticket reservationForSeat) {
		this.tickets.add(reservationForSeat);
	}

	public boolean removeTicket(
			Ticket reservationForSeat) {

		return tickets.remove(reservationForSeat);
	}

	public List<Seat> getReservedSeats() {
		List<Seat> reservedSeats = new ArrayList<Seat>();
		for (int i = 0; i < tickets.size(); i++) {
			reservedSeats.add(tickets.get(i).getSeat());
		}
		
		return reservedSeats;
	}

	/**
	 * @return the isUsed
	 */
	public boolean isUsed() {
		return isUsed;
	}

	/**
	 * @param isUsed the isUsed to set
	 */
	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

}
