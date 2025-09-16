package main;
import java.util.Scanner;
import java.util.HashMap;
import java.util.UUID;
import metier.*;
import utils.Validator;
import utils.Comptfinder;
import utils.OpperationSource;
import utils.Destination;


public class main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int choix;
		double soulde ;
    	double supplémentaire ;
    	double InterestRate;
    	UUID acountID ;
    	UUID ReseveracountID ;
    	String input;
    	CompteCourant tempCompteCourant;
    	CompteEpargne tempCompteEpargne;
    	CompteCourant ReseverCompteCourant;
    	CompteEpargne ReseverCompteEpargne;
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
                    System.out.print("👉 Entrez l'UUID du compte : ");
                    sc.nextLine(); // pour vider le buffer si besoin
                    input = sc.nextLine();

                    try {
                        acountID = UUID.fromString(input);
                    } catch (IllegalArgumentException e) {
                        System.out.println("⚠️ UUID invalide !");
                        break;
                    }

                     tempCompteCourant = Comptfinder.CompteCourantFinder(acountID, coumpts);
                    tempCompteEpargne = Comptfinder.CompteEpargneFinder(acountID, coumpts);

                    if (tempCompteCourant == null && tempCompteEpargne == null) {
                        System.out.println("⚠️ Aucun compte trouvé avec cet UUID !");
                        break;
                    }

                    System.out.print("👉 Entrez le montant du versement : ");
                    double montant = sc.nextDouble();
                    sc.nextLine(); // vider le buffer

                    // --- Demander la source du versement ---
                    System.out.println("👉 Choisissez la source du versement : ");
                    System.out.println("1️⃣ Virement externe");
                    System.out.println("2️⃣ Dépôt espèces");
                    System.out.println("3️⃣ Salaire");
                    int choixSource = sc.nextInt();
                    sc.nextLine(); // vider le buffer

                    OpperationSource source = null;
                    switch (choixSource) {
                        case 1: source = OpperationSource.VIREMENT_EXTERNE; break;
                        case 2: source = OpperationSource.DEPOT_ESPECES; break;
                        case 3: source = OpperationSource.SALAIRE; break;
                        default:
                            System.out.println("⚠️ Choix invalide, versement annulé !");
                            break;
                    }
                    Versement versement = new Versement(montant, source);

                    if (tempCompteCourant != null) {
                        tempCompteCourant.setVersement(versement);
                        System.out.println("✅ Versement ajouté au Compte Courant.");
                    } else if (tempCompteEpargne != null) {
                        tempCompteEpargne.setVersement(versement);
                        System.out.println("✅ Versement ajouté au Compte Épargne.");
                    }
                    break;
                case 3:
                    System.out.println("🏧 Retrait d'un compte...");
                    System.out.print("👉 Entrez l'UUID du compte : ");
                    sc.nextLine(); // vider le buffer si besoin
                    input = sc.nextLine();

                    try {
                        acountID = UUID.fromString(input);
                    } catch (IllegalArgumentException e) {
                        System.out.println("⚠️ UUID invalide !");
                        break;
                    }

                    tempCompteCourant = Comptfinder.CompteCourantFinder(acountID, coumpts);
                    tempCompteEpargne = Comptfinder.CompteEpargneFinder(acountID, coumpts);
                    
                    if (tempCompteCourant == null && tempCompteEpargne == null) {
                        System.out.println("⚠️ Aucun compte trouvé avec cet UUID !");
                        break;
                    }
                    
                    System.out.print("👉 Entrez le montant du retrait : ");
                    montant = sc.nextDouble();
                    sc.nextLine(); // vider le buffer
                    
                    
                    int choixDestination = sc.nextInt();
                    sc.nextLine(); // vider le buffer
                    
                    Destination destination = null;
                    switch (choixDestination) {
                        case 1: destination = Destination.DISTRIBUTEUR_ATM; break;
                        case 2: destination = Destination.CHEQUE; break;
                        case 3: destination = Destination.VIREMENT_SORTANT; break;
                        default:
                            System.out.println("⚠️ Choix invalide, retrait annulé !");
                            break;
                    }
                    Retrait retrait = new Retrait(montant, destination);
                    
                    if (tempCompteCourant != null) {
                    	if (tempCompteCourant.retirer(montant)) {
                    		tempCompteCourant.setRetrait(retrait);
						}
                    	
                    } else if (tempCompteEpargne != null) {
                    	if (tempCompteEpargne.retirer(montant)) {
                    		tempCompteEpargne.setRetrait(retrait);
						}
                    }
                    break;
                case 4:
                    System.out.println("🔄 Virement entre comptes...");
                    System.out.print("👉 Entrez l'UUID du compte of the sender : ");
                    sc.nextLine(); // pour vider le buffer si besoin
                    input = sc.nextLine();
                    System.out.print("👉 Entrez l'UUID du compte resever : ");
                    sc.nextLine(); // pour vider le buffer si besoin
                    String secondinput = sc.nextLine();
                    
                    try {
                        acountID = UUID.fromString(input);
                        ReseveracountID = UUID.fromString(secondinput);
                    } catch (IllegalArgumentException e) {
                        System.out.println("⚠️ UUID invalide !");
                        break;
                    }
                    
                    
                    
                    
                    
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
