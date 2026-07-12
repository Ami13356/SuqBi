package views;

import java.util.ArrayList;

import entity.Client;
import entity.Commande;
import entity.Livreur;
import entity.Produit;
import entity.Vendeur;
import services.ClientService;
import services.CommandeService;
import services.LivreurService;
import services.ProduitService;
import services.VendeurService;

public final class AdminView {
    private AdminView() {
    }

    public static void afficherMenuAdmin() {
        int choix;
        do {
            System.out.println("\n===== Menu Admin =====");
            System.out.println("1. Gerer les utilisateurs");
            System.out.println("2. Gerer les produits");
            System.out.println("3. Consulter les commandes");
            System.out.println("4. Se deconnecter");
            choix = AuthView.saisieEntier("Saisir votre choix : ");

            switch (choix) {
                case 1:
                    gererUtilisateurs();
                    break;
                case 2:
                    gererProduits();
                    break;
                case 3:
                    afficherCommandes();
                    break;
                case 4:
                    System.out.println("Deconnexion...");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez reessayer.");
            }
        } while (choix != 4);
    }

    // ===== Gerer les utilisateurs =====
    private static void gererUtilisateurs() {
        System.out.println("Type d'utilisateur : 1.Clients  2.Vendeurs  3.Livreurs");
        int type = AuthView.saisieEntier("Choix : ");
        switch (type) {
            case 1:
                gererClients();
                break;
            case 2:
                gererVendeurs();
                break;
            case 3:
                gererLivreurs();
                break;
            default:
                System.out.println("Choix invalide.");
        }
    }

    private static void gererClients() {
        ArrayList<Client> clients = ClientService.getTousLesClients();
        afficherListe(clients);
        System.out.println("1.Modifier  2.Supprimer  3.Retour");
        int action = AuthView.saisieEntier("Choix : ");
        if (clients.isEmpty() || action == 3) {
            return;
        }
        int id = AuthView.saisieEntier("Id du client : ");
        Client client = null;
        for (Client c : clients) {
            if (c.getId() == id) {
                client = c;
            }
        }
        if (client == null) {
            System.out.println("Client introuvable.");
            return;
        }
        if (action == 1) {
            String nom = AuthView.saisieChaine("Nouveau nom : ");
            String prenom = AuthView.saisieChaine("Nouveau prenom : ");
            String tel = AuthView.saisieChaine("Nouveau tel : ");
            String email = AuthView.saisieChaine("Nouvel email : ");
            ClientService.modifierClient(client, nom, prenom, tel, email);
            System.out.println("Client modifie avec succes.");
        } else if (action == 2) {
            String confirmation = AuthView.saisieChaine("Confirmer la suppression ? (oui/non) : ");
            if (confirmation.equalsIgnoreCase("oui")) {
                ClientService.supprimerClient(client);
                System.out.println("Client supprime avec succes.");
            }
        }
    }

    private static void gererVendeurs() {
        ArrayList<Vendeur> vendeurs = VendeurService.getTousLesVendeurs();
        afficherListe(vendeurs);
        System.out.println("1.Ajouter  2.Modifier  3.Supprimer  4.Retour");
        int action = AuthView.saisieEntier("Choix : ");
        if (action == 1) {
            String nom = AuthView.saisieChaine("Nom : ");
            String prenom = AuthView.saisieChaine("Prenom : ");
            String tel = AuthView.saisieChaine("Tel : ");
            String email = AuthView.saisieChaine("Email : ");
            VendeurService.ajouterVendeur(nom, prenom, tel, email);
            System.out.println("Vendeur ajoute avec succes.");
            return;
        }
        if (vendeurs.isEmpty() || action == 4) {
            return;
        }
        int id = AuthView.saisieEntier("Id du vendeur : ");
        Vendeur vendeur = null;
        for (Vendeur v : vendeurs) {
            if (v.getId() == id) {
                vendeur = v;
            }
        }
        if (vendeur == null) {
            System.out.println("Vendeur introuvable.");
            return;
        }
        if (action == 2) {
            String nom = AuthView.saisieChaine("Nouveau nom : ");
            String prenom = AuthView.saisieChaine("Nouveau prenom : ");
            String tel = AuthView.saisieChaine("Nouveau tel : ");
            String email = AuthView.saisieChaine("Nouvel email : ");
            VendeurService.modifierVendeur(vendeur, nom, prenom, tel, email);
            System.out.println("Vendeur modifie avec succes.");
        } else if (action == 3) {
            String confirmation = AuthView.saisieChaine("Confirmer la suppression ? (oui/non) : ");
            if (confirmation.equalsIgnoreCase("oui")) {
                VendeurService.supprimerVendeur(vendeur);
                System.out.println("Vendeur supprime avec succes.");
            }
        }
    }

