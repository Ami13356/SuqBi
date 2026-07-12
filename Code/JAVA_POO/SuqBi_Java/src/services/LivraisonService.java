package services;

import java.util.ArrayList;

import entity.Commande;
import entity.Livraison;
import entity.Livreur;

public final class LivraisonService {
    private static ArrayList<Livraison> livraisons = new ArrayList<>();
    private static int prochainId = 1;

    private LivraisonService() {
    }

    // Cas d'utilisation Livreur : Accepter une livraison
    public static Livraison accepterLivraison(Livreur livreur, Commande commande) {
        Livraison livraison = livreur.accepterLivraison(commande);
        if (livraison == null) {
            return null;
        }
        livraison.setId(prochainId);
        prochainId++;
        livraisons.add(livraison);
        return livraison;
    }

    // Cas d'utilisation Livreur : Marquer livraison comme effectuee
    public static void marquerEffectuee(Livraison livraison) {
        livraison.marquerEffectuee();
    }

    // Cas d'utilisation Livreur : Consulter ses livraisons
    public static ArrayList<Livraison> getLivraisonsParLivreur(Livreur livreur) {
        return livreur.getLivraisons();
    }
}
