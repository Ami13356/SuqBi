package entity;

import java.time.LocalDate;

public class Livraison {
    private int id;
    private LocalDate dateLivraison;
    private String statut; // "en cours", "effectuee"
    private Livreur livreur;
    private Client client;
    private Commande commande;

    public Livraison() {
        this.dateLivraison = LocalDate.now();
        this.statut = "en cours";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(LocalDate dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Livreur getLivreur() {
        return livreur;
    }

    public void setLivreur(Livreur livreur) {
        this.livreur = livreur;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    // Methode metier : marquer la livraison comme effectuee
    public void marquerEffectuee() {
        this.statut = "effectuee";
    }

    @Override
    public String toString() {
        return "Livraison [id=" + id + ", dateLivraison=" + dateLivraison + ", statut=" + statut + "]";
    }
}
