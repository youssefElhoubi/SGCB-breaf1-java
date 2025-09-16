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
	
}
