package ui;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import metier.*;
import utils.Validator;

public final class Components {
	private static CompteCourant tempCompteCourant = null;
	private static CompteEpargne tempCompteEpargne = null;
	private static List<Retrait> retraitlist = null;
	private static List<Versement> versementslist = null;
	
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

	}