package ma.ac.uir.metier;

import ma.ac.uir.model.Ingredient;
import java.util.List;

public interface IIngredientService {
    void ajouterIngredient(Ingredient ingredient);
    Ingredient obtenirIngredientParId(int id);
    List<Ingredient> listerTousLesIngredients();
    void mettreAJourIngredient(Ingredient ingredient);
    void supprimerIngredient(int id);
}