package metier;
import java.util.List;
import java.util.UUID;

public abstract class Compte {
	protected UUID code;
	protected double solde;
	protected List<Retrait> retrait;
	protected List<Versement> versement;
	
	
	public abstract void retirer(double amount);
	public abstract double calculerInteret(float intrest);
	public abstract void afficherDetails();
	
	public UUID getCode() {
        return code;
    }

    public void setCode(UUID code) {
        this.code = code;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }
    public void setRetrait(Retrait retrait) {
    		this.retrait.add(retrait) ;
    }
    public Retrait getRetrait(int posesion) {
    		return this.retrait.get(posesion);
    }
    public void setVersement(Versement versement) {
		this.versement.add(versement) ;
    }
    	public Versement getVersement(int posesion) {
		return this.versement.get(posesion);
    	}
	
}
