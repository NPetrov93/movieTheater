package javaEEproject.movieTheater.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Seat
 * 
 */
@Entity
@Table(name = "Seat")
public class Seat implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private int row;

	private int col;

	@ManyToOne
	private Hall hall;
	
	@Transient
	public boolean isTaken;

	public Seat() {
	}

	public Seat(int col, int row) {
		this.col = col;
		this.row = row;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCol() {
		return this.col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getRow() {
		return this.row;
	}

	public void setRow(int row) {
		this.row = row;
	}

}
