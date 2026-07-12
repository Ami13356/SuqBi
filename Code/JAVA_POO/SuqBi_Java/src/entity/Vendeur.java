package entity;

import java.util.ArrayList;

public class Vendeur {
    private int id;
    private String nom;
    private String prenom;
    private String tel;
    private String email;
    private ArrayList<Produit> produits = new ArrayList<>();
    private ArrayList<Commande> commandes = new ArrayList<>();

    public Vendeur() {
    }

    public Vendeur(int id, String nom, String prenom, String tel, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.email = email;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Produit> getProduits() {
        return produits;
    }

    public void addProduit(Produit produit) {
        this.produits.add(produit);
    }

    public void removeProduit(Produit produit) {
        this.produits.remove(produit);
    }

    public ArrayList<Commande> getCommandes() {
        return commandes;
    }

    public void addCommande(Commande commande) {
        this.commandes.add(commande);
    }

    public void removeCommande(Commande commande) {
        this.commandes.remove(commande);
    }

    @Override
    public String toString() {
        return "Vendeur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", tel=" + tel + ", email=" + email + "]";
    }

    @Override
    public boolean equals(Object obj) {
        var vendeur = (Vendeur) obj;
        return this.id == vendeur.getId();
    }
}
