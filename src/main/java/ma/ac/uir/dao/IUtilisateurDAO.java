package ma.ac.uir.dao;

import ma.ac.uir.model.Utilisateur;
import java.util.List;

public interface IUtilisateurDAO {
    void addUtilisateur(Utilisateur utilisateur);
    Utilisateur getUtilisateurById(int id);
    public Utilisateur getUtilisateurByEmail(String email);
    List<Utilisateur> getAllUtilisateurs();
    void updateUtilisateur(Utilisateur utilisateur);
    void deleteUtilisateur(int id);
    void deleteFavorisById(int idFavorisToDelete, Utilisateur utilisateur );
}