package ma.ac.uir.presentation;

import com.opensymphony.xwork2.ActionSupport;
import ma.ac.uir.metier.ICategorieService;
import ma.ac.uir.metier.impl.CategorieService;
import ma.ac.uir.model.Categorie;
import java.util.List;

public class CategorieAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    private ICategorieService categorieService = new CategorieService();

    // Paramètres de catégorie
    private Categorie categorie;
    private int categorieId;
    private List<Categorie> listeCategories;

    // Getters et Setters
    
    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public int getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(int categorieId) {
        this.categorieId = categorieId;
    }

    public List<Categorie> getListeCategories() {
        return listeCategories;
    }

    public void setListeCategories(List<Categorie> listeCategories) {
        this.listeCategories = listeCategories;
    }

    // Actions

    public String ajouterCategorie() {
        try {
            categorieService.ajouterCategorie(categorie);
            return SUCCESS;
        } catch (Exception e) {
            addActionError("Erreur lors de l'ajout de la catégorie : " + e.getMessage());
            return ERROR;
        }
    }

    public String obtenirCategorie() {
        try {
            categorie = categorieService.obtenirCategorieParId(categorieId);
            return SUCCESS;
        } catch (Exception e) {
            addActionError("Erreur lors de l'obtention de la catégorie : " + e.getMessage());
            return ERROR;
        }
    }

    public String listerCategories() {
        try {
            listeCategories = categorieService.listerToutesLesCategories();
            return SUCCESS;
        } catch (Exception e) {
            addActionError("Erreur lors de la liste des catégories : " + e.getMessage());
            return ERROR;
        }
    }

    public String mettreAJourCategorie() {
        try {
            categorieService.mettreAJourCategorie(categorie);
            return SUCCESS;
        } catch (Exception e) {
            addActionError("Erreur lors de la mise à jour de la catégorie : " + e.getMessage());
            return ERROR;
        }
    }

    public String supprimerCategorie() {
        try {
            categorieService.supprimerCategorie(categorieId);
            return SUCCESS;
        } catch (Exception e) {
            addActionError("Erreur lors de la suppression de la catégorie : " + e.getMessage());
            return ERROR;
        }
    }
    
}

    // Autres méthodes pour différentes actions...
