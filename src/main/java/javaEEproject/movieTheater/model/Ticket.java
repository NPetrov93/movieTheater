package javaEEproject.movieTheater.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: ReservationSeat
 */
@Entity
@Table(name = "Ticket")
public class Ticket implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private Reservation reservation;

	@ManyToOne
	private Seat seat;

	public Ticket() {
	}

	public Ticket(Reservation reservation) {
		this.reservation = reservation;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Reservation getReservation() {
		return this.reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public Seat getSeat() {
		return this.seat;
	}

	public void reserveSeat(Seat seat) {
		this.seat = seat;
	}

}
