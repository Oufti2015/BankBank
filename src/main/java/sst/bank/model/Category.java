package sst.bank.model;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = {"criteria"})
public class Category implements Comparable<Category> {
    public static final String CREDIT = "Crédits";
    public static final String ASSURANCES = "Assurances";
    public static final String EPARGNE = "Epargne";
    public static final String VOYAGES = "Voyages";
    public static final String CADEAUX = "Cadeaux";
    public static final String CARBURANTS = "Carburants";
    public static final String ELECTRICITE = "Electricité";
    public static final String FRAIS_BANCAIRES = "Frais Bancaires";
    public static final String ABONNEMENTS = "Abonnements";
    public static final String EAU = "Eau";
    public static final String SALAIRE = "Salaire";
    public static final String ENTRETIEN_MAISON = "Entretien Maison";
    public static final String LOISIRS = "Loisirs";
    public static final String ALIMENTATION = "Alimentation";
    public static final String SOINS = "Soins";
    public static final String CHIENS = "Chiens";
    public static final String ELECTROMENAGER = "Electroménager";
    public static final String RESTAURANT = "Restaurant";
    public static final String HYGIENE = "Hygiène";
    public static final String TAXES = "TAXES";
    public static final String TELEPHONIE = "Téléphonie";
    public static final String ECOMMERCE = "Amazon";
    public static final String VOITURES = "Voitures";
    public static final String BCA = "BCA";
    public static final String OTHER_INCOME = "Autres Recettes";
    public static final String UNKNOWN = "Inconnu";

    private Integer priority = 50;
    private String name;
    private Boolean income = false;
    private Boolean savings = false;
    private final List<Criteria> criteria = new ArrayList<>();

    @Override
    public int compareTo(Category o) {
        return this.getName().compareTo(o.getName());
    }
}
