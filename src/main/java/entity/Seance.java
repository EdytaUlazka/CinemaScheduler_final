package entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Seance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalDateTime startDate;

    private LocalTime duration;

    private String description;

    private Integer maxSeatsAvailable;

    @Enumerated(EnumType.STRING)
    private MovieType movieType;

    @ManyToOne
    @JoinColumn(name = "cinemaId")
    private Cinema cinema;

    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "seance")
    private List<Reservation> reservations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MovieType getMovieType() {
        return movieType;
    }

    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public Integer getMaxSeatsAvailable() {
        return maxSeatsAvailable;
    }

    public void setMaxSeatsAvailable(Integer maxSeatsAvailable) {
        this.maxSeatsAvailable = maxSeatsAvailable;
    }

    @Override
    public String toString() {
        return "Id seansu: " + id + '\'' +
                ", Tytuł:'" + title + '\'' +
                ", Data rozpoczęcia:" + startDate +
                ", Czas trwania:" + duration +
                ", Opis:'" + description + '\'' +
                ", Typ:" + movieType +
                ", Kino:" + cinema.getName()  + " ("+cinema.getCity()+") " +
                ", Ilość wolnych miejsc: " + resolveAvailableSeatsAmount();
    }

    public int resolveAvailableSeatsAmount() {
        int seatsTaken = 0;
       for(Reservation reservation : reservations) {
           seatsTaken += reservation.getSeatsAmount();
       }
       return  maxSeatsAvailable - seatsTaken;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
