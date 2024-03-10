package ma.ac.uir.dao;

import ma.ac.uir.model.Recette;
import java.util.List;
import java.util.Set;

public interface IRecetteDAO {
    void addRecette(Recette recette);
    void updateRecette(Recette recette);
    Recette getRecetteById(int id);
    List<Recette> getAllRecettes();
    void deleteRecette(Recette recette);
    
    List<Recette> getRecettesBySearchTitre(String nom);
    List<Recette> getRecettesByCategorieId(int idCategorie);
    Set<Recette> getUserRecettes(int userId);
    
    
}