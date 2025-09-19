package ui;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import metier.*;
import utils.OpperationSource;
import utils.Validator;
import java.util.Scanner;
import utils.Comptfinder;

public final class Components {
	private static CompteCourant tempCompteCourant = null;
	private static CompteEpargne tempCompteEpargne = null;
	private static List<Retrait> retraitlist = null;
	private static List<Versement> versementslist = null;
	private static Scanner sc = new Scanner(System.in);
	
//	LSO means Liste des opérations
	 public static boolean LSO(HashMap<UUID, Object> coumpts ,UUID acountID) {
	        if (!Validator.doeaObjectExist(coumpts, acountID)) {
	            System.out.println("⚠️ Ce compte n'existe pas.");
	            return false;
	        }
	        
	        if (coumpts.get(acountID) instanceof CompteCourant) {
	            tempCompteCourant = (CompteCourant) coumpts.get(acountID);
	            retraitlist = tempCompteCourant.getRetraitList();
	            versementslist = tempCompteCourant.getVersement();
	            
	            System.out.println("📜 Liste des retraits de ce compte :");
	            if (retraitlist.isEmpty()) {
	                System.out.println("👉 Aucun retrait trouvé.");
	            } else {
	                retraitlist.forEach((e) -> {
	                    System.out.println("🔹 ID de l’opération : " + e.getNumero());
	                    System.out.println("   Montant : " + e.getMontant());
	                    System.out.println("   Date : " + e.getOperationDate());
	                    System.out.println("   Destination : " + e.getDestination());
	                });
	            }

	            System.out.println("📜 Liste des versements de ce compte :");
	            if (versementslist.isEmpty()) {
	                System.out.println("👉 Aucun versement trouvé.");
	            } else {
	                versementslist.forEach((e) -> {
	                    System.out.println("🔹 ID de l’opération : " + e.getNumero());
	                    System.out.println("   Montant : " + e.getMontant());
	                    System.out.println("   Date : " + e.getOperationDate());
	                });
	            }
	        }
	        
	        
	        if (coumpts.get(acountID) instanceof CompteEpargne) {
	            tempCompteEpargne = (CompteEpargne) coumpts.get(acountID);
	            retraitlist = tempCompteCourant.getRetraitList();
	            versementslist = tempCompteCourant.getVersement();

	            System.out.println("📜 Liste des retraits de ce compte :");
	            if (retraitlist.isEmpty()) {
	                System.out.println("👉 Aucun retrait trouvé.");
	            } else {
	                retraitlist.forEach((e) -> {
	                    System.out.println("🔹 ID de l’opération : " + e.getNumero());
	                    System.out.println("   Montant : " + e.getMontant());
	                    System.out.println("   Date : " + e.getOperationDate());
	                    System.out.println("   Destination : " + e.getDestination());
	                });
	            }

	            System.out.println("📜 Liste des versements de ce compte :");
	            if (versementslist.isEmpty()) {
	                System.out.println("👉 Aucun versement trouvé.");
	            } else {
	                versementslist.forEach((e) -> {
	                    System.out.println("🔹 ID de l’opération : " + e.getNumero());
	                    System.out.println("   Montant : " + e.getMontant());
	                    System.out.println("   Date : " + e.getOperationDate());
	                });
	            }
	        }
	        
	        return true;
	    }
//	 CCM means create Compte Courant
	 public static CompteCourant CCM () {
		 System.out.println("✅ Vous avez choisi un Compte Courant.");
			System.out.println("💵 Entrez le solde initial : ");
			double soulde = Validator.askPositiveDouble("");
			System.out.println("📉 Entrez le découvert autorisé (montant positif) : ");
			double decouvert = Validator.asknegativeDouble("");
			tempCompteCourant = new CompteCourant(soulde, decouvert);
			UUID acountID = tempCompteCourant.getCode();
			System.out.println("you have create and account");
			return tempCompteCourant;
	 }
	 public static CompteEpargne CCE () {
			System.out.println("✅ Vous avez choisi un Compte Épargne.");
			System.out.println("💵 Entrez le solde initial : ");
			double soulde = Validator.askPositiveDouble("");
			System.out.println("📈 Entrez le taux d’intérêt (ex: 0.05 pour 5%) : ");
			double InterestRate = Validator.askInterestRate("");
			tempCompteEpargne = new CompteEpargne(soulde, InterestRate);
			
			
			return tempCompteEpargne ;
	 }
	// ---------------- Versement ----------------
	    public static void Versement(HashMap<UUID, Object> coumpts) {
	        UUID acountID = null;
	        String input;
	        Versement versement;

	        System.out.println("💵 Versement dans un compte...");
	        System.out.print("👉 Entrez l'UUID du compte : ");
	        sc.nextLine(); // vider le buffer si besoin
	        input = sc.nextLine();

	        try {
	            acountID = UUID.fromString(input);
	        } catch (IllegalArgumentException e) {
	            System.out.println("⚠️ UUID invalide !");
	            return;
	        }

	        tempCompteCourant = Comptfinder.CompteCourantFinder(acountID, coumpts);
	        tempCompteEpargne = Comptfinder.CompteEpargneFinder(acountID, coumpts);

	        if (tempCompteCourant == null && tempCompteEpargne == null) {
	            System.out.println("⚠️ Aucun compte trouvé avec cet UUID !");
	            return;
	        }

	        System.out.print("👉 Entrez le montant du versement : ");
	        double montant = sc.nextDouble();
	        sc.nextLine(); // vider le buffer

	        // --- Choix de la source ---
	        System.out.println("👉 Choisissez la source du versement : ");
	        System.out.println("1️⃣ Virement externe");
	        System.out.println("2️⃣ Dépôt espèces");
	        System.out.println("3️⃣ Salaire");
	        int choixSource = sc.nextInt();
	        sc.nextLine(); // vider le buffer

	        OpperationSource source = null;
	        switch (choixSource) {
	            case 1:
	                source = OpperationSource.VIREMENT_EXTERNE;
	                break;
	            case 2:
	                source = OpperationSource.DEPOT_ESPECES;
	                break;
	            case 3:
	                source = OpperationSource.SALAIRE;
	                break;
	            default:
	                System.out.println("⚠️ Choix invalide, versement annulé !");
	                return;
	        }

	        versement = new Versement(montant, source);

	        if (tempCompteCourant != null) {
	            tempCompteCourant.setVersement(versement);
	            System.out.println("✅ Versement ajouté au Compte Courant.");
	        } else if (tempCompteEpargne != null) {
	            tempCompteEpargne.setVersement(versement);
	            System.out.println("✅ Versement ajouté au Compte Épargne.");
	        }
	    }

	}