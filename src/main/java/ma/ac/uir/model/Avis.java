package ma.ac.uir.model;

import javax.persistence.*;

@Entity
@Table(name = "TBL_AVIS")
public class Avis {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "AVIS_ID_UTILISATEUR")
	private Utilisateur utilisateur;

	@Column(name = "AVIS_NOTE5")
	private int noteSur5;

	@Column(name = "AVIS_COMMENTAIRE")
	private String commentaire;

	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH})
	@JoinColumn(name = "AVIS_RECETTE_ID")
	private Recette recette;

	public Avis() {
		super();
	}

	public Avis(Integer id, Utilisateur utilisateur, int noteSur5, String commentaire, Recette recette) {
		super();
		this.id = id;
		this.utilisateur = utilisateur;
		this.noteSur5 = noteSur5;
		this.commentaire = commentaire;
		this.recette = recette;
	}

	public Avis(Integer id, Utilisateur utilisateur, int noteSur5, String commentaire) {
		super();
		this.id = id;
		this.utilisateur = utilisateur;
		this.noteSur5 = noteSur5;
		this.commentaire = commentaire;
	}

	public Avis(Integer id, int noteSur5, String commentaire, Recette recette) {
		super();
		this.id = id;
		this.noteSur5 = noteSur5;
		this.commentaire = commentaire;
		this.recette = recette;
	}

	public Avis(Integer id, int noteSur5, String commentaire) {
		super();
		this.id = id;
		this.noteSur5 = noteSur5;
		this.commentaire = commentaire;
	}

	// Getters and setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public int getNoteSur5() {
		return noteSur5;
	}

	public void setNoteSur5(int noteSur5) {
		this.noteSur5 = noteSur5;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Recette getRecette() {
		return recette;
	}

	public void setRecette(Recette recette) {
		this.recette = recette;
	}

	@Override
	public String toString() {
		return "Avis [Id=" + id + ", utilisateur=" + utilisateur + ", noteSur5=" + noteSur5 + ", commentaire="
				+ commentaire + ", recette=" + recette.getId() + "]";
	}

}
