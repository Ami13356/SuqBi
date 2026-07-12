package services;

import java.util.ArrayList;

import entity.Produit;
import entity.Vendeur;

public final class ProduitService {
    private static ArrayList<Produit> produits = new ArrayList<>();
    private static int prochainId = 1;

    private ProduitService() {
    }

    // Cas d'utilisation Vendeur : Ajouter un produit
    public static Produit ajouterProduit(Vendeur vendeur, String nom, String description, double prix, int qteStock) {
        Produit produit = new Produit(prochainId, nom, description, prix, qteStock);
        prochainId++;
        produit.setVendeur(vendeur);
        vendeur.addProduit(produit);
        produits.add(produit);
        return produit;
    }

    // Cas d'utilisation Vendeur/Admin : Modifier un produit
    public static void modifierProduit(Produit produit, String nom, String description, double prix, int qteStock) {
        produit.setNom(nom);
        produit.setDescription(description);
        produit.setPrix(prix);
        produit.setQteStock(qteStock);
    }

    // Cas d'utilisation Vendeur/Admin : Supprimer un produit
    public static void supprimerProduit(Produit produit) {
        if (produit.getVendeur() != null) {
            produit.getVendeur().removeProduit(produit);
        }
        produits.remove(produit);
    }

    // Cas d'utilisation Client : Consulter les produits disponibles
    public static ArrayList<Produit> getProduitsDisponibles() {
        ArrayList<Produit> disponibles = new ArrayList<>();
        for (Produit p : produits) {
            if (p.getQteStock() > 0) {
                disponibles.add(p);
            }
        }
        return disponibles;
    }

    // Cas d'utilisation Client : Rechercher un produit (extend de Consulter les produits)
    public static ArrayList<Produit> rechercherProduit(String motCle) {
        ArrayList<Produit> resultats = new ArrayList<>();
        for (Produit p : getProduitsDisponibles()) {
            if (p.getNom().toLowerCase().contains(motCle.toLowerCase())) {
                resultats.add(p);
            }
        }
        return resultats;
    }

    // Cas d'utilisation Admin : Consulter les produits (tous, disponibles ou non)
    public static ArrayList<Produit> getTousLesProduits() {
        return produits;
    }
}
