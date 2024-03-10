package ma.ac.uir.metier.impl;

import ma.ac.uir.dao.HibernateUtil;
import ma.ac.uir.dao.IRecetteDAO;
import ma.ac.uir.dao.IUtilisateurDAO;
import ma.ac.uir.dao.impl.RecetteDAO;
import ma.ac.uir.metier.IRecetteService;
import ma.ac.uir.metier.IUtilisateurService;
import ma.ac.uir.model.Categorie;
import ma.ac.uir.model.Ingredient;
import ma.ac.uir.model.Recette;
import ma.ac.uir.model.Utilisateur;

import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RecetteService implements IRecetteService {

	private IRecetteDAO iRecetteDAO = new RecetteDAO();

	private IUtilisateurService iUtilisateurService;

	// public RecetteService(IRecetteDAO recetteDAO) {
	// this.recetteDAO = recetteDAO;
	// }

	public RecetteService() {
		super();
		iUtilisateurService = new UtilisateurService();

	}

	@Override
	public void ajouterRecette(Recette recette) {
		iRecetteDAO.addRecette(recette);
	}

	@Override
	public Recette obtenirRecetteParId(int id) {
		return iRecetteDAO.getRecetteById(id);
	}

	@Override
	public List<Recette> listerToutesLesRecettes() {
		return iRecetteDAO.getAllRecettes();
	}

	@Override
	public void mettreAJourRecette(Recette recette) {
		iRecetteDAO.updateRecette(recette);
	}

	@Override
	public void supprimerRecette(int idRecetteToDelete) {
		Recette recetteASupprimer = iRecetteDAO.getRecetteById(idRecetteToDelete);
		// il faut supprimer la recette de toutes les liste de favoris de chaque user
		for (Utilisateur user : iUtilisateurService.listerTousLesUtilisateurs()) {
			iUtilisateurService.supprimerFavorisBySonId(idRecetteToDelete, user);
		}
		iRecetteDAO.deleteRecette(recetteASupprimer);
		// List<Commentaire> commentairesDeLaRecetteASupprimer = new
		// CommentaireService().

	}

	@Override
	public void mettreAJourRecette(Recette recetteDeLAction, Integer idCategorie, Integer[] idIngredients)
			throws Exception {

		Recette recetteDansDatabase = iRecetteDAO.getRecetteById(recetteDeLAction.getId());
		
		if(recetteDeLAction.getImage()==null) {
			recetteDeLAction.setImage(recetteDansDatabase.getImage());
		}

		if (idIngredients == null) {
			throw new Exception("Vous n'avez saisi aucun ingredient !! ");
		}

		

		Categorie nouvelleCategorie = null;
		if (idCategorie != null) {
			nouvelleCategorie = new CategorieService().obtenirCategorieParId(idCategorie);
			recetteDeLAction.setCategorie(nouvelleCategorie);
		}
		
		
		
		
		
//
//		// Preparation de la nouvelle liste des ingrédients
//		Set<Ingredient> nouvIngredients = new HashSet<Ingredient>();
//		for (int idIngredient : idIngredients) {
//			nouvIngredients.add(new IngredientService().obtenirIngredientParId(idIngredient));
//		}
//
//		// Si un nouvel ingredient va être ajouté à la recette
//
//		for (Ingredient ingredientDansLaNouvelleListe : nouvIngredients) {
//			boolean itsAlReadyOnRecipeList = false;
//
//			for (Ingredient ingredientDansLaRecette : recetteDansDataBase.getIngredients()) {
//				if (ingredientDansLaNouvelleListe.getId() == ingredientDansLaRecette.getId()) {
//					itsAlReadyOnRecipeList = true;
//					break;
//				}
//			}
//
//			if (!itsAlReadyOnRecipeList)
//				recetteDansDataBase.getIngredients().add(ingredientDansLaNouvelleListe);
//		}
//
//		// Si un Ingrediet va être supprimé de la recette
//
//		Ingredient ingredientASupprimer = null;
//
//		for (Ingredient ingredientDansLaRecette : recetteDansDataBase.getIngredients()) {
//			boolean itsAlReadyOnNewList = false;
//
//			for (Ingredient ingredientDansLaNouvelleListe : nouvIngredients) {
//				if (ingredientDansLaRecette.getId() == ingredientDansLaNouvelleListe.getId()) {
//					itsAlReadyOnNewList = true;
//					break;
//				}
//
//				if (!itsAlReadyOnNewList) {
//					ingredientASupprimer = ingredientDansLaRecette;
//				}
//			}
//		}
//
//		// TODO : avec les ID !!!!!!!
//
//		if (ingredientASupprimer != null)
//			recetteDansDataBase.getIngredients().remove(ingredientASupprimer);
//
//		if (nouvelleCategorie != null)
//			recetteDansDataBase.setCategorie(null);

		mettreAJourRecette(recetteDeLAction);

	}

	@Override
	public String obtenirImage64EncodedDeRecetteParIdRecette(int idRecette) {
		Recette recette = obtenirRecetteParId(idRecette);
		String imageEncoded = "aa";
		byte[] imageAEncoder = recette.getImage();
		if (imageAEncoder != null)
			imageEncoded = Base64.getEncoder().encodeToString(imageAEncoder);
		return imageEncoded;

	}

	@Override
	public List<Recette> getRecettesBySearchTitre(String nom) {
		return iRecetteDAO.getRecettesBySearchTitre("%" + nom + "%");
	}

	@Override
	public Set<Recette> listerRecettesDunUser(Integer userId) {
		return iRecetteDAO.getUserRecettes(userId);
	}

	@Override
	public List<Recette> listerRecettesParCategorieId(int idCategorie) {
		return iRecetteDAO.getRecettesByCategorieId(idCategorie);
	}

	@Override
	public List<Recette> filtrerRecettesParCategorieId(List<Recette> recettes, int idCategorie) {
		for (Recette recette : recettes) {
			if (recette.getCategorie().getId() != idCategorie)
				recettes.remove(recette);
		}
		return recettes;
	}

	@Override
	public Recette fromJson(String json) {

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.readValue(json, Recette.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}