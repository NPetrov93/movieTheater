package javaEEproject.movieTheater.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: Screening
 * 
 */
@Entity
@Table(name = "Movie")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "findByTitle", query = "SELECT m FROM Movie m WHERE m.title = :title"),
        @NamedQuery(name = "getAllMovies", query = "SELECT m FROM Movie m")})
public class Movie implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique=true)
	private String title;

	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;

	@ManyToOne
	private Hall hall;

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
	private List<Reservation> reservations = new ArrayList<Reservation>();

	public Movie() {
	}

	public Movie(String title, Date startTime) {
		this.title = title;
		this.startTime = startTime;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the name
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}

	public Hall getHall() {
		return this.hall;
	}

	public boolean equalsMovie(Movie movie) {
		return (this.title.equals(movie.title) && this.hall.equals(movie.hall) && this.startTime
				.equals(startTime));
	}
	
	@Override
    public String toString() {
        String result = getClass().getSimpleName() + " ";
        if (title != null && !title.trim().isEmpty())
            result += "title: " + title;
        if (startTime != null)
            result += ", startTime: " + startTime;
        if (hall != null)
            result += ", hall: " + hall.getId();
        
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Movie)) {
            return false;
        }
        Movie other = (Movie) obj;
        if (id != null) {
            if (!id.equals(other.id)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

}
