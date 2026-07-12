package services;

import java.util.ArrayList;

import entity.Commande;
import entity.Livreur;

public final class LivreurService {
    private static ArrayList<Livreur> livreurs = new ArrayList<>();
    private static int prochainId = 1;

    private LivreurService() {
    }

    private static boolean emailExiste(String email) {
        for (Livreur l : livreurs) {
            if (l.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    // Cas d'utilisation : Creer un compte (Livreur)
    public static Livreur creerCompte(String nom, String prenom, String tel, String email) {
        if (emailExiste(email)) {
            System.out.println("Ce compte existe deja.");
            return null;
        }
        Livreur livreur = new Livreur(prochainId, nom, prenom, tel, email);
        prochainId++;
        livreurs.add(livreur);
        return livreur;
    }

    public static Livreur seConnecter(String email) {
        for (Livreur l : livreurs) {
            if (l.getEmail().equalsIgnoreCase(email)) {
                return l;
            }
        }
        System.out.println("Compte introuvable.");
        return null;
    }

    public static ArrayList<Livreur> getTousLesLivreurs() {
        return livreurs;
    }

    // Cas d'utilisation Livreur : Consulter les commandes a livrer
    // Regle de gestion : uniquement si le livreur est disponible
    public static ArrayList<Commande> consulterCommandesALivrer(Livreur livreur) {
        ArrayList<Commande> resultat = new ArrayList<>();
        if (!livreur.isDisponibilite()) {
            System.out.println("Vous devez etre disponible pour accepter une livraison.");
            return resultat;
        }
        for (Commande c : CommandeService.getToutesLesCommandes()) {
            if (c.getStatut().equals("en attente de livraison") && c.getLivraison() == null) {
                resultat.add(c);
            }
        }
        return resultat;
    }

    // Cas d'utilisation Admin : Ajouter un livreur
    public static Livreur ajouterLivreur(String nom, String prenom, String tel, String email) {
        return creerCompte(nom, prenom, tel, email);
    }

    // Cas d'utilisation Admin : Modifier un livreur
    public static void modifierLivreur(Livreur livreur, String nom, String prenom, String tel, String email) {
        livreur.setNom(nom);
        livreur.setPrenom(prenom);
        livreur.setTel(tel);
        livreur.setEmail(email);
    }

    // Cas d'utilisation Admin : Supprimer un livreur
    public static void supprimerLivreur(Livreur livreur) {
        livreurs.remove(livreur);
    }
}
