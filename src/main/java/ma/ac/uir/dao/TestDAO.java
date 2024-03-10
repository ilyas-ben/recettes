package ma.ac.uir.dao;

import java.util.List;
import java.util.Scanner;
import com.fasterxml.jackson.databind.ObjectMapper;


import ma.ac.uir.dao.impl.AvisDAO;
import ma.ac.uir.dao.impl.RecetteDAO;
import ma.ac.uir.metier.IRecetteService;
import ma.ac.uir.metier.IUtilisateurService;
import ma.ac.uir.metier.impl.RecetteService;
import ma.ac.uir.metier.impl.UtilisateurService;
import ma.ac.uir.model.Avis;
import ma.ac.uir.model.Recette;
import ma.ac.uir.model.Utilisateur;

public class TestDAO {

	public static void main(String[] args) {

        try {
        	// Supposons que vous ayez une liste d'objets Recette
        	List<Recette> listeRecettes = new RecetteService().listerToutesLesRecettes() ;
        	
        	// Créez un objet ObjectMapper
        	ObjectMapper objectMapper = new ObjectMapper();
            // Convertissez la liste en JSON
            String json = objectMapper.writeValueAsString(listeRecettes);

            // Faites quelque chose avec la chaîne JSON (envoi à une action Struts, enregistrement dans une base de données, etc.)
            System.out.println("JSON result: " + json);
        } catch (Exception e) {
            System.out.println(e.toString());
            // Gérez les erreurs de sérialisation ici
        }
    }

}
