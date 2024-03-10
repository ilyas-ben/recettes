package ma.ac.uir.dao;

import ma.ac.uir.model.Categorie;
import java.util.List;

public interface ICategorieDAO {
    void addCategorie(Categorie categorie);
    Categorie getCategorieById(int id);
    List<Categorie> getAllCategories();
    void updateCategorie(Categorie categorie);
    void deleteCategorie(int id);
}