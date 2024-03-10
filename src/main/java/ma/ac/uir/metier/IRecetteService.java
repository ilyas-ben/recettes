package ma.ac.uir.metier;

import ma.ac.uir.model.Recette;
import java.util.List;
import java.util.Set;

public interface IRecetteService {
    void ajouterRecette(Recette recette);
    Recette obtenirRecetteParId(int id);
    String obtenirImage64EncodedDeRecetteParIdRecette(int idRecette);
    List<Recette> listerToutesLesRecettes();
    List<Recette> getRecettesBySearchTitre(String nom);
    void mettreAJourRecette(Recette recette);
    void mettreAJourRecette(Recette recette, Integer idCategorie, Integer[] idIngredients) throws Exception;
    void supprimerRecette(int id);
    Set<Recette> listerRecettesDunUser(Integer userId);
    List<Recette> listerRecettesParCategorieId(int idCategorie);
    List<Recette> filtrerRecettesParCategorieId(List<Recette> recettes, int idCategorie);
    Recette fromJson(String json);
}