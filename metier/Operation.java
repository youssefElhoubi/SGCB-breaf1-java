package metier;
import java.util.UUID;
import java.time.LocalDateTime;

public abstract class Operation {
	protected UUID numero;
	protected double montant;
	protected LocalDateTime operationDate;
	public Operation(double montant) {
        this.numero = UUID.randomUUID();
        this.operationDate = LocalDateTime.now();
        this.montant = montant;
    }
	
	public LocalDateTime getOperationDate() {
        return operationDate;
    }

    // optional setter if you want to allow changing it
    public void setOperationDate(LocalDateTime operationDate) {
        this.operationDate = operationDate;
    }
    
    public UUID getNumero() {
		return numero;
	}

	public void setNumero(UUID numero) {
		this.numero = numero;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}
	
}
