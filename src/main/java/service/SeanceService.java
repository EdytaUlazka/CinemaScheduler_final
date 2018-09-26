package service;

import dao.SeanceDAO;
import entity.Seance;
import java.time.*;
import java.util.List;

public class SeanceService {
    private SeanceDAO seanceDAO = new SeanceDAO();

    public List<Seance> getSeancesByCity(String city) {
        return  seanceDAO.findByCity(city);
    }

    public List<Seance> getSeancesByDate(LocalDateTime date) {
        return seanceDAO.findByDate(date);
    }

    public List<Seance> getSeancesByCinemaName(String cinemaName) {
        return  seanceDAO.findByCinemaName(cinemaName);
    }
}
