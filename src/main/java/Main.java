import entity.Seance;
import service.ReservationService;
import service.SeanceService;
import util.HibernateUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String... args) {
        HibernateUtil.getSessionFactory();

        System.out.println("Kinowy repertuar 1.0");
        while(true) {
            System.out.println("Wybierz opcję:");
            System.out.println("1. Lista seansów wg. nazwy kina");
            System.out.println("2. Lista seansów wg. miasta");
            System.out.println("3. Lista seansów wg. daty");
            System.out.println("5. Zarezerwuj seans");
            System.out.println("9. Wyjście");

            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            scanner.nextLine();
            SeanceService seanceService = new SeanceService();
            List<Seance> seances = new ArrayList<>();
            switch (option) {
                case 1:
                    System.out.println("Podaj nazwę kina:");
                    String cinemaName = scanner.nextLine();
                    seances = seanceService.getSeancesByCinemaName(cinemaName);
                    showSeances(seances);
                    break;
                case 2:
                    System.out.println("Podaj nazwę miast:");
                    String city = scanner.nextLine();
                    seances = seanceService.getSeancesByCity(city);
                    showSeances(seances);
                    break;
                case 3:
                    System.out.println("Podaj datę(format yyyy-MM-dd)");
                    String dateString = scanner.nextLine();
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    try {
                        LocalDate dateParsed = LocalDate.parse(dateString, dtf);
                        seances = seanceService.getSeancesByDate(dateParsed.atStartOfDay());
                        showSeances(seances);
                    }catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Błędny format daty.");
                    }
                    break;
                case 5:
                    System.out.println("Podaj id seansu:");
                    Long seanceId = scanner.nextLong();
                    scanner.nextLine();
                    System.out.println("Podaj imię i nazwisko:");
                    String client = scanner.nextLine();
                    System.out.println("Podaj ilość miejsc");
                    int seatsAmount = scanner.nextInt();
                    ReservationService reservationService = new ReservationService();
                    boolean isAdded =  reservationService.addReservation(seanceId, client, seatsAmount);
                    if(isAdded) {
                        System.out.println("Rezerwacja powiodła się");
                    } else {
                        System.out.println("Rezerwacja nieudana");
                    }
                    break;
                case 9:
                    HibernateUtil.getSessionFactory().close();
                    return;
                    default:
                        System.out.println("Wybrałeś niesitniejącą opcję.");
                        break;
            }
        }
    }

    private static void showSeances(List<Seance> seances) {
        if(!seances.isEmpty()) {
            seances.forEach(System.out::println);
        } else {
            System.out.println("Brak seansów dla podanych kryteriów.");
        }
    }
}
