package ma.ac.uir.metier.impl;

import ma.ac.uir.dao.HibernateUtil;
import ma.ac.uir.dao.ICategorieDAO;
import ma.ac.uir.dao.impl.CategorieDAO;
import ma.ac.uir.metier.ICategorieService;
import ma.ac.uir.model.Categorie;
import java.util.List;

public class CategorieService implements ICategorieService {

    private ICategorieDAO categorieDAO = new CategorieDAO(HibernateUtil.getSessionFactory());

    //public CategorieService(ICategorieDAO categorieDAO) {
        //this.categorieDAO = categorieDAO;
    //}

    @Override
    public void ajouterCategorie(Categorie categorie) {
        categorieDAO.addCategorie(categorie);
    }

    @Override
    public Categorie obtenirCategorieParId(int id) {
        return categorieDAO.getCategorieById(id);
    }

    @Override
    public List<Categorie> listerToutesLesCategories() {
        return categorieDAO.getAllCategories();
    }

    @Override
    public void mettreAJourCategorie(Categorie categorie) {
        categorieDAO.updateCategorie(categorie);
    }

    @Override
    public void supprimerCategorie(int id) {
        categorieDAO.deleteCategorie(id);
    }
}