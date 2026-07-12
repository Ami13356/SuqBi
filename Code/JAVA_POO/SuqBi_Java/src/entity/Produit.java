package entity;

public class Produit {
    private int id;
    private String nom;
    private String description;
    private double prix;
    private int qteStock;
    private Vendeur vendeur;

    public Produit() {
    }

    public Produit(int id, String nom, String description, double prix, int qteStock) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.qteStock = qteStock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getQteStock() {
        return qteStock;
    }

    public void setQteStock(int qteStock) {
        this.qteStock = qteStock;
    }

    public Vendeur getVendeur() {
        return vendeur;
    }

    public void setVendeur(Vendeur vendeur) {
        this.vendeur = vendeur;
    }

    // Methode metier : verifie si la quantite demandee est disponible en stock
    public boolean verifierStock(int quantite) {
        return this.qteStock >= quantite;
    }

    // Methode metier : diminue le stock apres une commande
    public void diminuerStock(int quantite) {
        this.qteStock = this.qteStock - quantite;
    }

    @Override
    public String toString() {
        return "Produit [id=" + id + ", nom=" + nom + ", description=" + description
                + ", prix=" + prix + ", qteStock=" + qteStock + "]";
    }

    @Override
    public boolean equals(Object obj) {
        var produit = (Produit) obj;
        return this.id == produit.getId();
    }
}
