package entity;

// Classe association entre Commande et Produit
public class LigneCommande {
    private int quantite;
    private double prixUnitaire;
    private Produit produit;

    public LigneCommande() {
    }

    // Le prix unitaire est fige au moment de la commande (copie du prix du produit)
    public LigneCommande(Produit produit, int quantite) {
        this.produit = produit;
        this.quantite = quantite;
        this.prixUnitaire = produit.getPrix();
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public double getSousTotal() {
        return this.prixUnitaire * this.quantite;
    }

    @Override
    public String toString() {
        return "LigneCommande [produit=" + produit.getNom() + ", quantite=" + quantite
                + ", prixUnitaire=" + prixUnitaire + ", sousTotal=" + getSousTotal() + "]";
    }
}
