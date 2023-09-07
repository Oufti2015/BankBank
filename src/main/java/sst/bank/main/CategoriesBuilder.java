package sst.bank.main;

import sst.bank.model.Category;
import sst.bank.model.Criteria;
import sst.bank.model.CriteriaField;

import java.util.*;

public class CategoriesBuilder {

    public CategoriesBuilder() {
    }

    public List<Category> build() {
        List<Category> result = new ArrayList<>();

        counterpartyAccount(result, Category.CREDIT, "BE36774229214981");
        counterpartyAccount(result, Category.CREDIT, "BE42001212214454");
        counterpartyAccount(result, Category.ASSURANCES, "BE24310114300038");
        counterpartyAccount(result, Category.ASSURANCES, "BE13651161857539");
        counterpartyAccount(result, Category.EPARGNE, "BE86240780456950");
        counterpartyAccount(result, Category.CADEAUX, "BE36063499913581");
        counterpartyAccount(result, Category.CARBURANTS, "LU460030728953320000");
        counterpartyAccount(result, Category.ELECTRICITE, "BE76335055459895");
        counterpartyAccount(result, Category.EAU, "BE71091010926269");
        counterpartyAccount(result, Category.EAU, "BE08096969000113");
        counterpartyAccount(result, Category.SALAIRE, "LU230020124792811300");
        counterpartyAccount(result, Category.ENTRETIEN_MAISON, "BE15001772474330");
        counterpartyAccount(result, Category.LOISIRS, "FR8330002070340000019686P96");
        counterpartyAccount(result, Category.SOINS, "LU890019100203005000");
        counterpartyAccount(result, Category.CHIENS, "BE46732675196136");
        counterpartyAccount(result, Category.CHIENS, "BE68001611318934");
        counterpartyAccount(result, Category.CHIENS, "BE19210063404912");
        counterpartyAccount(result, Category.ELECTROMENAGER, "NL09DEUT0265258561");
        counterpartyAccount(result, Category.TAXES, "BE09091215032457");
        counterpartyAccount(result, Category.TAXES, "BE36091215034881");
        counterpartyAccount(result, Category.TAXES, "BE28363058915820");
        counterpartyAccount(result, Category.TAXES, "BE86097180231050");
        counterpartyAccount(result, Category.TAXES, "BE30679200209111");
        counterpartyAccount(result, Category.ABONNEMENTS, "BE62375104253861");
        counterpartyAccount(result, Category.ABONNEMENTS, "BE88000325070541");
        counterpartyAccount(result, Category.TELEPHONIE, "BE82210000088968");
        counterpartyAccount(result, Category.CHIENS, "BE52001381746509");
        counterpartyAccount(result, Category.CHIENS, "BE96001799292305");
        counterpartyAccount(result, Category.VOYAGES, "LU510025151468565400");
        counterpartyAccount(result, Category.VOYAGES, "FR7610278050150003056724058");
        counterpartyAccount(result, Category.VOYAGES, "FR7610278056110002041320185");
        counterpartyAccount(result, Category.VOYAGES, "LU650090000055813117");
        counterpartyAccount(result, Category.VOYAGES, "LU830020128441585500");
        counterpartyAccount(result, Category.ENTRETIEN_MAISON, "BE31267007307755");
        counterpartyAccount(result, Category.ENTRETIEN_MAISON, "BE39363134178019");
        counterpartyAccount(result, Category.CHIENS, "BE54973028999697");
        counterpartyAccount(result, Category.CHIENS, "BE32068930722202");
        counterpartyAccount(result, Category.CHIENS, "BE32000433861802");
        counterpartyAccount(result, Category.CHIENS, "BE05000326024575");
        counterpartyAccount(result, Category.CHIENS, "BE84001677717959");
        counterpartyAccount(result, Category.CHIENS, "BE32000433861802");
        counterpartyAccount(result, Category.CHIENS, "BE14973056148583");
        counterpartyAccount(result, Category.CHIENS, "BE34271013711990");
        counterpartyAccount(result, Category.CHIENS, "BE64310026167252");
        counterpartyAccount(result, Category.CHIENS, "BE14973056148583");
        counterpartyAccount(result, Category.CHIENS, "BE05734062600775", "Bergers Belges");
        counterpartyAccount(result, Category.CHIENS, "BE96068202601705", "Bonheidense hv");
        counterpartyAccount(result, Category.CHIENS, "DE13585501300001104488", "Saarbruck");
        counterpartyAccount(result, Category.SOINS, "BE40210007955163");
        counterpartyAccount(result, Category.CADEAUX, "BE28063391529320");
        counterpartyAccount(result, Category.CADEAUX, "BE85063578223806", "Fabrice Nadin");
        counterpartyAccount(result, Category.CADEAUX, "BE26001103872629", "Ecole d'Alice");
        counterpartyAccount(result, Category.ASSURANCES, "BE64310180203252");
        counterpartyAccount(result, Category.BCA, "BE05063512144675");
        counterpartyAccount(result, Category.BCA, "BE71068906876669");
        counterpartyAccount(result, Category.BCA, "BE75001528405051");
        counterpartyAccount(result, Category.BCA, "BE03001418420084");
        counterpartyAccount(result, Category.VOYAGES, "LU230022154498867200");
        counterpartyAccount(result, Category.OTHER_INCOME, "LU581111008544080000");
        counterpartyAccount(result, Category.VOITURES, "BE37068908647628");


        detail(result, Category.VOYAGES, "LE PARADIS DES A");
        detail(result, Category.VOYAGES, "LE COMPAS D OR");
        detail(result, Category.VOYAGES, "OPERAPARIS");
        detail(result, Category.VOYAGES, "RATP FRANCE");
        detail(result, Category.VOYAGES, "CARS ON BOOKING");
        detail(result, Category.VOYAGES, "CFL PARKING LUXEMBOUR");
        detail(result, Category.VOYAGES, "EDREAMS");
        detail(result, Category.VOYAGES, "BORGO ANTICO");
        detail(result, Category.VOYAGES, "NOLEGGIARE SRL");
        detail(result, Category.VOYAGES, "PARK MONOPOLI");
        detail(result, Category.VOYAGES, "MAESTRALE DI LUCA");
        detail(result, Category.VOYAGES, "PAYPAL *EASYPARK");
        detail(result, Category.VOYAGES, "Q8 DEI F.LLI");
        detail(result, Category.VOYAGES, "GIULIANI GIAMBATTISTA");
        detail(result, Category.VOYAGES, "LIBELLULA GROUP");
        detail(result, Category.VOYAGES, "CARUCCI S. STAZ");
        detail(result, Category.VOYAGES, "ENI9360FBERNARD");
        detail(result, Category.VOYAGES, "HORODATEURSHOULE2365666");
        detail(result, Category.VOYAGES, "LA MERE CHAMPLAIN");
        detail(result, Category.VOYAGES, "DIFERTI FRANCE");
        detail(result, Category.VOYAGES, "SAS CAPARMOR");
        detail(result, Category.VOYAGES, "ALFORNO FRANCE");
        detail(result, Category.VOYAGES, "CREPERIE D ARMO");
        detail(result, Category.VOYAGES, "COTE BREIZH FRANCE");
        detail(result, Category.VOYAGES, "COMPTOIR DE LA");
        detail(result, Category.VOYAGES, "LES SARDINES");
        detail(result, Category.VOYAGES, "LA FEE DES GREVE");
        detail(result, Category.VOYAGES, "KEOLIS MONT ST M");
        detail(result, Category.VOYAGES, "ABB ST MICHEL");
        detail(result, Category.VOYAGES, "LE GOURVERNEUR FRANCE");
        detail(result, Category.VOYAGES, "LA TABLE MALOUI");
        detail(result, Category.VOYAGES, "LE 333 CAFE");
        detail(result, Category.VOYAGES, "LA TRINITAINE D");
        detail(result, Category.VOYAGES, "MAISONSARDINEDINARD");
        detail(result, Category.VOYAGES, "BIMAG ST MALO");
        detail(result, Category.VOYAGES, "SANEF");
        detail(result, Category.VOYAGES, "COFIROUTE");
        detail(result, Category.VOYAGES, "DELICES MALOUINS");
        detail(result, Category.VOYAGES, "LUFTHANSA");
        detail(result, Category.VOYAGES, "EFFIA");
        detail(result, Category.VOYAGES, "DYNEFF DAC");
        detail(result, Category.VOYAGES, "RYANAIR IRLANDE");
        detail(result, Category.VOYAGES, "EUROSPAR ESPAGNE", "Canaries");
        detail(result, Category.VOYAGES, "GAS ATTACK, S.L. ESPAGNE", "Canaries");
        detail(result, Category.TAXES, "FOD JUSTITIE");
        detail(result, Category.EPARGNE, "EASY SAVE");
        detail(result, Category.ABONNEMENTS, "NETFLIX");
        detail(result, Category.ABONNEMENTS, "SPOTIFY");
        detail(result, Category.ABONNEMENTS, "AMAZON MUSIC");
        detail(result, Category.ABONNEMENTS, "DISNEYPLUS");
        detail(result, Category.ABONNEMENTS, "BABBEL");
        detail(result, Category.ABONNEMENTS, "MICROSOFT");
        detail(result, Category.ABONNEMENTS, "ITUNESAPPST");
        detail(result, Category.CREDIT, "PAIEMENT A BANK CARD COMPANY");
        detail(result, Category.ALIMENTATION, "HELLOFRESH");
        detail(result, Category.ALIMENTATION, "FALIANGA");
        detail(result, Category.ALIMENTATION, "BOULANGERIE");
        detail(result, Category.ALIMENTATION, "CORA SA MESSANCY");
        detail(result, Category.ALIMENTATION, "NESPRESSOBE");
        detail(result, Category.ALIMENTATION, "AUCHAN LUXEMBOUR");
        detail(result, Category.ALIMENTATION, "AUCHAN MONT ST");
        detail(result, Category.ALIMENTATION, "AD DELHAIZE");
        detail(result, Category.ALIMENTATION, "ANIMOBOUTIK");
        detail(result, Category.ALIMENTATION, "BEERWULF ");
        detail(result, Category.ALIMENTATION, "HOLYPAYPAL", "Boissons Holy");
        detail(result, Category.ALIMENTATION, "MARCHE DES SAVEU");
        detail(result, Category.RESTAURANT, "ARAMARK");
        detail(result, Category.RESTAURANT, "FARINELLA", "Farinella");
        detail(result, Category.RESTAURANT, "RESTAURANT FARIN", "Farinella");
        detail(result, Category.RESTAURANT, "BURGER KING");
        detail(result, Category.RESTAURANT, "QUICK");
        detail(result, Category.RESTAURANT, "100 PATATES");
        detail(result, Category.RESTAURANT, "IL CASTELLO BORGHESE");
        detail(result, Category.RESTAURANT, "DA LEONI");
        detail(result, Category.RESTAURANT, "POKHARA LUXEMBOURG");
        detail(result, Category.RESTAURANT, "SUSHI LOVERS");
        detail(result, Category.RESTAURANT, "RESTAURANT LA TA", "La Table De Jo");
        detail(result, Category.RESTAURANT, "KBEEF LUXEMBOURG");
        detail(result, Category.RESTAURANT, "LE SOLEIL KABYLE", "La Couscoussiere");
        detail(result, Category.RESTAURANT, "LE PETIT BAIGNEU SENEFFE");
        detail(result, Category.RESTAURANT, "VITA DESIGN LUXEMBOUR", "Come A La Maison");
        detail(result, Category.RESTAURANT, "EJC ARLON ARLON", "Faubourg 101");
        detail(result, Category.RESTAURANT, "L'ENFANT GATE", "Habay");
        detail(result, Category.RESTAURANT, "ENTRE NOUS", "Virton");
        detail(result, Category.RESTAURANT, "RESTAURANT L'ALTRO");
        detail(result, Category.RESTAURANT, "TRATTORIA MTT HABAY-LA-VIEI");
        detail(result, Category.ENTRETIEN_MAISON, "XLG HOME LIBRAMONT");
        detail(result, Category.ENTRETIEN_MAISON, "ENMOINS");
        detail(result, Category.ENTRETIEN_MAISON, "PLAN-IT");
        detail(result, Category.ENTRETIEN_MAISON, "FIXPART");
        detail(result, Category.HYGIENE, "900CARE");
        detail(result, Category.TELEPHONIE, "PROXIMUS");
        detail(result, Category.ELECTROMENAGER, "COOLBLUE");
        detail(result, Category.ECOMMERCE, "AMAZON EU SARL");
        detail(result, Category.ECOMMERCE, "AMAZON PAYMENTS");
        detail(result, Category.ECOMMERCE, "AMZN MKTP FR");
        detail(result, Category.ECOMMERCE, "AMAZON MEDIA");
        detail(result, Category.ECOMMERCE, "AMAZON.FR");
        detail(result, Category.ECOMMERCE, "AMZ*AIGOTECH");
        detail(result, Category.ECOMMERCE, "AMZ*YONGLAIEU");
        detail(result, Category.ECOMMERCE, "AMZ*PYRJIN");
        detail(result, Category.ECOMMERCE, "AMAZON PRIME");
        detail(result, Category.ECOMMERCE, "AMAZONRETAIL");
        detail(result, Category.ECOMMERCE, "AMAZONMKTPLC");
        detail(result, Category.ECOMMERCE, "DIGITALRIVE DR ALLEMAGNE", "Logitech");
        detail(result, Category.ECOMMERCE, "AMANDEIA", "Arnaque");
        detail(result, Category.CHIENS, "ZOOPLUS SE");
        detail(result, Category.CHIENS, "OF SHADOW IN LIGH");
        detail(result, Category.CHIENS, "WORLDAGILIT");
        detail(result, Category.CHIENS, "DECATHLON");
        detail(result, Category.CHIENS, "CRAFTYFOX");
        detail(result, Category.CADEAUX, "SPRL L ANCOLIE");
        detail(result, Category.LOISIRS, "EMISYS");
        detail(result, Category.LOISIRS, "PARKING");
        detail(result, Category.LOISIRS, "NATIONALE LOTERI");
        detail(result, Category.SOINS, "FAMILIA 15 HALAN");
        detail(result, Category.SOINS, "HARMONIE 2 MESSANCY");
        detail(result, Category.SOINS, "CABINET DR BECK & ASSOCIE", "Dentiste");
        detail(result, Category.SOINS, "DR. KOONEN");
        detail(result, Category.SOINS, "OPTIQUE DEBOULLE", "La Lunetterie");
        detail(result, Category.SOINS, "PHARMACIE DU KIR");
        detail(result, Category.SOINS, "NEWPHARMA");
        detail(result, Category.SOINS, "PARTENA MUTUALITE LIBRE");
        detail(result, Category.SOINS, "DR PAOLO CECCHINI", "Ophtalmo");
        detail(result, Category.VOITURES, "MULLER PNEUS LUXEMBOURG");
        detail(result, Category.CARBURANTS, "ESSO RODANGE");
        detail(result, Category.CARBURANTS, "TOTAL N");
        detail(result, Category.CARBURANTS, "SHELL STEINFORT");
        detail(result, Category.BCA, "APPART CITY ARLON");
        detail(result, Category.BCA, "BRICO");
        detail(result, Category.BCA, "AVA MESSANCY");
        detail(result, Category.BCA, "ETS. THEIS");
        detail(result, Category.SOINS, "FARMALINE");
        detail(result, Category.CHIENS, "GOLDMEDICS");
        detail(result, Category.CHIENS, "PETNESS");
        detail(result, Category.VOITURES, "CARGLASS");
        detail(result, Category.VOITURES, "AUTOSECURITE");
        detail(result, Category.VOITURES, "J.A.R. MOTOR SA ARLON", "Llorens");

        transactionType(result, Category.FRAIS_BANCAIRES, "Coûts opérations diverses");
        transactionType(result, Category.FRAIS_BANCAIRES, "Intérêts du compte à vue");
        transactionType(result, Category.FRAIS_BANCAIRES, "Frais liés au compte");

        result.stream()
                .filter(category -> Arrays.asList(Category.SALAIRE, Category.OTHER_INCOME).contains(category.getName()))
                .forEach(category -> category.setIncome(true));
        result.stream()
                .filter(category -> Objects.equals(Category.EPARGNE, category.getName()))
                .forEach(category -> category.setSavings(true));

        all(result, Category.UNKNOWN);

        return result;
    }

