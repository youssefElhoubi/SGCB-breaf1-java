package metier;
import java.util.UUID;

import metier.Compte;

public class CompteEpargne extends Compte {
	private double InterestRate;
	
	public CompteEpargne(double soulde,double InterestRate) {
		this.code = UUID.randomUUID();
		this.solde = soulde;
	}
	public void setInterestRate(double InterestRate) {
		this.InterestRate = InterestRate;
	}
	public double getInterestRate() {
		return this.InterestRate;
	}
	@Override()
	public void retirer(double amount) {
		if (this.solde>amount) {
			System.out.println("the amount your tring to withrawl is more that your solde");
		}else {
			System.out.println("you have withrawl" + amount);
		this.solde -= amount;
		}
	}
	@Override()
	public double calculerInteret(float intrest) {
		return (intrest <= 0) ? 0 : this.solde * intrest;
	}
	@Override()
	public void afficherDetails() {
		System.out.println("this is your id " + this.code);
		System.out.println("this is your solde " + this.solde);
		System.out.println("this is your max supplémentaire " + this.InterestRate);
	}
	
}
