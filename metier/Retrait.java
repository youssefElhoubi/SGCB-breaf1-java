package metier;

import java.time.LocalDateTime;
import java.util.UUID;
import utils.Destination;

public class Retrait extends Operation {
    private Destination destination;

    public Retrait(double montant, Destination destination) {
        this.numero = UUID.randomUUID();
        this.montant = montant;
        this.operationDate = LocalDateTime.now();
        this.destination = destination;
    }

    // Getter et Setter pour destination
    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }
}
