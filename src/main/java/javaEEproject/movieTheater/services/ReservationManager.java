package javaEEproject.movieTheater.services;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javaEEproject.movieTheater.dao.ReservationDAO;
import javaEEproject.movieTheater.model.Reservation;
import javaEEproject.movieTheater.utils.UnpaidReservation;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("reservation")
public class ReservationManager {

	private static final Response RESPONSE_OK = Response.ok().build();

	@Inject
	private UserContext context;

	@Inject
	private ReservationDAO reservationDAO;

	@Inject
	private UnpaidReservationsService unpaidReservationsService;

	@GET
	@Produces("application/json")
	public Collection<Reservation> getUserReservations() {
		return context.getCurrentUser().getReservations();
	}

	@Path("unpaid")
	@GET
	@Produces("application/json")
	public Collection<UnpaidReservation> getUserUnpaidReservations() {
		List<UnpaidReservation> userUnpaidReservations = new ArrayList<UnpaidReservation>();

		for (UnpaidReservation temp : this.unpaidReservationsService
				.getUnpaidReservations()) {
			if (temp.getUser().equalsUser(context.getCurrentUser())) {
				userUnpaidReservations.add(temp);
			}
		}
		return userUnpaidReservations;
	}

	@Path("reserve")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addUnpaidReservation(
			UnpaidReservation reservation) {
		if (context.getCurrentUser() != null) {
			reservation.setUser(context.getCurrentUser());
			unpaidReservationsService.addUnpaidReservation(reservation);

			return RESPONSE_OK;
		}

		return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).build();
	}

	@Path("pay")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response payUnpaidReservation(
			UnpaidReservation unpaidReservation) {

		for (UnpaidReservation temp : this.unpaidReservationsService
				.getUnpaidReservations()) {
			if (temp.isLikeUnpaidReservation(unpaidReservation)) {
				Reservation paidReservation = new Reservation(unpaidReservation);
				unpaidReservationsService.removeUnpaidReservation(temp);
				reservationDAO.addReservation(paidReservation);

				return RESPONSE_OK;
			}
		}

		return Response.status(HttpURLConnection.HTTP_NO_CONTENT).build();
	}

	/*
	 * private boolean checkIfSeatIsTaken(Movie movie, Seat seat) {
	 * List<Reservation> reservationsFromDb = reservationDAO
	 * .getAllReservationsForMovie(movie); for (Reservation reservation :
	 * reservationsFromDb) { for (Ticket ticket :
	 * reservation.getReservedTickets()) { if (ticket.getSeat().equals(seat)) {
	 * return true; } } }
	 * 
	 * for (UnpaidReservation unpaidReservation : unpaidReservations) { for
	 * (Ticket ticket : unpaidReservation.getReservedTickets()) { if
	 * (ticket.getSeat().equals(seat)) { return true; } } }
	 * 
	 * return false; }
	 */
}
