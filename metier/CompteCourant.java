package metier;
import metier.Compte;
import java.util.UUID;

public class CompteCourant extends Compte {
	private double supplémentaire;
	public CompteCourant(double soulde ,double supplémentaire) {
		this.code = UUID.randomUUID();
		this.solde = soulde;
		this.supplémentaire = supplémentaire;
	}
	public void setInterestRate(double supplémentaire) {
		this.supplémentaire = supplémentaire;
	}
	public double getInterestRate() {
		return this.supplémentaire;
	}
	@Override()
	public void retirer(double amount) {
		if (this.solde-amount>this.supplémentaire) {
			System.out.println("le montant que vous essayez de retirer est supérieur au découvert autorisé pour votre compte");
		}else {
		this.solde -= amount;
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
		System.out.println("this is your max supplémentaire " + this.supplémentaire);
	}
}