    //LE PARADIS DES A
    private void counterpartyAccount(List<Category> categories, String categoryName, String criteria, String comment) {
        createCategory(categories, categoryName, criteria, CriteriaField.COUNTERPARTY_ACCOUNT, comment);
    }

    private void counterpartyAccount(List<Category> categories, String categoryName, String criteria) {
        createCategory(categories, categoryName, criteria, CriteriaField.COUNTERPARTY_ACCOUNT);
    }

    private void detail(List<Category> categories, String categoryName, String criteria) {
        createCategory(categories, categoryName, criteria, CriteriaField.DETAIL);
    }

    private void detail(List<Category> categories, String categoryName, String criteria, String comment) {
        createCategory(categories, categoryName, criteria, CriteriaField.DETAIL, comment);
    }

    private void transactionType(List<Category> categories, String categoryName, String criteria) {
        createCategory(categories, categoryName, criteria, CriteriaField.TRANSACTION_TYPE);
    }

    private void all(List<Category> categories, String categoryName) {
        createCategory(categories, categoryName, null, CriteriaField.ALL, 99);
    }

    private static void createCategory(List<Category> categories, String categoryName, String criteria, CriteriaField counterpartyAccount) {
        createCategory(categories, categoryName, criteria, counterpartyAccount, 50);
    }

    private static void createCategory(List<Category> categories, String categoryName, String criteria, CriteriaField counterpartyAccount, int priority) {
        final Category category = findCategory(categories, categoryName);
        category.getCriteria().add(new Criteria(criteria, counterpartyAccount));
        category.setPriority(priority);
    }

    private static void createCategory(List<Category> categories, String categoryName, String criteria, CriteriaField counterpartyAccount, String comment) {
        final Category category = findCategory(categories, categoryName);
        final Criteria e = new Criteria(criteria, counterpartyAccount);
        e.setComment(comment);
        category.getCriteria().add(e);
    }

    private static Category findCategory(List<Category> categories, String categoryName) {
        final Optional<Category> first = categories.stream().filter(c -> categoryName.equals(c.getName())).findFirst();
        Category category;
        if (first.isEmpty()) {
            category = new Category();
            category.setName(categoryName);
            categories.add(category);
        } else {
            category = first.get();
        }
        return category;
    }
}
