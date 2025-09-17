package utils;

import metier.CompteCourant;
import metier.CompteEpargne;
import java.util.HashMap;
import java.util.UUID;

public class Comptfinder {

	public static CompteEpargne CompteEpargneFinder(UUID id, HashMap<UUID, Object> acounts) {
		Object compte = acounts.get(id);

		if (compte instanceof CompteEpargne) {
			return (CompteEpargne) compte; // safe cast
		}

		return null;
	}

	public static CompteCourant CompteCourantFinder(UUID id, HashMap<UUID, Object> acounts) {
		Object compte = acounts.get(id);

		if (compte instanceof CompteEpargne) {
			return (CompteCourant) compte; // safe cast
		}

		return null;
	}

}
