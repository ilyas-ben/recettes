package ma.ac.uir.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "TBL_INGREDIENT")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ING_NOM")
    private String nom;

    @Column(name = "ING_QUANTITE")
    private String quantite; // (ex: "1 tasse", "100g")

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinTable(
        name = "TBL_RECETTE_INGREDIENT",
        joinColumns = @JoinColumn(name = "ingredient_id"),
        inverseJoinColumns = @JoinColumn(name = "recette_id")
    )
    private Set<Recette> recettes;

    public Ingredient() {
        // Default constructor
    }

    public Ingredient(String nom, String quantite) {
        this.nom = nom;
        this.quantite = quantite;
    }

    // Getters and setters

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

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public Set<Recette> getRecettes() {
        return recettes;
    }

    public void setRecettes(Set<Recette> recettes) {
        this.recettes = recettes;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", quantite='" + quantite + '\'' +
                ", recettes=" + (recettes != null ? recettes.size() : "null") +
                '}';
    }
}