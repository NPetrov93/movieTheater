package javaEEproject.movieTheater.dao;

import java.util.List;

import javaEEproject.movieTheater.model.Movie;
import javaEEproject.movieTheater.model.Reservation;
import javaEEproject.movieTheater.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class ReservationDAO {

	@PersistenceContext
	private EntityManager em;

	public void addReservation(Reservation reservation) {
    	em.persist(reservation);
    }

	public Reservation getReservationById(Long id)
	{
		return em.find(Reservation.class, id);
	}
	
	public void deleteReservation(Reservation reservation)
	{
		em.remove(reservation);
		em.flush();
	}
    
    public List<Reservation> getAll() {
    	String txtQuery = "SELECT r FROM Reservation r";
        TypedQuery<Reservation> query = em.createQuery(txtQuery, Reservation.class);
        return query.getResultList();
    }
    
    public List<Reservation> getAllUserReservations(User user) {
    	String txtQuery = "SELECT r FROM Reservation r WHERE r.user=:user";
        TypedQuery<Reservation> query = em.createQuery(txtQuery, Reservation.class);
        query.setParameter("user", user);
        return query.getResultList();
    }
    
    public List<Reservation> getAllReservationsForMovie(Movie movie) {
    	String txtQuery = "SELECT r FROM Reservation r WHERE r.movie=:movie";
        TypedQuery<Reservation> query = em.createQuery(txtQuery, Reservation.class);
        query.setParameter("movie", movie);
        return query.getResultList();
    }

}
