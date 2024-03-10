 package ma.ac.uir.model;

import java.util.Set;

import javax.persistence.*;
 

@Entity
@Table(name = "TBL_CATEGORIE")
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "CAT_NOM")
    private String nom;

    
    // Constructors
    public Categorie() {
        // Default constructor
    	super();
    }

	

	public Categorie(int id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}



	

	

    
}