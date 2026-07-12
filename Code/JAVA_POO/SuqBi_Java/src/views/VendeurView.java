package views;

import java.util.ArrayList;

import entity.Commande;
import entity.Produit;
import entity.Vendeur;
import services.CommandeService;
import services.ProduitService;

public final class VendeurView {
    private VendeurView() {
    }

    public static void afficherMenuVendeur(Vendeur vendeur) {
        int choix;
        do {
            System.out.println("\n===== Menu Vendeur (" + vendeur.getPrenom() + ") =====");
            System.out.println("1. Ajouter un produit");
            System.out.println("2. Modifier un produit");
            System.out.println("3. Supprimer un produit");
            System.out.println("4. Consulter mes produits");
            System.out.println("5. Consulter les commandes recues");
            System.out.println("6. Se deconnecter");
            choix = AuthView.saisieEntier("Saisir votre choix : ");

            switch (choix) {
                case 1:
                    ajouterProduit(vendeur);
                    break;
                case 2:
                    modifierProduit(vendeur);
                    break;
                case 3:
                    supprimerProduit(vendeur);
                    break;
                case 4:
                    afficherProduits(vendeur.getProduits());
                    break;
                case 5:
                    afficherCommandes(vendeur);
                    break;
                case 6:
                    System.out.println("Deconnexion...");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez reessayer.");
            }
        } while (choix != 6);
    }

    private static void afficherProduits(ArrayList<Produit> produits) {
        if (produits.isEmpty()) {
            System.out.println("Aucun produit trouve.");
        } else {
            for (Produit p : produits) {
                System.out.println(p);
            }
        }
    }

    private static void ajouterProduit(Vendeur vendeur) {
        String nom = AuthView.saisieChaine("Nom du produit : ");
        String description = AuthView.saisieChaine("Description : ");
        double prix = AuthView.saisieDouble("Prix : ");
        int qteStock = AuthView.saisieEntier("Quantite en stock : ");
        ProduitService.ajouterProduit(vendeur, nom, description, prix, qteStock);
        System.out.println("Produit ajoute avec succes.");
    }

    private static Produit selectionnerProduit(Vendeur vendeur) {
        afficherProduits(vendeur.getProduits());
        if (vendeur.getProduits().isEmpty()) {
            return null;
        }
        int id = AuthView.saisieEntier("Saisir l'id du produit : ");
        for (Produit p : vendeur.getProduits()) {
            if (p.getId() == id) {
                return p;
            }
        }
        System.out.println("Produit introuvable.");
        return null;
    }

    private static void modifierProduit(Vendeur vendeur) {
        Produit produit = selectionnerProduit(vendeur);
        if (produit == null) {
            return;
        }
        String nom = AuthView.saisieChaine("Nouveau nom : ");
        String description = AuthView.saisieChaine("Nouvelle description : ");
        double prix = AuthView.saisieDouble("Nouveau prix : ");
        int qteStock = AuthView.saisieEntier("Nouvelle quantite en stock : ");
        ProduitService.modifierProduit(produit, nom, description, prix, qteStock);
        System.out.println("Produit modifie avec succes.");
    }

    private static void supprimerProduit(Vendeur vendeur) {
        Produit produit = selectionnerProduit(vendeur);
        if (produit == null) {
            return;
        }
        String confirmation = AuthView.saisieChaine("Confirmer la suppression ? (oui/non) : ");
        if (confirmation.equalsIgnoreCase("oui")) {
            ProduitService.supprimerProduit(produit);
            System.out.println("Produit supprime avec succes.");
        } else {
            System.out.println("Suppression annulee.");
        }
    }

    private static void afficherCommandes(Vendeur vendeur) {
        ArrayList<Commande> commandes = CommandeService.getCommandesParVendeur(vendeur);
        if (commandes.isEmpty()) {
            System.out.println("Aucune commande recue pour le moment.");
        } else {
            for (Commande c : commandes) {
                System.out.println(c);
            }
        }
    }
}
