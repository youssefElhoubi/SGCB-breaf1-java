package metier;
import java.time.LocalDateTime;
import utils.OpperationSource;

import java.util.HashMap;
import java.util.UUID;

import metier.Operation;

public class Versement extends Operation {
	private double supplémentaire;
	private OpperationSource opperationsource;

	
	public Versement(double montant,double supplémentaire,OpperationSource op ) {
		this.numero = UUID.randomUUID();
		this.montant = montant;
		this.supplémentaire = supplémentaire;
		this.operationDate = LocalDateTime.now();
		this.opperationsource = op;
		
	}
	public void setInterestRate(double supplémentaire) {
		this.supplémentaire = supplémentaire;
	}
	public double getInterestRate() {
		return this.supplémentaire;
	}
	
}
