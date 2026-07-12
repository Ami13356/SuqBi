package services;

import entity.Admin;

public final class AdminService {
    // Compte Admin pre-defini en dur, comme le compte "chef" dans l'exemple du
    // professeur (pas de cas d'utilisation "Creer un compte" pour l'Admin)
    private static Admin admin = new Admin(1, "Admin", "SuqBi", "770000000", "admin@suqbi.com", "admin");

    private AdminService() {
    }

    // Cas d'utilisation : Se connecter (Admin)
    public static Admin seConnecter(String email, String mdp) {
        if (admin.getEmail().equalsIgnoreCase(email) && admin.getMdp().equals(mdp)) {
            return admin;
        }
        System.out.println("Identifiants incorrects.");
        return null;
    }
}
