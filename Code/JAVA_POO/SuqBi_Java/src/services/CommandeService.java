package services;

import java.util.ArrayList;

import entity.Client;
import entity.Commande;
import entity.Vendeur;

public final class CommandeService {
    private static ArrayList<Commande> commandes = new ArrayList<>();
    private static int prochainId = 1;

    private CommandeService() {
    }

    // Cas d'utilisation Client : Passer une commande
    // Orchestre Client.passerCommande() (logique metier) puis l'enregistrement
    public static Commande passerCommande(Client client) {
        Commande commande = client.passerCommande();
        if (commande == null) {
            return null;
        }
        commande.setId(prochainId);
        prochainId++;
        commandes.add(commande);
        return commande;
    }

    // Cas d'utilisation Vendeur : Consulter les commandes recues
    public static ArrayList<Commande> getCommandesParVendeur(Vendeur vendeur) {
        ArrayList<Commande> resultat = new ArrayList<>();
        for (Commande c : commandes) {
            if (c.getVendeur().equals(vendeur)) {
                resultat.add(c);
            }
        }
        return resultat;
    }

    // Cas d'utilisation Admin : Consulter les commandes (toutes)
    public static ArrayList<Commande> getToutesLesCommandes() {
        return commandes;
    }
}
