package utils;

import java.util.HashMap;
import java.util.UUID;
import metier.CompteCourant;
import metier.CompteEpargne;

public class Filler {
	
	public static void filler(HashMap<UUID, Object> compt) {
        // create 10 CompteCourant
        for (int i = 0; i < 10; i++) {
            double solde = 1000 + (i * 100);   // just some example values
            double decouvert = 500 + (i * 50); 
            CompteCourant cc = new CompteCourant(solde, decouvert);
            compt.put(cc.getCode(), cc);
        }

        // create 10 CompteEpargne
        for (int i = 0; i < 10; i++) {
            double solde = 2000 + (i * 200);   
            double interestRate = 0.02 + (i * 0.01); 
            CompteEpargne ce = new CompteEpargne(solde, interestRate);
            ce.setInterestRate(interestRate);  
            compt.put(ce.getCode(), ce);
        }
    }

}
