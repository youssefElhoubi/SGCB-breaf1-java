package utils;

import java.util.Scanner;
import java.util.UUID;
import java.util.HashMap;

public class Validator {
	private static Scanner sc = new Scanner(System.in);

	public static double askPositiveDouble(String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine();
            try {
                double value = Double.parseDouble(input);
                if (value >= 0) {
                    return value;
                } else {
                    System.out.println("⚠️ Le montant doit être positif. Réessayez.");
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Entrée invalide. Veuillez entrer un nombre.");
            }
        }
    }

    public static double askInterestRate(String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine();
            try {
                double rate = Double.parseDouble(input);
                if (rate >= 0 && rate <= 1) {
                    return rate;
                } else {
                    System.out.println("⚠️ Le taux doit être entre 0 et 1. Réessayez.");
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Entrée invalide. Veuillez entrer un nombre.");
            }
        }
    }

    public static double askNegativeDouble(String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine();
            try {
                double value = Double.parseDouble(input);
                if (value <= 0) {
                    return value;
                } else {
                    System.out.println("⚠️ Le montant doit être négatif. Réessayez.");
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Entrée invalide. Veuillez entrer un nombre.");
            }
        }
    }

	public static boolean isValidUuid(String uuidString) {
		try {
			UUID.fromString(uuidString);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	public static int isBetween(int min, int max) {
	    while (true) {
	        System.out.print("👉 Entrez une valeur entre " + min + " et " + max + " : ");
	        String input = sc.nextLine();
	        sc.nextLine();
	        try {
	            int value = Integer.parseInt(input);
	            if (value < min || value > max) {
	                System.out.println("⚠️ La valeur doit être comprise entre " + min + " et " + max + ". Réessayez.");
	            } else {
	                return value;
	            }
	        } catch (NumberFormatException e) {
	            System.out.println("⚠️ Entrée invalide. Veuillez entrer un nombre entier.");
	        }
	    }
	}
	public static boolean doeaObjectExist(HashMap<UUID,Object>compt,UUID ID) {
		if (compt.get(ID)==null) {
			return false;
		}
		return true;
	}

}
