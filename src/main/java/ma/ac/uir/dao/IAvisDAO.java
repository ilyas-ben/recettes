package ma.ac.uir.dao;

import java.util.List;

import ma.ac.uir.model.Avis;

public interface IAvisDAO {

	void ajouterAvis(Avis avis);
	void modifierAvis(Avis avis);
	void supprimerAvis(int idAvisToDelete);
	void supprimerAvis(Avis avisToDelete);
	
	List<Avis> listerALLAvis();
	Avis getAvisById(Integer idAvis);

}
