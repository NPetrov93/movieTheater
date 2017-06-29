package javaEEproject.movieTheater.test;

import java.util.ArrayList;
import java.util.List;

import javaEEproject.movieTheater.model.Reservation;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

@Singleton
public class TestClass {
	
	@Resource
	private TimerService timerService;
	
	private long UNPAID_RESERVATION_TIMEOUT = 15000;
	
	private List<Reservation> unpaidReservations = new ArrayList<Reservation>();
	
	public void addUnpaidReservation(Reservation reservation)
	{
		TimerConfig timerConfig = new TimerConfig();
		timerConfig.setInfo(reservation);
		@SuppressWarnings("unused")
		Timer timer =
		    timerService.createTimer(UNPAID_RESERVATION_TIMEOUT, reservation);
	}
	
	@Timeout
	public void timeout(Timer timer) {
	    Reservation unPaidReservation = (Reservation)timer.getInfo();
	    if(unpaidReservations.contains(unPaidReservation))
	    	unpaidReservations.remove(unPaidReservation);
	}
	
}
