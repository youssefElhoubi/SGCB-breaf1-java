package main;
import java.util.Scanner;
import java.util.HashMap;
import java.util.UUID;
import metier.*;
import utils.Validator;

public class main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int choix;
		double soulde ;
    	double supplémentaire ;
    	double InterestRate;
    	UUID acountID ;
    	CompteCourant tempCompteCourant;
    	CompteEpargne tempCompteEpargne;
		HashMap<UUID,Object> coumpts = new HashMap<UUID, Object>() ;
		

        do {
        	System.out.flush();
            System.out.println("=====================================");
            System.out.println("         💰 MENU BANCAIRE 💰");
            System.out.println("=====================================");
            System.out.println("1️⃣  Créer un compte (Courant ou Épargne)");
            System.out.println("2️⃣  Effectuer un versement dans un compte");
            System.out.println("3️⃣  Effectuer un retrait d'un compte");
            System.out.println("4️⃣  Effectuer un virement entre comptes");
            System.out.println("5️⃣  Consulter le solde du compte");
            System.out.println("6️⃣  Consulter la liste des opérations");
            System.out.println("0️⃣  Quitter");
            System.out.println("=====================================");
            System.out.print("👉 Votre choix : ");

            choix = sc.nextInt();

            switch (choix) {
                case 1:
                	System.out.println("🆕 Création d'un compte...");
                    System.out.println("=====================================");
                    System.out.println("1️⃣  Compte Courant");
                    System.out.println("2️⃣  Compte Épargne");
                    System.out.print("👉 Choisissez le type de compte : ");

                    int typeCompte = sc.nextInt();
                    sc.nextLine();

                    switch (typeCompte) {
                        case 1:
                        	System.out.println("✅ Vous avez choisi un Compte Courant.");
                        	System.out.println("💵 Entrez le solde initial : ");
                        	 soulde = Validator.askPositiveDouble("");
                        	 sc.nextLine(); // clear buffer
                        	 System.out.println("📉 Entrez le découvert autorisé (montant positif) : ");
                        	 supplémentaire = Validator.asknegativeDouble("");
                        	 sc.nextLine(); // clear buffer
                        	tempCompteCourant = new CompteCourant(soulde,supplémentaire);
                        	acountID = tempCompteCourant.getCode();
                        	coumpts.put(acountID, tempCompteCourant);
                        	tempCompteCourant =null;
                            // Ici : demander infos (solde initial, découvert autorisé, etc.)
                            break;
                        case 2:
                        	System.out.println("✅ Vous avez choisi un Compte Épargne.");
                        	System.out.println("💵 Entrez le solde initial : ");
                        	 soulde = Validator.askPositiveDouble("");
                        	 sc.nextLine(); // clear buffer
                        	 System.out.println("📈 Entrez le taux d’intérêt (ex: 0.05 pour 5%) : ");
                        	 InterestRate = Validator.askInterestRate("");
                        	 sc.nextLine(); // clear buffer
                        	 tempCompteEpargne = new CompteEpargne(soulde,InterestRate);
                        	acountID = tempCompteEpargne.getCode();
                        	coumpts.put(acountID, tempCompteEpargne);
                        	tempCompteEpargne =null;
                            break;
                        default:
                            System.out.println("⚠️ Choix invalide pour le type de compte.");
                    }
                    break;
                case 2:
                    System.out.println("💵 Versement dans un compte...");
                    // logiqe versement
                    break;
                case 3:
                    System.out.println("🏧 Retrait d'un compte...");
                    // logiqe retrait
                    break;
                case 4:
                    System.out.println("🔄 Virement entre comptes...");
                    // logiqe virement
                    break;
                case 5:
                    System.out.println("📊 Consultation du solde...");
                    // logiqe solde
                    break;
                case 6:
                    System.out.println("📜 Liste des opérations...");
                    // logiqe opérations
                    break;
                case 0:
                    System.out.println("👋 Merci d'avoir utilisé notre banque !");
                    break;
                default:
                    System.out.println("⚠️ Choix invalide, veuillez réessayer.");
            }

            System.out.println(); // ligne vide pour l'esthétique
        } while (choix != 0);

        sc.close();
	}

}
