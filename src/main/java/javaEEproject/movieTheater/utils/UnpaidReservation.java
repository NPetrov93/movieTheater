package javaEEproject.movieTheater.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javaEEproject.movieTheater.model.Movie;
import javaEEproject.movieTheater.model.Ticket;
import javaEEproject.movieTheater.model.User;

public class UnpaidReservation implements Serializable {

	private static final long serialVersionUID = 1L;

	private User user;

	private Movie movie;

	private List<Ticket> tickets = new ArrayList<Ticket>();

	public UnpaidReservation(User user, Movie movie,
			List<Ticket> tickets) {
		this.setUser(user);
		this.setMovie(movie);
		this.setReservedTickets(tickets);
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the movie
	 */
	public Movie getMovie() {
		return movie;
	}

	/**
	 * @param movie the movie to set
	 */
	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	/**
	 * @return the reservationForSeats
	 */
	public List<Ticket> getReservedTickets() {
		return tickets;
	}

	/**
	 * @param reservationForSeats the reservationForSeats to set
	 */
	public void setReservedTickets(List<Ticket> reservationForSeats) {
		this.tickets = reservationForSeats;
	}
	
	public boolean isLikeUnpaidReservation(UnpaidReservation unpaidReservation)
	{
		if(unpaidReservation.tickets.size() == 0 || this.tickets.size() ==  0)
			return false;

		return this.movie.equals(unpaidReservation.movie) && this.user.equals(unpaidReservation.user)
				&& unpaidReservation.tickets.get(0).equals(this.tickets.get(0));
	}

}
