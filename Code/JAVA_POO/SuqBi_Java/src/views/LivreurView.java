package views;

import java.util.ArrayList;

import entity.Commande;
import entity.Livraison;
import entity.Livreur;
import services.LivraisonService;
import services.LivreurService;

public final class LivreurView {
    private LivreurView() {
    }

    public static void afficherMenuLivreur(Livreur livreur) {
        int choix;
        do {
            System.out.println("\n===== Menu Livreur (" + livreur.getPrenom() + ") =====");
            System.out.println("1. Renseigner ma disponibilite");
            System.out.println("2. Consulter les commandes a livrer");
            System.out.println("3. Accepter une livraison");
            System.out.println("4. Marquer une livraison comme effectuee");
            System.out.println("5. Consulter mes livraisons");
            System.out.println("6. Se deconnecter");
            choix = AuthView.saisieEntier("Saisir votre choix : ");

            switch (choix) {
                case 1:
                    renseignerDisponibilite(livreur);
                    break;
                case 2:
                    afficherCommandesALivrer(livreur);
                    break;
                case 3:
                    accepterLivraison(livreur);
                    break;
                case 4:
                    marquerEffectuee(livreur);
                    break;
                case 5:
                    afficherLivraisons(livreur);
                    break;
                case 6:
                    System.out.println("Deconnexion...");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez reessayer.");
            }
        } while (choix != 6);
    }

    private static void renseignerDisponibilite(Livreur livreur) {
        System.out.println("Etat actuel : " + (livreur.isDisponibilite() ? "disponible" : "indisponible"));
        String choix = AuthView.saisieChaine("Nouveau statut (disponible/indisponible) : ");
        livreur.renseignerDisponibilite(choix.equalsIgnoreCase("disponible"));
        System.out.println("Disponibilite mise a jour.");
    }

    private static void afficherCommandesALivrer(Livreur livreur) {
        ArrayList<Commande> commandes = LivreurService.consulterCommandesALivrer(livreur);
        if (commandes.isEmpty()) {
            System.out.println("Aucune commande a livrer actuellement.");
        } else {
            for (Commande c : commandes) {
                System.out.println(c);
            }
        }
    }

    private static void accepterLivraison(Livreur livreur) {
        ArrayList<Commande> commandes = LivreurService.consulterCommandesALivrer(livreur);
        if (commandes.isEmpty()) {
            return;
        }
        for (Commande c : commandes) {
            System.out.println(c);
        }
        int idCommande = AuthView.saisieEntier("Saisir l'id de la commande a accepter : ");
        Commande commandeChoisie = null;
        for (Commande c : commandes) {
            if (c.getId() == idCommande) {
                commandeChoisie = c;
            }
        }
        if (commandeChoisie == null) {
            System.out.println("Commande introuvable.");
            return;
        }
        Livraison livraison = LivraisonService.accepterLivraison(livreur, commandeChoisie);
        if (livraison != null) {
            System.out.println("Livraison acceptee : " + livraison);
        }
    }

    private static void marquerEffectuee(Livreur livreur) {
        ArrayList<Livraison> enCours = new ArrayList<>();
        for (Livraison l : livreur.getLivraisons()) {
            if (l.getStatut().equals("en cours")) {
                enCours.add(l);
            }
        }
        if (enCours.isEmpty()) {
            System.out.println("Aucune livraison en cours.");
            return;
        }
        for (Livraison l : enCours) {
            System.out.println(l);
        }
        int idLivraison = AuthView.saisieEntier("Saisir l'id de la livraison effectuee : ");
        for (Livraison l : enCours) {
            if (l.getId() == idLivraison) {
                LivraisonService.marquerEffectuee(l);
                System.out.println("Livraison effectuee avec succes.");
                return;
            }
        }
        System.out.println("Livraison introuvable.");
    }

    private static void afficherLivraisons(Livreur livreur) {
        if (livreur.getLivraisons().isEmpty()) {
            System.out.println("Aucune livraison pour le moment.");
        } else {
            for (Livraison l : livreur.getLivraisons()) {
                System.out.println(l);
            }
        }
    }
}
