package javaEEproject.movieTheater.services;

import java.util.ArrayList;
import java.util.List;

import javaEEproject.movieTheater.utils.UnpaidReservation;

import javax.ejb.Singleton;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

@Singleton
public class UnpaidReservationsService {

	private List<UnpaidReservation> unpaidReservations = new ArrayList<UnpaidReservation>();

	private static final long UNPAID_RESERVATION_TIMEOUT = 60000;
	
	private TimerService timerService;
	
	public List<UnpaidReservation> getUnpaidReservations()
	{
		return unpaidReservations;
	}
	
	public void removeUnpaidReservation(UnpaidReservation unpaidReservation)
	{
		unpaidReservations.remove(unpaidReservation);
	}
	
	public void addUnpaidReservation(UnpaidReservation reservation)
	{
		TimerConfig timerConfig = new TimerConfig();
		timerConfig.setInfo(reservation);

		@SuppressWarnings("unused")
		Timer timer = timerService.createSingleActionTimer(
				UNPAID_RESERVATION_TIMEOUT, timerConfig);
	}
	
	@Timeout
	public void reservationTimeout(Timer timer) {
		UnpaidReservation unpaidReservation = (UnpaidReservation) timer
				.getInfo();
		if (!unpaidReservations.contains(unpaidReservation))
			unpaidReservations.remove(unpaidReservation);
	}
}
