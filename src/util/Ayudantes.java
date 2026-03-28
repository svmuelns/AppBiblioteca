package util;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Ayudantes {

    // AYUDANTE SCANNER INT
    public static int AyudanteScannerInt(Scanner sc, String aviso, int min, int max) {
        System.out.print(aviso);
        int choice = 0;

        try {
            choice = sc.nextInt();
            if (choice >= min && choice <= max) {
                return choice;
            } else {
                System.out.println("Opción inválida, elige una opción entre " + min + " y " + max);
            }
        } catch (InputMismatchException e) {
            System.out.println("Opción invalida, elige un número.");
        } finally {
            sc.nextLine(); // Limpiar
        }
        return choice;
    }

    // AYUDANTE SCANNER STRING
    public static String AyudanteScannerString(Scanner sc, String aviso) {
        System.out.print(aviso);
        try{
            String input = sc.nextLine().trim(); // quitamos los espacios y tabuladores con trim

            if(!input.isEmpty() && input.length() < 50 && input.matches("^[a-zA-Z0-9\\s\\-\\.,!?;:()]+$")){
                return input;

            } else if (input.isEmpty()){
                throw new IllegalArgumentException("No puede estar vacío o ser solo espacios...");

            } else if (input.length() > 50){
                throw new IllegalArgumentException("Es muy largo, máximo 50 caracteres...");

            } else if (!input.matches("^[a-zA-Z0-9\\s\\-\\.,!?;:()]+$")){
                throw new IllegalArgumentException("El título contiene caracteres inválidos...");
            }
            return input;
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println(" [ Error: " + e.getMessage() + " ]");
            return null;
        }
    }

}
