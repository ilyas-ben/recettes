package ma.ac.uir.presentation;

import com.opensymphony.xwork2.ActionSupport;

import ma.ac.uir.metier.ICategorieService;
import ma.ac.uir.metier.IIngredientService;
import ma.ac.uir.metier.IRecetteService;
import ma.ac.uir.metier.IUtilisateurService;
import ma.ac.uir.metier.impl.CategorieService;
import ma.ac.uir.metier.impl.IngredientService;
import ma.ac.uir.metier.impl.RecetteService;
import ma.ac.uir.metier.impl.UtilisateurService;
import ma.ac.uir.model.Avis;
import ma.ac.uir.model.Categorie;
import ma.ac.uir.model.Ingredient;
import ma.ac.uir.model.Recette;
import ma.ac.uir.model.Utilisateur;

import java.io.File;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

public class RecetteAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private IIngredientService iIngredientService;
	private ICategorieService categorieService;
	private IRecetteService recetteService;
	private IUtilisateurService iUtilisateurService;

	private Utilisateur currentUser;

	private String error;

	private Recette recette;
	// Paramètres de recette
	private byte[] recipImage;
	private Integer recetteId;
	private String recipImage64encoded;

	private String titre;
	private String description;
	private Integer tempsPreparation;
	private Categorie categorie;
	private Set<Ingredient> ingredients;

	// liste des total recettes
	private List<Recette> listeRecettes;
	private List<Recette> recettes;

	// Pour la gestion des messages ou erreurs
	private String message;
	private String actionContext;
	private static final Logger logger = LogManager.getLogger(RecetteAction.class);

	private Integer[] idIngredients;
	private Integer idCategorie;

	private File uploadedFile;
	private String uploadedFileName;
	private String localDirectory = "d:\\projetImages";

	List<Categorie> totalCategories; // pr affihcer la liste des categories
	List<Categorie> categories;
	List<Ingredient> totalIngredients; // pr afficher la liste des ingredients

	private File imageFile;

	HttpSession session;

	public RecetteAction() {
		super();
		categorieService = new CategorieService();
		iIngredientService = new IngredientService();
		recetteService = new RecetteService();
		iUtilisateurService = new UtilisateurService();

		session = ServletActionContext.getRequest().getSession();
		currentUser = (Utilisateur) session.getAttribute("user");
		currentUser = iUtilisateurService.obtenirUtilisateurParId(currentUser.getId());
		session.setAttribute("user", (Utilisateur) session.getAttribute("user"));

	}

	public String ajouterRecetteForm() {
		try {
			this.totalCategories = categorieService.listerToutesLesCategories();
			this.totalIngredients = iIngredientService.listerTousLesIngredients();

			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ERROR;
		}
	}

	public String ajouterRecetteSubmit() {
		try {

			byte[] fileBytes = Files.readAllBytes(uploadedFile.toPath());

			this.ingredients = new HashSet<Ingredient>();

			for (int idString : idIngredients) {
				this.ingredients.add(iIngredientService.obtenirIngredientParId(idString));
			}

			this.categorie = categorieService.obtenirCategorieParId(this.idCategorie);

			// this.recipImage = Files.readAllBytes(imageFile.toPath());

			this.recette = new Recette(null, fileBytes, this.titre, this.description, this.tempsPreparation,
					this.ingredients, this.currentUser, this.categorie);

			System.out.println("Recette à ajouter : " + recette.toString());

			recetteService.ajouterRecette(recette);
			return SUCCESS;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ERROR;
		}

	}

	public String getRecetteByName() {
		try {
			this.recettes = recetteService.getRecettesBySearchTitre(titre);
			System.out.println("trouvé  : ");
			for (Recette recette : recettes) {
				System.out.println(recette.toString());
			}
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ERROR;
		}

	}

	public String getRecetteById() {
		try {

			if (recetteId == null) {
				recetteId = (int) session.getAttribute("idRecette");
			}

			this.recipImage64encoded = recetteService.obtenirImage64EncodedDeRecetteParIdRecette(this.recetteId);
			this.recette = recetteService.obtenirRecetteParId(recetteId);

			session.setAttribute("idRecette", recetteId);
			return SUCCESS;
		} catch (Exception e) {
			addActionError("Erreur lors de l'obtention de la recette : " + e.getMessage());
			return ERROR;
		}
	}

	public String listerRecettes() {
		try {
			listeRecettes = recetteService.listerToutesLesRecettes();
			return SUCCESS;
		} catch (Exception e) {
			addActionError("Erreur lors de la liste des recettes : " + e.getMessage());
			return ERROR;
		}
	}

	public String mettreAjourRecetteForm() {
		try {
			this.recipImage64encoded = recetteService.obtenirImage64EncodedDeRecetteParIdRecette(this.recetteId);
			
			

			Recette recetteToUpdate = recetteService.obtenirRecetteParId(this.recetteId);
			System.out.println("in action, recette id is " + this.recetteId);
			System.out.println(recetteToUpdate.toString());
			this.totalCategories = categorieService.listerToutesLesCategories();
			this.totalIngredients = iIngredientService.listerTousLesIngredients();
			
			

			this.recette = recetteToUpdate;

			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ERROR;
		}
	}

	public String mettreAJourRecetteSubmit() {
		try {
			System.out.println("id categorie selectionnée" + idCategorie + "\ningredients selectionnés : ");
			
			byte[] imageRecette =null;
			
			if(uploadedFile!=null) imageRecette = Files.readAllBytes(uploadedFile.toPath());
			
			
			this.recette = new Recette(recetteId, imageRecette, titre, description, tempsPreparation, ingredients, currentUser, null);
			
			
			

			recetteService.mettreAJourRecette(recette, this.idCategorie, this.idIngredients);
			
			

			return SUCCESS;
		} catch (Exception e) {
			addActionError("Erreur lors de la mise à jour de la recette : " + e.getMessage());
			e.printStackTrace();
			return ERROR;
		}
	}

	public String supprimerRecetteSubmit() {
		try {
			recetteService.supprimerRecette(recetteId);
			System.out.println("suppression réussie");
			return SUCCESS;
		} catch (Exception e) {
			System.out.println("suppression failed");
			addActionError("Erreur lors de la suppression de la recette : " + e.getMessage());
			e.printStackTrace();
			return ERROR;
		}
	}

	private Integer userId; // Parametre de la methode ci dessous
	Set<Recette> myRecipes;

	public String myRecipes() {
		try {
			this.myRecipes = currentUser.getRecettes();
			logger.info("La méthode myRecipes a été exécutée avec succès.");
			return SUCCESS;
		} catch (Exception e) {
			logger.error("Erreur lors de l'exécution de la méthode myRecipes.", e);
			error = e.toString();
			e.printStackTrace();
			return ERROR;
		}
	}

	public String filtrerParCategorie() {
		try {
			recettes = recetteService.listerRecettesParCategorieId(idCategorie);
			categories = categorieService.listerToutesLesCategories();
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ERROR;
		}
	}

	// Getters et Setters

	public Recette getRecette() {
		return recette;
	}

	public RecetteAction(IIngredientService iIngredientService, ICategorieService categorieService,
			IRecetteService recetteService, IUtilisateurService iUtilisateurService, Utilisateur currentUser,
			String error, Recette recette, byte[] recipImage, Integer recetteId, String recipImage64encoded,
			String titre, String description, Integer tempsPreparation, Categorie categorie,
			Set<Ingredient> ingredients, List<Recette> listeRecettes, List<Recette> recettes, String message,
			String actionContext, Integer[] idIngredients, Integer idCategorie, List<Categorie> totalCategories,
			List<Ingredient> totalIngredients, File imageFile, HttpSession session, Integer userId,
			Set<Recette> myRecipes) {
		super();
		this.iIngredientService = iIngredientService;
		this.categorieService = categorieService;
		this.recetteService = recetteService;
		this.iUtilisateurService = iUtilisateurService;
		this.currentUser = currentUser;
		this.error = error;
		this.recette = recette;
		this.recipImage = recipImage;
		this.recetteId = recetteId;
		this.recipImage64encoded = recipImage64encoded;
		this.titre = titre;
		this.description = description;
		this.tempsPreparation = tempsPreparation;
		this.categorie = categorie;
		this.ingredients = ingredients;
		this.listeRecettes = listeRecettes;
		this.recettes = recettes;
		this.message = message;
		this.actionContext = actionContext;
		this.idIngredients = idIngredients;
		this.idCategorie = idCategorie;
		this.totalCategories = totalCategories;
		this.totalIngredients = totalIngredients;
		this.imageFile = imageFile;
		this.session = session;
		this.userId = userId;
		this.myRecipes = myRecipes;
	}

	public Integer[] getIdIngredients() {
		return idIngredients;
	}

	public String getRecipImage64encoded() {
		return recipImage64encoded;
	}

	public void setRecipImage64encoded(String recipImage64encoded) {
		this.recipImage64encoded = recipImage64encoded;
	}

	public static Logger getLogger() {
		return logger;
	}

	public void setRecipImage(byte[] recipImage) {
		this.recipImage = recipImage;
	}

	public void setIdIngredients(Integer[] idIngredients) {
		this.idIngredients = idIngredients;
	}

	public Set<Recette> getMyRecipes() {
		return myRecipes;
	}

	public void setMyRecipes(Set<Recette> myRecipes) {
		this.myRecipes = myRecipes;
	}

	public Integer getIdCategorie() {
		return idCategorie;
	}

	public File getImageFile() {
		return imageFile;
	}

	public void setImageFile(File imageFile) {
		this.imageFile = imageFile;
	}

	public void setIdCategorie(Integer idCategorie) {
		this.idCategorie = idCategorie;
	}

	public void setRecette(Recette recette) {
		this.recette = recette;
	}

	public int getRecetteId() {
		return recetteId;
	}

	public void setRecetteId(int recetteId) {
		this.recetteId = recetteId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public IRecetteService getRecetteService() {
		return recetteService;
	}

	public void setRecetteService(IRecetteService recetteService) {
		this.recetteService = recetteService;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public List<Recette> getRecettes() {
		return recettes;
	}

	public void setRecettes(List<Recette> recettes) {
		this.recettes = recettes;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getActionContext() {
		return actionContext;
	}

	public void setActionContext(String actionContext) {
		this.actionContext = actionContext;
	}

	public List<Recette> getListeRecettes() {
		return listeRecettes;
	}

	public void setListeRecettes(List<Recette> listeRecettes) {
		this.listeRecettes = listeRecettes;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public ICategorieService getCategorieService() {
		return categorieService;
	}

	public void setCategorieService(ICategorieService categorieService) {
		this.categorieService = categorieService;
	}

	public List<Categorie> getTotalCategories() {
		return totalCategories;
	}

	public void setTotalCategories(List<Categorie> totalCategories) {
		this.totalCategories = totalCategories;
	}

	public IIngredientService getiIngredientService() {
		return iIngredientService;
	}

	public void setiIngredientService(IIngredientService iIngredientService) {
		this.iIngredientService = iIngredientService;
	}

	public List<Ingredient> getTotalIngredients() {
		return totalIngredients;
	}

	public void setTotalIngredients(List<Ingredient> totalIngredients) {
		this.totalIngredients = totalIngredients;
	}

	public Utilisateur getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(Utilisateur currentUser) {
		this.currentUser = currentUser;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getTempsPreparation() {
		return tempsPreparation;
	}

	public void setTempsPreparation(Integer tempsPreparation) {
		this.tempsPreparation = tempsPreparation;
	}

	public Set<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public String getRecipImage() {
		return recipImage64encoded;
	}

	public void setRecipImage(String recipImage) {
		this.recipImage64encoded = recipImage;
	}

	public void setRecetteId(Integer recetteId) {
		this.recetteId = recetteId;
	}

	public File getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(File file) {
		this.uploadedFile = file;
	}

	public String getUploadedFileName() {
		return uploadedFileName;
	}

	public void setUploadedFileFileName(String name) {
		this.uploadedFileName = name;
	}

}