package main;
import java.util.Scanner;
import java.util.HashMap;
import java.util.List;
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
    	UUID acountID = null ;
    	UUID ReseveracountID ;
    	String input;
    	CompteCourant tempCompteCourant = null;
    	CompteEpargne tempCompteEpargne= null;
    	CompteCourant ReseverCompteCourant= null;
    	CompteEpargne ReseverCompteEpargne = null;
    	Retrait retrait = null;
    	Destination destination = null;
    	Versement versement = null;
		HashMap<UUID,Object> coumpts = new HashMap<UUID, Object>() ;
		List<Retrait> retraitlist = null;
		List<Versement> versementslist = null;
		

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

                    int typeCompte = Validator.isBetween(1, 2);
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
                     versement = new Versement(montant, source);

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
                    
                    
                    switch (choixDestination) {
                        case 1: destination = Destination.DISTRIBUTEUR_ATM; break;
                        case 2: destination = Destination.CHEQUE; break;
                        case 3: destination = Destination.VIREMENT_SORTANT; break;
                        default:
                            System.out.println("⚠️ Choix invalide, retrait annulé !");
                            break;
                    }
                     retrait = new Retrait(montant, destination);
                    
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
                    if (coumpts.get(acountID) instanceof CompteCourant) {
						tempCompteCourant = (CompteCourant) coumpts.get(acountID); 
					}
                    if (coumpts.get(acountID) instanceof CompteEpargne) {
						tempCompteEpargne = (CompteEpargne) coumpts.get(acountID); 
					}
                    if (coumpts.get(ReseveracountID) instanceof CompteCourant) {
                    	ReseverCompteCourant = (CompteCourant) coumpts.get(acountID); 
					}
                    if (coumpts.get(ReseveracountID) instanceof CompteEpargne) {
                    	ReseverCompteEpargne = (CompteEpargne) coumpts.get(acountID); 
					}
                    System.out.print("👉 Entrez le monton de Virement : ");
                    double vermont = Validator.asknegativeDouble("");
                    
                    retrait =  new Retrait(vermont, Destination.VIREMENT_SORTANT);
//                    sender
                    if (tempCompteCourant != null) {
                    	if (tempCompteCourant.retirer(vermont)) {
                    		tempCompteCourant.setRetrait(retrait);
						}
                    	
                    } else if (tempCompteEpargne != null) {
                    	if (tempCompteEpargne.retirer(vermont)) {
                    		tempCompteEpargne.setRetrait(retrait);
						}
                    }
//                    resever
                    versement = new Versement(vermont*-1,OpperationSource.VIREMENT_EXTERNE);
                    if (ReseverCompteCourant != null) {
                    	if (ReseverCompteCourant.retirer(vermont*-1)) {
                    		ReseverCompteCourant.setVersement(versement);
						}
                    	
                    } else if (ReseverCompteEpargne != null) {
                    	if (ReseverCompteEpargne.retirer(vermont*-1)) {
                    		ReseverCompteEpargne.setVersement(versement);
						}
                    }
                    System.out.println("✅ Virement de " + vermont + " effectué avec succès !");
                    break;
                case 5:
                    System.out.println("📊 Consultation du solde...");
                    System.out.print("👉 Entrez l'UUID du compte : ");
                    sc.nextLine(); // vider le buffer
                    String inputUUID = sc.nextLine();

                    
                    try {
                    	acountID = UUID.fromString(inputUUID);
                    } catch (IllegalArgumentException e) {
                        System.out.println("⚠️ UUID invalide !");
                        break;
                    }

                    // --- Récupérer le compte ---
                    Object compte = coumpts.get(acountID);
                    
                    if (coumpts.get(acountID) instanceof CompteCourant) {
						tempCompteCourant = (CompteCourant) coumpts.get(acountID); 
						System.out.println("💰 Solde du compte [" + tempCompteCourant.getCode() + "] : " + tempCompteCourant.getSolde() + " €");
					}
                    if (coumpts.get(acountID) instanceof CompteEpargne) {
						tempCompteEpargne = (CompteEpargne) coumpts.get(acountID); 
						System.out.println("💰 Solde du compte [" + tempCompteCourant.getCode() + "] : " + tempCompteCourant.getSolde() + " €");
					}
                    break;
                case 6:
                    System.out.println("📜 Liste des opérations...");
                    // logiqe opérations
                    System.out.print("👉 Entrez l'UUID du compte : ");
                    sc.nextLine(); // vider le buffer
                    input = sc.nextLine();

                    
                    try {
                    	acountID = UUID.fromString(input);
                    } catch (IllegalArgumentException e) {
                        System.out.println("⚠️ UUID invalide !");
                        break;
                    }
                    if (coumpts.get(acountID) instanceof CompteCourant) {
						tempCompteCourant = (CompteCourant) coumpts.get(acountID); 
						retraitlist = tempCompteCourant.getRetraitList() ;
						versementslist = tempCompteCourant.getVersement();
						System.out.println("list de retrait de set compt");
						if (retraitlist.isEmpty()) {
							System.out.println("there is no retrait");
						} else {
							retraitlist.forEach((e)->{
								System.out.println("le ID de operation"+e.getNumero());
								System.out.println("le Montant de set operation"+e.getMontant());
								System.out.println("le date de set operation"+e.getOperationDate());
								System.out.println("le date de set operation"+e.getDestination());
							});
						}
						
						System.out.println("list de versement de set compt");
						if (versementslist.isEmpty()) {
							System.out.println("there is no retrait");
						} else {
							versementslist.forEach((e)->{
								System.out.println("le ID de operation"+e.getNumero());
								System.out.println("le Montant de set operation"+e.getMontant());
								System.out.println("le date de set operation"+e.getOperationDate());
								System.out.println("le date de set operation"+e.getOperationDate());
							});
						}
					}
                    if (coumpts.get(acountID) instanceof CompteEpargne) {
						tempCompteEpargne = (CompteEpargne) coumpts.get(acountID); 
						retraitlist = tempCompteCourant.getRetraitList() ;
						versementslist = tempCompteCourant.getVersement();
						
						System.out.println("list de retrait de set compt");
						if (retraitlist.isEmpty()) {
							System.out.println("there is no retrait");
						} else {
							retraitlist.forEach((e)->{
								System.out.println("le ID de operation"+e.getNumero());
								System.out.println("le Montant de set operation"+e.getMontant());
								System.out.println("le date de set operation"+e.getOperationDate());
								System.out.println("le date de set operation"+e.getDestination());
							});
						}
						
						System.out.println("list de versement de set compt");
						if (versementslist.isEmpty()) {
							System.out.println("there is no retrait");
						} else {
							versementslist.forEach((e)->{
								System.out.println("le ID de operation"+e.getNumero());
								System.out.println("le Montant de set operation"+e.getMontant());
								System.out.println("le date de set operation"+e.getOperationDate());
								System.out.println("le date de set operation"+e.getOperationDate());
							});
						}
					}
                    
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
