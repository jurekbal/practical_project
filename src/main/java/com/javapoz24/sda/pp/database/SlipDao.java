package com.javapoz24.sda.pp.database;

import com.javapoz24.sda.pp.model.Slip;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SlipDao {

    public boolean insertOrUpdate(Slip slip) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(slip);
            transaction.commit();
            return true;
        } catch (IllegalStateException | RollbackException ise) {
            System.err.println("Błąd wstawiania rekordu.");
            ise.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return false;
    }

    public List<Slip> getAll() {
        List<Slip> list = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Slip> query = cb.createQuery(Slip.class);
            Root<Slip> table = query.from(Slip.class);
            query.select(table);

            List<Slip> results = session.createQuery(query).list();
            list.addAll(results);

        } catch (HibernateException he) {
            System.err.println("Listing error.");
            he.printStackTrace();
        }
        return list;
    }

    public Optional<Slip> findById(Long slipId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Slip> query = cb.createQuery(Slip.class);
            Root<Slip> table = query.from(Slip.class);

            query.select(table).where(cb.equal(table.get("slipId"), slipId));
            Slip slip = session.createQuery(query).getSingleResult();

            return Optional.ofNullable(slip);
        } catch (PersistenceException he) {
            System.err.println("Listing error.");
            he.printStackTrace();
        }
        return Optional.empty();
    }

    public boolean deleteSlip(Long slipId) {

        Optional<Slip> optionalSlip = findById(slipId);
        if (optionalSlip.isPresent()) {
            Slip slip = optionalSlip.get();

            Transaction transaction = null;
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();
                session.delete(slip);
                transaction.commit();

                return true;
            } catch (IllegalStateException | RollbackException ise) {
                System.err.println("Błąd usuwania rekordu.");
                ise.printStackTrace();
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        } else {
            System.err.println("Nie udało się odnaleźć cytatu o podanym SlipId");
        }
        return false;
    }
}
