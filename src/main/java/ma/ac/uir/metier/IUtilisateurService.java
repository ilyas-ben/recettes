package ma.ac.uir.metier;

import ma.ac.uir.model.Utilisateur;
import java.util.List;

public interface IUtilisateurService {
    void ajouterUtilisateur(Utilisateur utilisateur);
    Utilisateur obtenirUtilisateurParId(int id);
    Utilisateur obtenirUtilisateurParEmail(String email);
    List<Utilisateur> listerTousLesUtilisateurs();
    void mettreAJourUtilisateur(Utilisateur utilisateur);
    void supprimerUtilisateur(int id);
    
    void ajouterFavorisParSonIdRecette(int idFavorisToAdd, Utilisateur utilisateur);
    void supprimerFavorisBySonId(int idFavorisToDelete, Utilisateur utilisateur);
    void supprimerAllFavorisByUserId(int utilisateur);
    // Autres méthodes spécifiques liées à la gestion des utilisateurs
}