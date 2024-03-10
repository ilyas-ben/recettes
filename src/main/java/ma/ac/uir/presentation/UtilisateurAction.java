package ma.ac.uir.presentation;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import ma.ac.uir.metier.impl.CategorieService;
import ma.ac.uir.metier.impl.RecetteService;
import ma.ac.uir.metier.impl.UtilisateurService;
import ma.ac.uir.metier.ICategorieService;
import ma.ac.uir.metier.IRecetteService;
import ma.ac.uir.metier.IUtilisateurService;
import ma.ac.uir.model.Categorie;
import ma.ac.uir.model.Recette;
import ma.ac.uir.model.Utilisateur;

public class UtilisateurAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(RecetteAction.class);

	private IUtilisateurService utilisateurService;
	private IRecetteService iRecetteService;
	private ICategorieService iCategorieService;

	// Paramètres utilisateur
	private Utilisateur utilisateur;
	private Integer id;
	private String email;
	private String motDePasse;
	private String nom;
	private List<Recette> recettes;

	Integer isNotDone = null;

	// Pour la gestion des messages ou erreurs
	private String message;

	private String actionContext;

	// parametres utilisés par methodes
	Utilisateur currentUser;
	List<Categorie> categories;
	
	
	

	public UtilisateurAction() {
		super();
		utilisateurService = new UtilisateurService();
		this.iRecetteService = new RecetteService();
		iCategorieService = new CategorieService();
	
		
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("user", (Utilisateur) session.getAttribute("user"));
		currentUser = (Utilisateur) session.getAttribute("user");

	}

	// Getters et Setters

	public int getId() {
		return id;
	}

	public List<Categorie> getCategories() {
		return categories;
	}

	public void setCategories(List<Categorie> categories) {
		this.categories = categories;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getActionContext() {
		return actionContext;
	}

	public IUtilisateurService getUtilisateurService() {
		return utilisateurService;
	}

	public void setUtilisateurService(IUtilisateurService utilisateurService) {
		this.utilisateurService = utilisateurService;
	}

	public IRecetteService getiRecetteService() {
		return iRecetteService;
	}

	public void setiRecetteService(IRecetteService iRecetteService) {
		this.iRecetteService = iRecetteService;
	}

	public List<Recette> getRecettes() {
		return recettes;
	}

	public void setRecettes(List<Recette> recettes) {
		this.recettes = recettes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setActionContext(String actionContext) {
		this.actionContext = actionContext;
	}

	public Set<Recette> getFavorites() {
		return favorites;
	}

	public void setFavorites(Set<Recette> favorites) {
		this.favorites = favorites;
	}

	public Integer getIsNotDone() {
		return isNotDone;
	}

	public void setIsNotDone(Integer isNotDone) {
		this.isNotDone = isNotDone;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	// Actions

	public String inscription() {
		try {
			Utilisateur newUser = new Utilisateur();
			newUser.setEmail(email);
			newUser.setNom(nom);
			newUser.setMotDePasse(motDePasse);
			utilisateurService.ajouterUtilisateur(newUser);
			this.message = "Inscription réussie";
			this.actionContext = "inscription-ok";
			return SUCCESS;
		} catch (Exception e) {
			this.message = "Erreur lors de l'inscription : " + e.getMessage();
			this.actionContext = "inscription-!ok";
			return ERROR;
		}
	}

	public String connexion() {
		try {
			Utilisateur user = utilisateurService.obtenirUtilisateurParEmail(email);
			if ((user != null && user.getMotDePasse().equals(motDePasse)) || currentUser != null) {
				HttpSession session = ServletActionContext.getRequest().getSession();
				if (user != null)
					session.setAttribute("user", user);

				if (currentUser != null) {
					currentUser = utilisateurService.obtenirUtilisateurParId(currentUser.getId());
					session.setAttribute("user", currentUser);
				}

				this.recettes = iRecetteService.listerToutesLesRecettes();
				this.categories = iCategorieService.listerToutesLesCategories();
				
				for(Categorie categorie : categories) {
					System.out.println(categorie.toString());
				}

				return SUCCESS;
			} else {
				this.message = "Email ou mot de passe incorrect";
				this.actionContext = "connexion-!ok";
				return ERROR;
			}
		} catch (Exception e) {
			this.message = "Erreur lors de la connexion : " + e.getMessage();
			this.actionContext = "connexion-!ok";
			return ERROR;
		}
	}

	// Action pour la mise à jour du profil
	public String miseAJourProfil() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		Utilisateur sessionUser = (Utilisateur) session.getAttribute("user");

		if (sessionUser != null) {
			try {
				// Mettre à jour l'utilisateur avec les nouvelles valeurs
				sessionUser.setNom(this.nom);
				sessionUser.setEmail(this.email);
				sessionUser.setMotDePasse(this.motDePasse);

				// Sauvegarder l'utilisateur dans la base de données
				utilisateurService.mettreAJourUtilisateur(sessionUser);

				// Mettre à jour l'utilisateur dans la session
				session.setAttribute("user", sessionUser);

				this.message = "Modification réussie";
				return SUCCESS;
			} catch (Exception e) {
				this.message = "Erreur lors de la mise à jour du profil : " + e.getMessage();
				this.actionContext = "miseAJourProfil-!ok";
				return ERROR;
			}
		} else {
			this.message = "Erreur : Utilisateur non connecté.";
			this.actionContext = "miseAJourProfil-!ok-session";
			return ERROR;
		}
	}

	// Action pour afficher les détails du profil
	public String afficherProfil() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		Utilisateur sessionUser = (Utilisateur) session.getAttribute("user");

		if (sessionUser != null) {
			this.id = sessionUser.getId();

			try {
				utilisateur = utilisateurService.obtenirUtilisateurParId(id);
				return SUCCESS;
			} catch (Exception e) {
				addActionError("Erreur lors de l'affichage du profil : " + e.getMessage());
				this.actionContext = "afficherProfil-!ok";
				return ERROR;
			}
		} else {
			addActionError("Erreur : Utilisateur non connecté.");
			this.actionContext = "afficherProfil-!ok-session";
			return ERROR;
		}
	}

	// Action pour la suppression d'un compte utilisateur
	public String supprimerCompte() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		Utilisateur sessionUser = (Utilisateur) session.getAttribute("user");

		if (sessionUser != null) {
			try {
				int userId = sessionUser.getId(); // Get the user ID from the session user
				utilisateurService.supprimerUtilisateur(userId);

				// Optionally, invalidate the session after account deletion
				session.invalidate();
				this.message = "Suppression réussie";
				this.actionContext = "supprimerCompte-ok";
				return SUCCESS;
			} catch (Exception e) {
				addActionError("Erreur lors de la suppression du compte : " + e.getMessage());
				this.actionContext = "supprimerCompte-!ok";
				return ERROR;
			}
		} else {
			addActionError("Erreur : Utilisateur non connecté ou session expirée.");
			this.actionContext = "supprimerCompte-!ok-session";
			return ERROR;
		}
	}

	public String logout() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		if (session != null) {
			try {
				session.invalidate(); // Invalidate the session to log out the user
				this.actionContext = "Utilisateur déconnecté";
				return SUCCESS;
			} catch (Exception e) {
				addActionError("Erreur lors de la déconnexion : " + e.getMessage());
				this.actionContext = "logout-!ok";
				return ERROR;
			}
		} else {
			addActionError("Erreur : Aucune session active trouvée.");
			this.actionContext = "logout!ok-session";
			return ERROR;
		}
	}
	
	public String ajouterFavoris() {
		try {
			utilisateurService.ajouterFavorisParSonIdRecette(this.id, currentUser);
			
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ERROR;
			
		}
	}

	private Set<Recette> favorites;

	public String ListerFavorites() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		Utilisateur sessionUser = (Utilisateur) session.getAttribute("user");
		try {
			this.favorites = sessionUser.getFavoris();

			for (Recette favoris : this.favorites) {
				System.out.println(favoris.toString());

			}

			if (this.favorites == null)
				System.err.println("sorry ! favoris attribute action is nul ! ");

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}

	}

	public String deleteFavorites() {
		try {
			utilisateurService.supprimerFavorisBySonId(this.id, currentUser);

			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			isNotDone = 1;
			e.printStackTrace();
			return ERROR;
		}
	}

}