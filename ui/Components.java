package ui;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import metier.*;

public final class Components {
//	LSO means Liste des opérations
	private static CompteCourant tempCompteCourant = null;
	private static CompteEpargne tempCompteEpargne = null;
	private static List<Retrait> retraitlist = null;
	private static List<Versement> versementslist = null;
	
	public static void LSO(HashMap<UUID, Object> coumpts ,UUID acountID) {
		if (coumpts.get(acountID) instanceof CompteCourant) {
			tempCompteCourant = (CompteCourant) coumpts.get(acountID);
			retraitlist = tempCompteCourant.getRetraitList();
			versementslist = tempCompteCourant.getVersement();
			System.out.println("list de retrait de set compt");
			if (retraitlist.isEmpty()) {
				System.out.println("there is no retrait");
			} else {
				retraitlist.forEach((e) -> {
					System.out.println("le ID de operation" + e.getNumero());
					System.out.println("le Montant de set operation" + e.getMontant());
					System.out.println("le date de set operation" + e.getOperationDate());
					System.out.println("le date de set operation" + e.getDestination());
				});
			}

			System.out.println("list de versement de set compt");
			if (versementslist.isEmpty()) {
				System.out.println("there is no retrait");
			} else {
				versementslist.forEach((e) -> {
					System.out.println("le ID de operation" + e.getNumero());
					System.out.println("le Montant de set operation" + e.getMontant());
					System.out.println("le date de set operation" + e.getOperationDate());
					System.out.println("le date de set operation" + e.getOperationDate());
				});
			}
		}
		if (coumpts.get(acountID) instanceof CompteEpargne) {
			tempCompteEpargne = (CompteEpargne) coumpts.get(acountID);
			retraitlist = tempCompteCourant.getRetraitList();
			versementslist = tempCompteCourant.getVersement();

			System.out.println("list de retrait de set compt");
			if (retraitlist.isEmpty()) {
				System.out.println("there is no retrait");
			} else {
				retraitlist.forEach((e) -> {
					System.out.println("le ID de operation" + e.getNumero());
					System.out.println("le Montant de set operation" + e.getMontant());
					System.out.println("le date de set operation" + e.getOperationDate());
					System.out.println("le date de set operation" + e.getDestination());
				});
			}

			System.out.println("list de versement de set compt");
			if (versementslist.isEmpty()) {
				System.out.println("there is no retrait");
			} else {
				versementslist.forEach((e) -> {
					System.out.println("le ID de operation" + e.getNumero());
					System.out.println("le Montant de set operation" + e.getMontant());
					System.out.println("le date de set operation" + e.getOperationDate());
					System.out.println("le date de set operation" + e.getOperationDate());
				});
			}
		}
		
	}

}