    private static void gererLivreurs() {
        ArrayList<Livreur> livreurs = LivreurService.getTousLesLivreurs();
        afficherListe(livreurs);
        System.out.println("1.Ajouter  2.Modifier  3.Supprimer  4.Retour");
        int action = AuthView.saisieEntier("Choix : ");
        if (action == 1) {
            String nom = AuthView.saisieChaine("Nom : ");
            String prenom = AuthView.saisieChaine("Prenom : ");
            String tel = AuthView.saisieChaine("Tel : ");
            String email = AuthView.saisieChaine("Email : ");
            LivreurService.ajouterLivreur(nom, prenom, tel, email);
            System.out.println("Livreur ajoute avec succes.");
            return;
        }
        if (livreurs.isEmpty() || action == 4) {
            return;
        }
        int id = AuthView.saisieEntier("Id du livreur : ");
        Livreur livreur = null;
        for (Livreur l : livreurs) {
            if (l.getId() == id) {
                livreur = l;
            }
        }
        if (livreur == null) {
            System.out.println("Livreur introuvable.");
            return;
        }
        if (action == 2) {
            String nom = AuthView.saisieChaine("Nouveau nom : ");
            String prenom = AuthView.saisieChaine("Nouveau prenom : ");
            String tel = AuthView.saisieChaine("Nouveau tel : ");
            String email = AuthView.saisieChaine("Nouvel email : ");
            LivreurService.modifierLivreur(livreur, nom, prenom, tel, email);
            System.out.println("Livreur modifie avec succes.");
        } else if (action == 3) {
            String confirmation = AuthView.saisieChaine("Confirmer la suppression ? (oui/non) : ");
            if (confirmation.equalsIgnoreCase("oui")) {
                LivreurService.supprimerLivreur(livreur);
                System.out.println("Livreur supprime avec succes.");
            }
        }
    }

    // ===== Gerer les produits =====
    private static void gererProduits() {
        ArrayList<Produit> produits = ProduitService.getTousLesProduits();
        afficherListe(produits);
        System.out.println("1.Modifier  2.Supprimer  3.Retour");
        int action = AuthView.saisieEntier("Choix : ");
        if (produits.isEmpty() || action == 3) {
            return;
        }
        int id = AuthView.saisieEntier("Id du produit : ");
        Produit produit = null;
        for (Produit p : produits) {
            if (p.getId() == id) {
                produit = p;
            }
        }
        if (produit == null) {
            System.out.println("Produit introuvable.");
            return;
        }
        if (action == 1) {
            String nom = AuthView.saisieChaine("Nouveau nom : ");
            String description = AuthView.saisieChaine("Nouvelle description : ");
            double prix = AuthView.saisieDouble("Nouveau prix : ");
            int qteStock = AuthView.saisieEntier("Nouvelle quantite en stock : ");
            ProduitService.modifierProduit(produit, nom, description, prix, qteStock);
            System.out.println("Produit modifie avec succes.");
        } else if (action == 2) {
            String confirmation = AuthView.saisieChaine("Confirmer la suppression ? (oui/non) : ");
            if (confirmation.equalsIgnoreCase("oui")) {
                ProduitService.supprimerProduit(produit);
                System.out.println("Produit supprime avec succes.");
            }
        }
    }

    // ===== Consulter les commandes =====
    private static void afficherCommandes() {
        ArrayList<Commande> commandes = CommandeService.getToutesLesCommandes();
        if (commandes.isEmpty()) {
            System.out.println("Aucune commande enregistree pour le moment.");
        } else {
            for (Commande c : commandes) {
                System.out.println(c);
            }
        }
    }

    private static void afficherListe(ArrayList<?> liste) {
        if (liste.isEmpty()) {
            System.out.println("Aucun element trouve.");
        } else {
            for (Object o : liste) {
                System.out.println(o);
            }
        }
    }
}
