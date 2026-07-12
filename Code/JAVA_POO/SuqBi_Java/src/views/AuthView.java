package views;

public final class AuthView {
    private AuthView() {
    }

    public static String saisieChaine(String message) {
        do {
            System.out.print(message);
            String input = System.console().readLine();
            if (input != null && !input.trim().isEmpty()) {
                return input.trim();
            }
            System.out.println("Ce champ ne peut pas etre vide.");
        } while (true);
    }

    public static int saisieEntier(String message) {
        do {
            System.out.print(message);
            String input = System.console().readLine();
            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("Veuillez saisir un nombre entier valide.");
            }
        } while (true);
    }

    public static double saisieDouble(String message) {
        do {
            System.out.print(message);
            String input = System.console().readLine();
            try {
                return Double.parseDouble(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("Veuillez saisir un nombre valide.");
            }
        } while (true);
    }
}
