package ma.ac.uir.dao.impl;

import ma.ac.uir.dao.HibernateUtil;
import ma.ac.uir.dao.IUtilisateurDAO;
import ma.ac.uir.model.Utilisateur;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UtilisateurDAO implements IUtilisateurDAO {

    private SessionFactory sessionFactory;

    public UtilisateurDAO() {
        this.sessionFactory = HibernateUtil.sessionFactory;
    }

    @Override
    public void addUtilisateur(Utilisateur utilisateur) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(utilisateur);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
        	session.clear();
            session.close();
        }
    }

    @Override
    public Utilisateur getUtilisateurById(int id) {
        Session session = sessionFactory.openSession();
        Utilisateur utilisateur= null;
        try {
        	utilisateur = session.get(Utilisateur.class, id);
        	session.evict(utilisateur);
            return utilisateur;
        } finally {
        	session.clear();
            session.close();
        }
    }
    
    @Override
    public Utilisateur getUtilisateurByEmail(String email) {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("FROM Utilisateur WHERE USR_EMAIL = :email", Utilisateur.class)
                          .setParameter("email", email)
                          .uniqueResult();
        } finally {
        	session.clear();
            session.close();
        }
    }

    @Override
    public List<Utilisateur> getAllUtilisateurs() {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("FROM Utilisateur", Utilisateur.class).list();
        } finally {
        	session.clear();
            session.close();
        }
    }

    @Override
    public void updateUtilisateur(Utilisateur utilisateur) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.merge(utilisateur);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteUtilisateur(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Utilisateur utilisateur = session.get(Utilisateur.class, id);
            if (utilisateur != null) {
                session.delete(utilisateur);
            }
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
        	session.clear();
            session.close();
        }
    }

	@Override
	public void deleteFavorisById(int idFavorisToDelete, Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		
	}
    
    
}