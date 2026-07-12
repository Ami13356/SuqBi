package entity;

import java.time.LocalDate;
import java.util.ArrayList;

public class Panier {
    private int id;
    private LocalDate dateCreation;
    private Client client;
    private ArrayList<LignePanier> lignes = new ArrayList<>();
    private boolean valide = false;

    public Panier() {
        this.dateCreation = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ArrayList<LignePanier> getLignes() {
        return lignes;
    }

    public void addLigne(LignePanier ligne) {
        this.lignes.add(ligne);
    }

    public void removeLigne(LignePanier ligne) {
        this.lignes.remove(ligne);
    }

    public boolean isValide() {
        return valide;
    }

    public boolean isVide() {
        return this.lignes.isEmpty();
    }

    // Methode metier : figer les lignes du panier avant de passer commande
    public void validerPanier() {
        this.valide = true;
    }

    // Methode metier : vider le panier apres passage de commande
    public void vider() {
        this.lignes.clear();
        this.valide = false;
    }

    public double getMontantTotal() {
        double total = 0;
        for (LignePanier ligne : this.lignes) {
            total = total + ligne.getSousTotal();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Panier [nbLignes=" + lignes.size() + ", montantTotal=" + getMontantTotal() + "]";
    }
}
