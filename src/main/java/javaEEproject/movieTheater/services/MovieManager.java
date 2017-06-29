package javaEEproject.movieTheater.services;

import java.util.Collection;

import javaEEproject.movieTheater.dao.MovieDAO;
import javaEEproject.movieTheater.model.Movie;
import javaEEproject.movieTheater.model.Seat;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Stateless
@Path("movie")
public class MovieManager {

	@Inject
	private MovieDAO movieDAO;
	
	@GET
    @Produces("application/json")
    public Collection<Movie> getAllMovies() {
		Collection<Movie> allMovies = movieDAO.getAllMovies();
        return allMovies;
    }
	
	@Path("/seats")
	@GET
	@Produces("application/json")
	public Collection<Seat> GetSeats(@QueryParam("title") String title)
	{
		return null;
	}
	
}
