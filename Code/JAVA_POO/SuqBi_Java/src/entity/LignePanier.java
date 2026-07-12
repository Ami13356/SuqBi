package entity;

// Classe association entre Panier et Produit
public class LignePanier {
    private int quantite;
    private Produit produit;

    public LignePanier() {
    }

    public LignePanier(Produit produit, int quantite) {
        this.produit = produit;
        this.quantite = quantite;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public double getSousTotal() {
        return this.produit.getPrix() * this.quantite;
    }

    @Override
    public String toString() {
        return "LignePanier [produit=" + produit.getNom() + ", quantite=" + quantite
                + ", sousTotal=" + getSousTotal() + "]";
    }
}
