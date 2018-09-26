package dao;

import entity.Reservation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class ReservationDAO {

    public boolean createReservation(Reservation reservation) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        boolean isCreated = false;
        try {
            session.persist(reservation);
            transaction.commit();
            isCreated = true;
        } catch(Exception e) {
            e.printStackTrace();
            transaction.rollback();
            isCreated = false;
        } finally {
            session.close();
        }
        return isCreated;
    }
}
