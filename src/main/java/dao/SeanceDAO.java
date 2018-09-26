package dao;

import entity.Seance;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SeanceDAO {

    public Seance findById(Long id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Seance seance = null;
        try {
            seance = session.find(Seance.class, id);

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return seance;
    }

    public List<Seance> findByCity(String city) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Seance> seances = new ArrayList<>();
        try {
             seances = session
                    .createQuery("select s from Seance s where s.cinema.city = :city", Seance.class)
                    .setParameter("city", city)
                    .getResultList();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return seances;
    }

    public List<Seance> findByCinemaName(String cinemaName) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Seance> seances = new ArrayList<>();
        try {
            seances = session
                    .createQuery("select s from Seance s where s.cinema.name = :cinemaName", Seance.class)
                    .setParameter("cinemaName", cinemaName)
                    .getResultList();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return seances;
    }

    public List<Seance> findByDate(LocalDateTime queriedDate) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Seance> seances = new ArrayList<>();
        try {
            LocalDateTime queriedDatePlusDay = queriedDate.plusDays(1);
            seances = session
                    .createQuery("select s from Seance s where s.startDate >= :queriedDate and s.startDate < :queriedDatePlusDay", Seance.class)
                    .setParameter("queriedDate", queriedDate)
                    .setParameter("queriedDatePlusDay", queriedDatePlusDay)
                    .getResultList();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return seances;
    }


}
