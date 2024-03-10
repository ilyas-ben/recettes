package ma.ac.uir.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ma.ac.uir.dao.HibernateUtil;
import ma.ac.uir.dao.IAvisDAO;
import ma.ac.uir.model.Avis;

public class AvisDAO implements IAvisDAO {

	private SessionFactory sessionFactory;

	public AvisDAO() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@Override
	public void ajouterAvis(Avis avis) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(avis);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			throw e;
		} finally {
			session.clear();
			session.close();
		}
		
	}

	@Override
	public void modifierAvis(Avis avis) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.update(avis);
			transaction.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(transaction!=null) transaction.rollback();
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
	}

	@Override
	public void supprimerAvis(Avis avisToDelete) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Avis avis = null;

		try {
			transaction = session.beginTransaction();
			session.delete(avisToDelete);
			transaction.commit();
		} catch (Exception e) {
			if(transaction!=null) transaction.rollback();
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}

	}

	@Override
	public void supprimerAvis(int idAvisToDelete) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Avis avis = null;

		try {
			transaction = session.beginTransaction();
			session.delete(idAvisToDelete);
			transaction.commit();
		} catch (Exception e) {
			if(transaction!=null) transaction.rollback();
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}

	}

	@Override
	public List<Avis> listerALLAvis() {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			return session.createQuery("FROM Avis", Avis.class).list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.clear();
			session.close();

		}
	}

	@Override
	public Avis getAvisById(Integer idAvisToGet) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Avis avis = null;

		try {
			transaction = session.beginTransaction();
			avis = session.get(Avis.class, idAvisToGet);
			session.evict(avis);
			transaction.commit();
			return avis;
		} catch (Exception e) {
			e.printStackTrace();
			return avis;
		} finally {
			session.clear();
			session.close();
		}
	}

}
