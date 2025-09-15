package metier;
import java.time.LocalDateTime;
import java.util.UUID;

import metier.Operation;

public class Versement extends Operation {
	private double supplémentaire;
	
	private Versement(double montant,double supplémentaire,Compte compt ) {
		this.numero = UUID.randomUUID();
		this.montant = montant;
		this.supplémentaire = supplémentaire;
		this.operationDate = LocalDateTime.now();
	}
	public void setInterestRate(double supplémentaire) {
		this.supplémentaire = supplémentaire;
	}
	public double getInterestRate() {
		return this.supplémentaire;
	}
}
