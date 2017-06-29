package javaEEproject.movieTheater.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Hall
 * 
 */
@Entity
@Table(name = "Hall")
public class Hall implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@OneToMany(mappedBy = "hall", cascade = CascadeType.ALL)
	private List<Seat> seats = new ArrayList<Seat>();

	@OneToMany(mappedBy = "hall", cascade = CascadeType.REMOVE)
	private List<Movie> movies = new ArrayList<Movie>();

	public Hall() {
		
	}
	
	public Hall(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void addSeat(Seat seat) {
		this.seats.add(seat);
	}

	public List<Movie> getMovie() {
		return movies;
	}

	public void addMovie(Movie movie) {
		this.movies.add(movie);
	}
}
