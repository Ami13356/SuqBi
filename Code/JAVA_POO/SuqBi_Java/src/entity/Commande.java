package entity;

import java.time.LocalDate;
import java.util.ArrayList;

public class Commande {
    private int id;
    private LocalDate dateCommande;
    private double montantTotal;
    private String statut; // "en attente de livraison", "livree"
    private Client client;
    private Vendeur vendeur;
    private ArrayList<LigneCommande> lignes = new ArrayList<>();
    private Livraison livraison;

    public Commande() {
        this.dateCommande = LocalDate.now();
        this.statut = "en attente de livraison";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(LocalDate dateCommande) {
        this.dateCommande = dateCommande;
    }

    public double getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(double montantTotal) {
        this.montantTotal = montantTotal;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Vendeur getVendeur() {
        return vendeur;
    }

    public void setVendeur(Vendeur vendeur) {
        this.vendeur = vendeur;
    }

    public ArrayList<LigneCommande> getLignes() {
        return lignes;
    }

    public void addLigne(LigneCommande ligne) {
        this.lignes.add(ligne);
    }

    public void removeLigne(LigneCommande ligne) {
        this.lignes.remove(ligne);
    }

    public Livraison getLivraison() {
        return livraison;
    }

    public void setLivraison(Livraison livraison) {
        this.livraison = livraison;
    }

    // Methode metier : calcule le montant total a partir des lignes de commande
    public void calculerMontantTotal() {
        double total = 0;
        for (LigneCommande ligne : this.lignes) {
            total = total + ligne.getSousTotal();
        }
        this.montantTotal = total;
    }

    @Override
    public String toString() {
        return "Commande [id=" + id + ", dateCommande=" + dateCommande + ", montantTotal=" + montantTotal
                + ", statut=" + statut + ", client=" + (client != null ? client.getNom() : "?")
                + ", vendeur=" + (vendeur != null ? vendeur.getNom() : "?") + "]";
    }
}
