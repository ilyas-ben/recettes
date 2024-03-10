package ma.ac.uir.metier.impl;

import ma.ac.uir.dao.HibernateUtil;
import ma.ac.uir.dao.IIngredientDAO;
import ma.ac.uir.dao.impl.IngredientDAO;
import ma.ac.uir.metier.IIngredientService;
import ma.ac.uir.model.Ingredient;
import java.util.List;

public class IngredientService implements IIngredientService {

    private IIngredientDAO ingredientDAO = new IngredientDAO(HibernateUtil.getSessionFactory());

    //public IngredientService(IIngredientDAO ingredientDAO) {
        //this.ingredientDAO = ingredientDAO;
    //}

    @Override
    public void ajouterIngredient(Ingredient ingredient) {
        ingredientDAO.addIngredient(ingredient);
    }

    @Override
    public Ingredient obtenirIngredientParId(int id) {
        return ingredientDAO.getIngredientById(id);
    }

    @Override
    public List<Ingredient> listerTousLesIngredients() {
        return ingredientDAO.getAllIngredients();
    }

    @Override
    public void mettreAJourIngredient(Ingredient ingredient) {
        ingredientDAO.updateIngredient(ingredient);
    }

    @Override
    public void supprimerIngredient(int id) {
        ingredientDAO.deleteIngredient(id);
    }
}