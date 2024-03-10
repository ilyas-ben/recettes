package ma.ac.uir.dao.impl;

import ma.ac.uir.dao.ICategorieDAO;
import ma.ac.uir.model.Categorie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class CategorieDAO implements ICategorieDAO {

    private SessionFactory sessionFactory;

    public CategorieDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addCategorie(Categorie categorie) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(categorie);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public Categorie getCategorieById(int id) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Categorie.class, id);
        } finally {
            session.close();
        }
    }

    @Override
    public List<Categorie> getAllCategories() {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("FROM Categorie", Categorie.class).list();
        } finally {
            session.close();
        }
    }

    @Override
    public void updateCategorie(Categorie categorie) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(categorie);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteCategorie(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Categorie categorie = session.get(Categorie.class, id);
            if (categorie != null) {
                session.delete(categorie);
            }
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}