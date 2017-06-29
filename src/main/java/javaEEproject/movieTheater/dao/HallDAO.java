package javaEEproject.movieTheater.dao;

import javaEEproject.movieTheater.model.Hall;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class HallDAO {

	@PersistenceContext
    private EntityManager em;

    public void addHall(Hall hall) {
        em.persist(hall);
    }
}
