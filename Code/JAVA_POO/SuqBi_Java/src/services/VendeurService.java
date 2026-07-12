package services;

import java.util.ArrayList;

import entity.Vendeur;

public final class VendeurService {
    private static ArrayList<Vendeur> vendeurs = new ArrayList<>();
    private static int prochainId = 1;

    private VendeurService() {
    }

    private static boolean emailExiste(String email) {
        for (Vendeur v : vendeurs) {
            if (v.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    // Cas d'utilisation : Creer un compte (Vendeur)
    public static Vendeur creerCompte(String nom, String prenom, String tel, String email) {
        if (emailExiste(email)) {
            System.out.println("Ce compte existe deja.");
            return null;
        }
        Vendeur vendeur = new Vendeur(prochainId, nom, prenom, tel, email);
        prochainId++;
        vendeurs.add(vendeur);
        return vendeur;
    }

    // NB : le mot de passe des acteurs Vendeur/Livreur n'est pas present dans le
    // diagramme de classe valide (seul Client a l'attribut mdp). Ici on simule une
    // connexion par email uniquement, comme convenu -> a confirmer avec l'encadrant
    // si un mot de passe doit etre ajoute officiellement.
    public static Vendeur seConnecter(String email) {
        for (Vendeur v : vendeurs) {
            if (v.getEmail().equalsIgnoreCase(email)) {
                return v;
            }
        }
        System.out.println("Compte introuvable.");
        return null;
    }

    public static ArrayList<Vendeur> getTousLesVendeurs() {
        return vendeurs;
    }

    // Cas d'utilisation Admin : Ajouter un vendeur
    public static Vendeur ajouterVendeur(String nom, String prenom, String tel, String email) {
        return creerCompte(nom, prenom, tel, email);
    }

    // Cas d'utilisation Admin : Modifier un vendeur
    public static void modifierVendeur(Vendeur vendeur, String nom, String prenom, String tel, String email) {
        vendeur.setNom(nom);
        vendeur.setPrenom(prenom);
        vendeur.setTel(tel);
        vendeur.setEmail(email);
    }

    // Cas d'utilisation Admin : Supprimer un vendeur
    public static void supprimerVendeur(Vendeur vendeur) {
        vendeurs.remove(vendeur);
    }
}
