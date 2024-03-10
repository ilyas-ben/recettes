package ma.ac.uir.presentation;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import ma.ac.uir.metier.IAvisService;
import ma.ac.uir.metier.IRecetteService;
import ma.ac.uir.metier.impl.AvisService;
import ma.ac.uir.metier.impl.RecetteService;
import ma.ac.uir.model.Avis;
import ma.ac.uir.model.Recette;
import ma.ac.uir.model.Utilisateur;

public class AvisAction extends ActionSupport {

	private Avis avis;

	private int idAvis;
	private String commentaire;
	private Integer rate;

	// services
	private IAvisService iAvisService;
	private IRecetteService iRecetteService;
	
	// autres parametres utilisés par les methodes de l'action
	private Utilisateur currentUser;
	private Recette recette;
	private int idRecette;

	public AvisAction() {
		super();
		System.out.println("construction");
		iAvisService = new AvisService();
		HttpSession session = ServletActionContext.getRequest().getSession();
		currentUser = (Utilisateur) session.getAttribute("user");
		session.setAttribute("user", (Utilisateur) session.getAttribute("user"));
		
		// ca aide a rediriger l'user vers la page de detail de recette
		if(idRecette != 0) {
			recette = iRecetteService.obtenirRecetteParId(idRecette);
			session.setAttribute("idRecette", idRecette);		
			}
	}

	public String ajouterAvis() {
		try {
			avis = new Avis(null, currentUser, rate, commentaire, null);
			System.out.println("commencer method");
			System.out.println("id rectte"+ idRecette);
			iAvisService.ajouterAvis(avis, idRecette,currentUser.getId());
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ERROR;
		}

	}

	public String supprimerAvis() {
		try {
			System.out.println("execution de supprimer methode");
			iAvisService.supprimerAvisById(idAvis);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String modifierAvis() {
		
		try {
			avis = new Avis(idAvis, rate, commentaire);
			iAvisService.modifierAvis(avis);
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ERROR;
		}
		
	}
	
	// getters & setters

	public Avis getAvis() {
		return avis;
	}

	public void setAvis(Avis avis) {
		this.avis = avis;
	}

	public Integer getIdAvis() {
		return idAvis;
	}

	public void setIdAvis(Integer idAvis) {
		this.idAvis = idAvis;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public IAvisService getiAvisService() {
		return iAvisService;
	}

	public void setiAvisService(IAvisService iAvisService) {
		this.iAvisService = iAvisService;
	}

	public IRecetteService getiRecetteService() {
		return iRecetteService;
	}

	public void setiRecetteService(IRecetteService iRecetteService) {
		this.iRecetteService = iRecetteService;
	}

	public Utilisateur getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(Utilisateur currentUser) {
		this.currentUser = currentUser;
	}

	public Recette getRecette() {
		return recette;
	}

	public void setRecette(Recette recette) {
		this.recette = recette;
	}

	public int getIdRecette() {
		return idRecette;
	}

	public void setIdRecette(int idRecette) {
		this.idRecette = idRecette;
	}

	public void setIdAvis(int idAvis) {
		this.idAvis = idAvis;
	}
	
	

}
