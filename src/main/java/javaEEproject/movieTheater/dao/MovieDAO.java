package javaEEproject.movieTheater.dao;

import java.util.Collection;

import javaEEproject.movieTheater.model.Movie;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class MovieDAO {

	@PersistenceContext
	private EntityManager em;
	
	public void addMovie(Movie movie)
	{
		em.persist(movie);
	}
	
	public Collection<Movie> getAllMovies()
	{
		return em.createNamedQuery("getAllMovies", Movie.class).getResultList();
	}
	
	public Movie getMovieByName(String movieName)
	{
		String txtQuery = "SELECT m FROM Movie m WHERE m.name=:movieName";
        TypedQuery<Movie> query = em.createQuery(txtQuery, Movie.class);
        query.setParameter("movieName", movieName);
        return queryMovie(query);
	}
	
	private Movie queryMovie(TypedQuery<Movie> query) {
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
	
}
