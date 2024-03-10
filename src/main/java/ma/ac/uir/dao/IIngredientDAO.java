package ma.ac.uir.dao;

import ma.ac.uir.model.Ingredient;
import java.util.List;

public interface IIngredientDAO {
    void addIngredient(Ingredient ingredient);
    Ingredient getIngredientById(int id);
    List<Ingredient> getAllIngredients();
    void updateIngredient(Ingredient ingredient);
    void deleteIngredient(int id);
}