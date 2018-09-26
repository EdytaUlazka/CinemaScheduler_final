package service;

import dao.ReservationDAO;
import dao.SeanceDAO;
import entity.Reservation;
import entity.Seance;

public class ReservationService {

    public boolean addReservation(Long seanceId, String client, int seatsAmount) {
        boolean isAdded;
        ReservationDAO reservationDAO = new ReservationDAO();
        Reservation reservation = new Reservation();
        SeanceDAO seanceDAO = new SeanceDAO();
        Seance seance = seanceDAO.findById(seanceId);
        int availableSeats = seance.resolveAvailableSeatsAmount();
        if(availableSeats < seatsAmount) {
            isAdded = false;
            System.out.println("Nie można zarezerwować " + seatsAmount + " miejsc. Ilość pozostałych wolnych miejsc: " + availableSeats);
        } else {
            reservation.setSeance(seanceDAO.findById(seanceId));
            reservation.setClient(client);
            reservation.setSeatsAmount(seatsAmount);
            isAdded =  reservationDAO.createReservation(reservation);
        }
       return isAdded;
    }
}
