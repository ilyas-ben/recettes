package ma.ac.uir.metier.impl;

import java.util.List;

import ma.ac.uir.dao.IAvisDAO;
import ma.ac.uir.dao.impl.AvisDAO;
import ma.ac.uir.metier.IAvisService;
import ma.ac.uir.metier.IRecetteService;
import ma.ac.uir.metier.IUtilisateurService;
import ma.ac.uir.model.Avis;
import ma.ac.uir.model.Recette;
import ma.ac.uir.model.Utilisateur;

public class AvisService implements IAvisService{
	
	private IAvisDAO iAvisDAO;
	
	private IRecetteService iRecetteService;
	private IUtilisateurService iUtilisateurService;

	public AvisService() {
		super();
		this.iAvisDAO = new AvisDAO();
		this.iRecetteService = new RecetteService();
		this.iUtilisateurService= new UtilisateurService();
	}

	@Override
	public void ajouterAvis(Avis avis, Integer idRecette, Integer idUser) {
		System.out.println("entré dans service, dans service id Reccette "+ idRecette);
		Recette recette = iRecetteService.obtenirRecetteParId(idRecette);
		System.out.println("recuperé recette");
		avis.setRecette(recette);
		System.out.println("setted recette in avis");
		System.out.println("avis added "+avis.toString() );
		Utilisateur utilisateur = iUtilisateurService.obtenirUtilisateurParId(idUser);
		avis.setUtilisateur(utilisateur);
		iAvisDAO.ajouterAvis(avis);
		System.out.println("avis addeed");
	}

	@Override
	public void modifierAvis(Avis updatedAvis) {
		Avis avis = getAvisById(updatedAvis.getId());
		avis.setCommentaire(updatedAvis.getCommentaire());
		avis.setNoteSur5(updatedAvis.getNoteSur5());
		iAvisDAO.modifierAvis(avis);
	}

	@Override
	public void supprimerAvisById(int idAvisToDelete) {
		Avis avis = new Avis();
		avis = getAvisById(idAvisToDelete);
		avis.setRecette(null);
		avis.setUtilisateur(null);
		modifierAvis(avis);
		iAvisDAO.supprimerAvis(avis);	
	}

	@Override
	public List<Avis> listerALLAvis() {
		return iAvisDAO.listerALLAvis();
	}

	@Override
	public Avis getAvisById(Integer idAvis) {
		Avis avis = iAvisDAO.getAvisById(idAvis);
		return avis;
	}

}
