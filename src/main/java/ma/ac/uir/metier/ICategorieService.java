package ma.ac.uir.metier;

import ma.ac.uir.model.Categorie;
import java.util.List;

public interface ICategorieService {
    void ajouterCategorie(Categorie categorie);
    Categorie obtenirCategorieParId(int id);
    List<Categorie> listerToutesLesCategories();
    void mettreAJourCategorie(Categorie categorie);
    void supprimerCategorie(int id);
}