package ma.ac.uir.presentation;

import com.opensymphony.xwork2.ActionSupport;
import ma.ac.uir.metier.IIngredientService;
import ma.ac.uir.metier.impl.IngredientService;
import ma.ac.uir.model.Ingredient;
import java.util.List;

public class IngredientAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    private IIngredientService ingredientService = new IngredientService();

    // Paramètres de l'ingrédient
    private Ingredient ingredient;
    private int ingredientId;
    private List<Ingredient> listeIngredients;

    // Getters et Setters

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public List<Ingredient> getListeIngredients() {
        return listeIngredients;
    }

    public void setListeIngredients(List<Ingredient> listeIngredients) {
        this.listeIngredients = listeIngredients;
    }

    // Actions

    public String ajouterIngredient() {
        try {
            ingredientService.ajouterIngredient(ingredient);
            return SUCCESS;
        } catch (Exception e) {
            addActionError("Erreur lors de l'ajout de l'ingrédient : " + e.getMessage());
            return ERROR;
        }
    }

    public String obtenirIngredient() {
        try {
            ingredient = ingredientService.obtenirIngredientParId(ingredientId);
            return SUCCESS;
        } catch (Exception e) {
            addActionError("Erreur lors de l'obtention de l'ingrédient : " + e.getMessage());
            return ERROR;
        }
    }

    public String listerIngredients() {
        try {
            listeIngredients = ingredientService.listerTousLesIngredients();
            return SUCCESS;
        } catch (Exception e) {
            addActionError("Erreur lors de la liste des ingrédients : " + e.getMessage());
            return ERROR;
        }
    }

    public String mettreAJourIngredient() {
        try {
            ingredientService.mettreAJourIngredient(ingredient);
            return SUCCESS;
        } catch (Exception e) {
            addActionError("Erreur lors de la mise à jour de l'ingrédient : " + e.getMessage());
            return ERROR;
        }
    }

    public String supprimerIngredient() {
        try {
            ingredientService.supprimerIngredient(ingredientId);
            return SUCCESS;
        } catch (Exception e) {
            addActionError("Erreur lors de la suppression de l'ingrédient : " + e.getMessage());
            return ERROR;
        }
    }

    // Autres méthodes pour différentes actions...
}