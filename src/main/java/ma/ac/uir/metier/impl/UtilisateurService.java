package ma.ac.uir.metier.impl;

import ma.ac.uir.dao.IUtilisateurDAO;
import ma.ac.uir.dao.impl.RecetteDAO;
import ma.ac.uir.dao.impl.UtilisateurDAO;
import ma.ac.uir.metier.IRecetteService;
import ma.ac.uir.metier.IUtilisateurService;
import ma.ac.uir.model.Recette;
import ma.ac.uir.model.Utilisateur;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UtilisateurService implements IUtilisateurService {

	private IUtilisateurDAO utilisateurDAO;

	

	public UtilisateurService() {
		super();
		utilisateurDAO = new UtilisateurDAO();

	}

	@Override
	public void ajouterUtilisateur(Utilisateur utilisateur) {
		// Ici, vous pouvez ajouter de la logique métier avant de sauvegarder
		// l'utilisateur
		utilisateurDAO.addUtilisateur(utilisateur);
	}

	@Override
	public Utilisateur obtenirUtilisateurParId(int id) {
		// Logique métier supplémentaire si nécessaire
		return utilisateurDAO.getUtilisateurById(id);
	}

	@Override
	public Utilisateur obtenirUtilisateurParEmail(String email) {
		return utilisateurDAO.getUtilisateurByEmail(email);
	}

	@Override
	public List<Utilisateur> listerTousLesUtilisateurs() {
		// Logique métier supplémentaire si nécessaire
		return utilisateurDAO.getAllUtilisateurs();
	}

	@Override
	public void mettreAJourUtilisateur(Utilisateur utilisateur) {
		// Valider les changements et appliquer la logique métier
		utilisateurDAO.updateUtilisateur(utilisateur);
	}

	@Override
	public void supprimerUtilisateur(int id) {
		// Logique métier pour la suppression, si nécessaire
		utilisateurDAO.deleteUtilisateur(id);
	}

	@Override
	public void supprimerFavorisBySonId(int idFavorisToDelete, Utilisateur utilisateur) {
		// TODO Auto-generated method stub

		for (Recette recette : utilisateur.getFavoris()) {
			if (recette.getId() == idFavorisToDelete) {
				System.out.println(utilisateur.getFavoris().remove(recette));
				break;
			}
		}

		mettreAJourUtilisateur(utilisateur);

	}

	@Override
	public void ajouterFavorisParSonIdRecette(int idFavorisToAdd, Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		Recette favorisAAjouter = new RecetteService().obtenirRecetteParId(idFavorisToAdd);
		utilisateur.getFavoris().add(favorisAAjouter);
		mettreAJourUtilisateur(utilisateur);
	}

	@Override
	public void supprimerAllFavorisByUserId(int utilisateur) {
		// TODO Auto-generated method stub
		// do nothig  just forget
	}

	

	// Autres méthodes spécifiques liées à la gestion des utilisateurs
}