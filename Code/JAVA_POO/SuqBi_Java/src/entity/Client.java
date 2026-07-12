package entity;

import java.util.ArrayList;

public class Client {
    private int id;
    private String nom;
    private String prenom;
    private String tel;
    private String email;
    private String mdp;
    private Panier panier;
    private ArrayList<Commande> commandes = new ArrayList<>();
    private ArrayList<Livraison> livraisons = new ArrayList<>();

    public Client() {
    }

    public Client(int id, String nom, String prenom, String tel, String email, String mdp) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.email = email;
        this.mdp = mdp;
        this.panier = new Panier();
        this.panier.setClient(this);
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

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
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

    public ArrayList<Livraison> getLivraisons() {
        return livraisons;
    }

    public void addLivraison(Livraison livraison) {
        this.livraisons.add(livraison);
    }

    public void removeLivraison(Livraison livraison) {
        this.livraisons.remove(livraison);
    }

    // ===== Methodes metier (issues des diagrammes de sequence/activite) =====

    // Cas d'utilisation : Ajouter un produit au panier
    public boolean ajouterProduitAuPanier(Produit produit, int quantite) {
        if (!produit.verifierStock(quantite)) {
            System.out.println("Stock insuffisant pour le produit : " + produit.getNom());
            return false;
        }
        this.panier.addLigne(new LignePanier(produit, quantite));
        return true;
    }

    // Cas d'utilisation : Passer une commande (a partir du panier valide)
    // NB : simplification assumee -> la commande est rattachee au vendeur du premier produit du panier
    public Commande passerCommande() {
        if (this.panier.isVide()) {
            System.out.println("Le panier est vide.");
            return null;
        }
        Commande commande = new Commande();
        commande.setClient(this);
        commande.setVendeur(this.panier.getLignes().get(0).getProduit().getVendeur());

        for (LignePanier lignePanier : this.panier.getLignes()) {
            LigneCommande ligneCommande = new LigneCommande(lignePanier.getProduit(), lignePanier.getQuantite());
            commande.addLigne(ligneCommande);
            lignePanier.getProduit().diminuerStock(lignePanier.getQuantite());
        }
        commande.calculerMontantTotal();

        this.addCommande(commande);
        commande.getVendeur().addCommande(commande);
        this.panier.vider();
        return commande;
    }

    @Override
    public String toString() {
        return "Client [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", tel=" + tel + ", email=" + email + "]";
    }

    @Override
    public boolean equals(Object obj) {
        var client = (Client) obj;
        return this.id == client.getId();
    }
}
