package ma.ac.uir.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "TBL_UTILISATEUR")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "USR_NOM")
    private String nom;

    @Column(name = "USR_EMAIL", unique = true)
    private String email;

    @Column(name = "USR_MDP")
    private String motDePasse;

    @OneToMany(
    		mappedBy = "utilisateur", 
    		cascade = {CascadeType.REMOVE, CascadeType.DETACH}, 
    		fetch = FetchType.EAGER)
    private Set<Recette> recettes;

    

    @ManyToMany(cascade = {CascadeType.REMOVE, CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinTable(
        name = "utilisateur_favoris_recette",
        joinColumns = @JoinColumn(name = "utilisateur_id"),
        inverseJoinColumns = @JoinColumn(name = "recette_id")
    )
    private Set<Recette> favoris;
    
    
    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinColumn(name="USR_AVIS")
    private Set<Avis> avis;

    public Utilisateur() {
    	super();
        // Default constructor
    }
    
    

    // Getters and setters

  



	public int getId() {
        return id;
    }

    public Utilisateur( String nom, String email, String motDePasse) {
		super();
		this.id = 0;
		this.nom = nom;
		this.email = email;
		this.motDePasse = motDePasse;
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

    public Set<Recette> getRecettes() {
        return recettes;
    }

    public void setRecettes(Set<Recette> recettes) {
        this.recettes = recettes;
    }

   
    public Set<Recette> getFavoris() {
        return favoris;
    }

    public void setFavoris(Set<Recette> favoris) {
        this.favoris = favoris;
    }



	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", nom=" + nom + ", email=" + email + ", motDePasse=" + motDePasse + "]";
	}



	
	



	

  
}