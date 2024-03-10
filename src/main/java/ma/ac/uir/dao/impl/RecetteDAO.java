package ma.ac.uir.dao.impl;

import ma.ac.uir.dao.HibernateUtil;
import ma.ac.uir.dao.IRecetteDAO;
import ma.ac.uir.metier.IIngredientService;
import ma.ac.uir.metier.impl.IngredientService;
import ma.ac.uir.metier.impl.UtilisateurService;
import ma.ac.uir.model.Ingredient;
import ma.ac.uir.model.Recette;
import ma.ac.uir.model.Utilisateur;

import org.hibernate.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

public class RecetteDAO implements IRecetteDAO {

	private SessionFactory sessionFactory;
	private IIngredientService iIngredientService = new IngredientService();

	public RecetteDAO() {
		this.sessionFactory = HibernateUtil.getSessionFactory();

	}

	@Override
	public void addRecette(Recette recette) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(recette);
			for (Ingredient ingredient : recette.getIngredients()) {
				Set<Recette> recettes = new HashSet<Recette>();
				recettes = ingredient.getRecettes();
				recettes.add(recette);
				ingredient.setRecettes(recettes);
				session.evict(ingredient);
				session.update(ingredient);
			}
			transaction.commit();
		} catch (RuntimeException e) {
			if (transaction != null)
				transaction.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public Recette getRecetteById(int id) {
		Session session = sessionFactory.openSession();
		Transaction transaction =null;
		try {
			transaction = session.beginTransaction();
			System.out.println(id);
			Recette recette = session.get(Recette.class, id);
			session.evict(recette);
			transaction.commit();
			session.evict(recette);
			session.clear();
			session.close();
			return recette;
		} catch (Exception e) {
			// TODO: handle exception
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Recette> getAllRecettes() {
		Session session = sessionFactory.openSession();
		try {
			return session.createQuery("FROM Recette", Recette.class).list();
		} finally {
			session.clear();
			session.close();
		}
	}

	@Override
	public void updateRecette(Recette recette) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.merge(recette);
			transaction.commit();
		} catch (RuntimeException e) {
			if (transaction != null)
				transaction.rollback();
			throw e;
		} finally {
			session.evict(recette);
			session.clear();
			session.close();
		}
	}

	@Override
	public void deleteRecette(Recette recette) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();

			session.createQuery("DELETE FROM Avis a " + "where " + "a.recette.id = :rid")
					.setParameter("rid", recette.getId()).executeUpdate();

			session.createQuery(
					" update Recette set " + "utilisateur = null," + "categorie = null " + "where id = :rid")
					.setParameter("rid", recette.getId()).executeUpdate();
			

			session.createQuery("DELETE FROM Recette " + "WHERE id = :rid").setParameter("rid", recette.getId())
					.executeUpdate();

			transaction.commit();
		} catch (RuntimeException e) {
			if (transaction != null)
				transaction.rollback();
			throw e;
		} finally {
			session.clear();
			session.close();
		}
		
		
	}

	@Override
	public List<Recette> getRecettesBySearchTitre(String nom) {
		Session session = sessionFactory.openSession();
		try {
			return session.createQuery("FROM Recette r where r.titre like  :tit ", Recette.class)
					.setParameter("tit", nom)
					.list();
		} finally {
			
			session.clear();
			System.out.println("rechercher : "+nom);
			session.close();
		}
	}

	@Override
	public Set<Recette> getUserRecettes(int userId) {
		Session session = sessionFactory.openSession();
		try {
			
			Set<Recette> recettes = new HashSet<Recette>();
			recettes .addAll(  session.createQuery(
					  "SELECT u.recettes "
					+ "FROM Utilisateur u "
					+ "WHERE "
					+ "u.id = :idU", Recette.class)
				.setParameter("idU", userId).list());
			return recettes;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}finally {
			session.clear();
			session.close();
		}
		
	}

	@Override
	public List<Recette> getRecettesByCategorieId(int idCategorie) {
		Session session = sessionFactory.openSession();
		try {
			return session.createQuery(
					"FROM Recette r "
					+ "WHERE r.categorie.id = :idC ", Recette.class)
					.setParameter("idC", idCategorie)
					.list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
}