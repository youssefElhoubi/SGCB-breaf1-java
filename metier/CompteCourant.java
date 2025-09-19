package metier;

import metier.Compte;
import java.util.UUID;

public class CompteCourant extends Compte {
	private double decouvert;

	public CompteCourant(double soulde, double decouvert) {
		super();
		this.code = UUID.randomUUID();
		this.solde = soulde;
		this.decouvert = decouvert;
	}

	public void setInterestRate(double decouvert) {
		this.decouvert = decouvert;
	}

	public double getInterestRate() {
		return this.decouvert;
	}

	@Override()
	public boolean retirer(double amount) {
		if (this.solde - amount < this.decouvert) {
			System.out.println(
					"le montant que vous essayez de retirer est supérieur au découvert autorisé pour votre compte");
			return false;

		} else {
			this.solde -= amount;
			return true;
		}
	}

	@Override()
	public double calculerInteret(float intrest) {
		return 0;
	}

	@Override()
	public void afficherDetails() {
		System.out.println("this is your id " + this.code);
		System.out.println("this is your solde " + this.solde);
		System.out.println("this is your max decouvert  " + this.decouvert);
	}
}
