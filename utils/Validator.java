package utils;
import java.util.Scanner;
import java.util.UUID;



public class Validator {
	private static Scanner sc = new Scanner(System.in);
	
	public static double askPositiveDouble(String message) {
        double value;
        while (true) {
            System.out.print(message);
            if (sc.hasNextDouble()) {
                value = sc.nextDouble();
                sc.nextLine(); // clear buffer
                if (value >= 0) {
                    return value;
                } else {
                    System.out.println("⚠️ Le montant doit être positif. Réessayez.");
                }
            } else {
                System.out.println("⚠️ Entrée invalide. Veuillez entrer un nombre.");
                sc.nextLine(); // clear invalid input
            }
        }
    }
	
	public static double askInterestRate(String message) {
        double rate;
        while (true) {
            System.out.print(message);
            if (sc.hasNextDouble()) {
                rate = sc.nextDouble();
                sc.nextLine(); // clear buffer
                if (rate >= 0 && rate <= 1) {
                    return rate;
                } else {
                    System.out.println("⚠️ Le taux doit être entre 0 et 1. Réessayez.");
                }
            } else {
                System.out.println("⚠️ Entrée invalide. Veuillez entrer un nombre.");
                sc.nextLine();
            }
        }
    }
	
	public static double asknegativeDouble(String message) {
        double value;
        while (true) {
            System.out.print(message);
            if (sc.hasNextDouble()) {
                value = sc.nextDouble();
                sc.nextLine(); // clear buffer
                if (value <= 0) {
                    return value;
                } else {
                    System.out.println("⚠️ Le montant doit être positif. Réessayez.");
                }
            } else {
                System.out.println("⚠️ Entrée invalide. Veuillez entrer un nombre.");
                sc.nextLine(); // clear invalid input
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

}
