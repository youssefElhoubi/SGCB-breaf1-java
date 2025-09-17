package metier;

import java.time.LocalDateTime;
import utils.OpperationSource;

import java.util.HashMap;
import java.util.UUID;

import metier.Operation;

public class Versement extends Operation {

	private OpperationSource opperationsource;

	public Versement(double montant, OpperationSource op) {
		super(montant);
		this.opperationsource = op;

	}

	public OpperationSource getOpperationsource() {
		return opperationsource;
	}

	public void setOpperationsource(OpperationSource opperationsource) {
		this.opperationsource = opperationsource;
	}

}
