package javaEEproject.movieTheater.utils;

import java.util.Date;

import javaEEproject.movieTheater.dao.HallDAO;
import javaEEproject.movieTheater.dao.MovieDAO;
import javaEEproject.movieTheater.dao.ReservationDAO;
import javaEEproject.movieTheater.dao.UserDAO;
import javaEEproject.movieTheater.model.Hall;
import javaEEproject.movieTheater.model.Movie;
import javaEEproject.movieTheater.model.Reservation;
import javaEEproject.movieTheater.model.Seat;
import javaEEproject.movieTheater.model.Ticket;
import javaEEproject.movieTheater.model.User;
import javaEEproject.movieTheater.test.TestClass;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class DbUtils {

	private static User[] USERS = { new User("admin", "admin", true),
			new User("niki", "niki", false), new User("ivan", "ivan", false) };

	private static Hall[] HALLS = { new Hall(1), new Hall(2) };
	
	private static Seat[] SEATS_HALL_1 = { new Seat(1, 1), new Seat(1, 2),
			new Seat(1, 3), new Seat(1, 4), new Seat(1, 5), new Seat(2, 1),
			new Seat(2, 2), new Seat(2, 3), new Seat(2, 4), new Seat(2, 5),
			new Seat(3, 1), new Seat(3, 2), new Seat(3, 3), new Seat(3, 4),
			new Seat(3, 5) };

	private static Seat[] SEATS_HALL_2 = { new Seat(1, 1), new Seat(1, 2),
			new Seat(1, 3), new Seat(1, 4), new Seat(2, 1), new Seat(2, 2),
			new Seat(2, 3), new Seat(2, 4), new Seat(3, 1), new Seat(3, 2),
			new Seat(3, 3), new Seat(3, 4), };

	private static Movie[] MOVIES = { new Movie("Tom and Jerry", new Date()),
			new Movie("Star Wars", new Date()),
			new Movie("MyMovie", new Date()) };

	private static Reservation[] RESERVATIONS = {
			new Reservation(USERS[1], MOVIES[0]),
			new Reservation(USERS[1], MOVIES[1]),
			new Reservation(USERS[2], MOVIES[2]) };

	private static Ticket[] ReservedSeats = {
			new Ticket(RESERVATIONS[0]),
			new Ticket(RESERVATIONS[0]),
			new Ticket(RESERVATIONS[0]),
			new Ticket(RESERVATIONS[0]),
			new Ticket(RESERVATIONS[0]),
			new Ticket(RESERVATIONS[1]),
			new Ticket(RESERVATIONS[1]),
			new Ticket(RESERVATIONS[2]) };

	@PersistenceContext
    private EntityManager em;
    
    @EJB
    private UserDAO userDAO;
    
    @EJB
    private ReservationDAO reservationDAO;
    
    @EJB
    private HallDAO hallDAO;
    
    @EJB
    private MovieDAO movieDAO;
    
    @EJB
    private TestClass testClass;
	
	public void addTestDataToDB() {
		 deleteData();
		 addTestData();
	}
	
	private void deleteData() {
        em.createQuery("DELETE FROM Ticket").executeUpdate();
        em.createQuery("DELETE FROM Seat").executeUpdate();
        em.createQuery("DELETE FROM Reservation").executeUpdate();
        em.createQuery("DELETE FROM Movie").executeUpdate();
        em.createQuery("DELETE FROM Hall").executeUpdate();
        em.createQuery("DELETE FROM User").executeUpdate();
   }

    private void addTestData() {
    	
        for (Seat seat: SEATS_HALL_1) {
        	HALLS[0].addSeat(seat);
        }
        
        for (Seat seat: SEATS_HALL_2) {
        	HALLS[1].addSeat(seat);
        }
        
    	MOVIES[0].setHall(HALLS[0]);
    	MOVIES[1].setHall(HALLS[1]);
    	MOVIES[2].setHall(HALLS[1]);
    	
    	HALLS[0].addMovie(MOVIES[0]);
    	HALLS[0].addMovie(MOVIES[1]);
    	HALLS[1].addMovie(MOVIES[2]);
    	
    	ReservedSeats[0].reserveSeat(SEATS_HALL_1[0]);
    	ReservedSeats[1].reserveSeat(SEATS_HALL_1[1]);
    	ReservedSeats[2].reserveSeat(SEATS_HALL_1[2]);
    	ReservedSeats[3].reserveSeat(SEATS_HALL_1[3]);
    	ReservedSeats[4].reserveSeat(SEATS_HALL_1[4]);
    	ReservedSeats[5].reserveSeat(SEATS_HALL_2[0]);
    	ReservedSeats[6].reserveSeat(SEATS_HALL_2[1]);
    	ReservedSeats[7].reserveSeat(SEATS_HALL_2[5]);
    	
        for (User user : USERS) {
            userDAO.addUser(user);
        }
        
        for (Hall hall: HALLS) {
        	hallDAO.addHall(hall);
        }
        
        for (Movie m: MOVIES) {
        	movieDAO.addMovie(m);
        }
        
        for (Reservation r: RESERVATIONS) {
        	reservationDAO.addReservation(r);
        }
        
        Reservation newReservation = new Reservation(USERS[0],MOVIES[1]);
        Ticket reserveForSeats1 = new Ticket(newReservation);
        Ticket reserveForSeats2 = new Ticket(newReservation);
        reserveForSeats1.reserveSeat(SEATS_HALL_2[7]);
        reserveForSeats2.reserveSeat(SEATS_HALL_2[8]);
        newReservation.addTicket(reserveForSeats1);
        newReservation.addTicket(reserveForSeats2);
        
        testClass.addUnpaidReservation(newReservation);
    }
}
