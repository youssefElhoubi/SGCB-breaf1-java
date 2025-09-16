package metier;
import utils.Destination;

public class Retrait extends Operation {
    private Destination destination;

    public Retrait(double montant, Destination destination) {
    	super(montant);
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
