package views;

import java.util.ArrayList;

import entity.Client;
import entity.Commande;
import entity.Produit;
import services.ClientService;
import services.CommandeService;
import services.ProduitService;

public final class ClientView {
    private ClientView() {
    }

    public static void afficherMenuClient(Client client) {
        int choix;
        do {
            System.out.println("\n===== Menu Client (" + client.getPrenom() + ") =====");
            System.out.println("1. Consulter les produits disponibles");
            System.out.println("2. Rechercher un produit");
            System.out.println("3. Ajouter un produit au panier");
            System.out.println("4. Consulter le panier");
            System.out.println("5. Valider le panier et passer commande");
            System.out.println("6. Consulter mes commandes");
            System.out.println("7. Se deconnecter");
            choix = AuthView.saisieEntier("Saisir votre choix : ");

            switch (choix) {
                case 1:
                    afficherProduits(ProduitService.getProduitsDisponibles());
                    break;
                case 2:
                    String motCle = AuthView.saisieChaine("Mot-cle a rechercher : ");
                    afficherProduits(ProduitService.rechercherProduit(motCle));
                    break;
                case 3:
                    ajouterAuPanier(client);
                    break;
                case 4:
                    afficherPanier(client);
                    break;
                case 5:
                    validerEtPasserCommande(client);
                    break;
                case 6:
                    afficherCommandes(client);
                    break;
                case 7:
                    System.out.println("Deconnexion...");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez reessayer.");
            }
        } while (choix != 7);
    }

    private static void afficherProduits(ArrayList<Produit> produits) {
        if (produits.isEmpty()) {
            System.out.println("Aucun produit disponible pour le moment.");
        } else {
            System.out.println("--- Liste des produits ---");
            for (Produit p : produits) {
                System.out.println(p);
            }
        }
    }

    private static void ajouterAuPanier(Client client) {
        ArrayList<Produit> produits = ProduitService.getProduitsDisponibles();
        afficherProduits(produits);
        if (produits.isEmpty()) {
            return;
        }
        int idProduit = AuthView.saisieEntier("Saisir l'id du produit : ");
        Produit produit = rechercherParId(produits, idProduit);
        if (produit == null) {
            System.out.println("Produit introuvable.");
            return;
        }
        int quantite = AuthView.saisieEntier("Saisir la quantite : ");
        boolean succes = client.ajouterProduitAuPanier(produit, quantite);
        if (succes) {
            System.out.println("Produit ajoute au panier avec succes.");
        }
    }

    private static Produit rechercherParId(ArrayList<Produit> produits, int id) {
        for (Produit p : produits) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    private static void afficherPanier(Client client) {
        if (client.getPanier().isVide()) {
            System.out.println("Votre panier est vide.");
            return;
        }
        System.out.println("--- Contenu du panier ---");
        client.getPanier().getLignes().forEach(ligne -> System.out.println(ligne));
        System.out.println("Montant total : " + client.getPanier().getMontantTotal());
    }

    private static void validerEtPasserCommande(Client client) {
        if (client.getPanier().isVide()) {
            System.out.println("Le panier est vide.");
            return;
        }
        afficherPanier(client);
        client.getPanier().validerPanier();
        Commande commande = CommandeService.passerCommande(client);
        if (commande != null) {
            System.out.println("Commande passee avec succes ! " + commande);
        }
    }

    private static void afficherCommandes(Client client) {
        if (client.getCommandes().isEmpty()) {
            System.out.println("Vous n'avez encore passe aucune commande.");
        } else {
            System.out.println("--- Vos commandes ---");
            for (Commande c : client.getCommandes()) {
                System.out.println(c);
            }
        }
    }
}
