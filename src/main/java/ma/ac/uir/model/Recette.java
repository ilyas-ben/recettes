package ma.ac.uir.model;

import javax.persistence.*;

import java.util.Arrays;
import java.util.Set;

@Entity
@Table(name = "TBL_RECETTE")
public class Recette {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "REC_IMAGE", columnDefinition = "LONGBLOB")
	private byte[] image;

	@Column(name = "REC_TITRE")
	private String titre;

	@Column(name = "REC_DESCRIPTION", length = 1000)
	private String description;

	@Column(name = "REC_TEMPS")
	private Integer tempsPreparation;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.REMOVE, CascadeType.DETACH, CascadeType.MERGE }, mappedBy = "recettes")
	private Set<Ingredient> ingredients;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH })
	@JoinColumn(name = "UTILISATEUR_ID")
	private Utilisateur utilisateur; // User who created the recipe

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name = "CATEGORIE_ID")
	private Categorie categorie; // Categorie associated with the recipe

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "recette", cascade = { CascadeType.DETACH, CascadeType.MERGE })
	private Set<Avis> avis;

	// Constructors
	public Recette() {
		// Default constructor
		super();
	}

	// constructors full

	public Recette(Integer id, String titre, String description, Integer tempsPreparation, Set<Ingredient> ingredients,
			Utilisateur utilisateur, Categorie categorie) {
		super();
		this.id = id;
		this.titre = titre;
		this.description = description;
		this.tempsPreparation = tempsPreparation;
		this.ingredients = ingredients;
		this.utilisateur = utilisateur;
		this.categorie = categorie;
	}

	public Recette(Integer id, byte[] image, String titre, String description, Integer tempsPreparation,
			Set<Ingredient> ingredients, Utilisateur utilisateur, Categorie categorie) {
		super();
		this.id = id;
		this.image = image;
		this.titre = titre;
		this.description = description;
		this.tempsPreparation = tempsPreparation;
		this.ingredients = ingredients;
		this.utilisateur = utilisateur;
		this.categorie = categorie;
	}
	
	

	public Set<Avis> getAvis() {
		return avis;
	}

	public void setAvis(Set<Avis> avis) {
		this.avis = avis;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public void setTempsPreparation(int tempsPreparation) {
		this.tempsPreparation = tempsPreparation;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public Set<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public void setTempsPreparation(Integer tempsPreparation) {
		this.tempsPreparation = tempsPreparation;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	@Override
	public String toString() {
		return "Recette [id=" + id + ", image=" + Arrays.toString(image) + ", titre=" + titre + ", description="
				+ description + ", tempsPreparation=" + tempsPreparation + ", ingredients=" + ingredients
				+ ", utilisateur=" + utilisateur + ", categorie=" + categorie + "]";
	}

}