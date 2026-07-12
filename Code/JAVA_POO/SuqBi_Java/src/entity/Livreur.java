package entity;

import java.util.ArrayList;

public class Livreur {
    private int id;
    private String nom;
    private String prenom;
    private String tel;
    private String email;
    private boolean disponibilite;
    private ArrayList<Livraison> livraisons = new ArrayList<>();

    public Livreur() {
    }

    public Livreur(int id, String nom, String prenom, String tel, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.email = email;
        this.disponibilite = false; // indisponible par defaut a la creation du compte
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

    public boolean isDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

    public ArrayList<Livraison> getLivraisons() {
        return livraisons;
    }

    public void addLivraison(Livraison livraison) {
        this.livraisons.add(livraison);
    }

    public void removeLivraison(Livraison livraison) {
        this.livraisons.remove(livraison);
    }

    // ===== Methodes metier =====

    // Cas d'utilisation : Renseigner sa disponibilite
    public void renseignerDisponibilite(boolean dispo) {
        this.disponibilite = dispo;
    }

    // Cas d'utilisation : Accepter une livraison
    // Regle de gestion : un livreur doit etre disponible pour accepter une livraison
    public Livraison accepterLivraison(Commande commande) {
        if (!this.disponibilite) {
            System.out.println("Vous devez etre disponible pour accepter une livraison.");
            return null;
        }
        Livraison livraison = new Livraison();
        livraison.setLivreur(this);
        livraison.setClient(commande.getClient());
        livraison.setCommande(commande);
        commande.setLivraison(livraison);
        this.addLivraison(livraison);
        return livraison;
    }

    @Override
    public String toString() {
        return "Livreur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", tel=" + tel
                + ", email=" + email + ", disponibilite=" + disponibilite + "]";
    }

    @Override
    public boolean equals(Object obj) {
        var livreur = (Livreur) obj;
        return this.id == livreur.getId();
    }
}
