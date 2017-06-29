package javaEEproject.movieTheater.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: User
 * 
 */
@Entity
@XmlRootElement
@Table(name = "User")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique=true)
	private String userName;

	@Column(name = "hashedPassword")
	private String password;

	private boolean isSupervisor;
	
	@OneToMany(mappedBy = "user")
	private List<Reservation> reservations = new ArrayList<Reservation>();

	public User() {
	}

	public User(String userName, String password, boolean isSupervisor) {
		this.setUserName(userName);
		this.password = password;
		this.isSupervisor = isSupervisor;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isSupervisor() {
		return isSupervisor;
	}

	public void setSupervisor(boolean isSupervisor) {
		this.isSupervisor = isSupervisor;
	}
	
	public List<Reservation> getReservations()
	{
		return this.reservations;
	}
	
	public boolean equalsUser(User user)
	{
		return (this.userName.equals(user.userName));
	}

}
