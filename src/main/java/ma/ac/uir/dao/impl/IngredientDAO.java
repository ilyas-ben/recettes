package ma.ac.uir.dao.impl;

import ma.ac.uir.dao.IIngredientDAO;
import ma.ac.uir.model.Ingredient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class IngredientDAO implements IIngredientDAO {

    private SessionFactory sessionFactory;

    public IngredientDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addIngredient(Ingredient ingredient) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(ingredient);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public Ingredient getIngredientById(int id) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Ingredient.class, id);
        } finally {
            session.close();
        }
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("FROM Ingredient", Ingredient.class).list();
        } finally {
            session.close();
        }
    }

    @Override
    public void updateIngredient(Ingredient ingredient) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(ingredient);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteIngredient(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Ingredient ingredient = session.get(Ingredient.class, id);
            if (ingredient != null) {
                session.delete(ingredient);
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