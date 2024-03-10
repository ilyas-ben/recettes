package ma.ac.uir.metier;

import java.util.List;

import ma.ac.uir.model.Avis;

public interface IAvisService {
	void ajouterAvis(Avis avis, Integer idRecette, Integer idUser);
	void modifierAvis(Avis updatedAvis);
	void supprimerAvisById(int idAvisToDelete);
	
	List<Avis> listerALLAvis();
	Avis getAvisById(Integer idAvis);
}
