Type  Vendeur =Classe
Debut
   prive id:entier
   prive nom:chaine
   prive prenom:chaine
   prive tel:chaine
   prive email:chaine
   prive tProduits:TabProduit
   prive nbreProduits:entier
   prive tCommandes:TabCommande
   prive nbreCommandes:entier

## Constructeurs
     public  Vendeur()
     Debut
          this.nbreProduits←0
          this.nbreCommandes←0
     Fin

     public  Vendeur(D id:entier, nom:chaine, prenom:chaine, tel:chaine, email:chaine)
     Debut
          this.id←id
          this.nom←nom
          this.prenom←prenom
          this.tel←tel
          this.email←email
          this.nbreProduits←0
          this.nbreCommandes←0
     Fin

## Getters & Setters  id
     public fonction getId():entier
     Debut
     retourner this.id
     Fin
     public procedure setId(D id:entier)
     Debut
     this.id←id
     Fin

## Getters & Setters  nom
     public fonction getNom():chaine
     Debut
     retourner this.nom
     Fin
     public procedure setNom(D nom:chaine)
     Debut
     this.nom←nom
     Fin

## Getters & Setters  prenom
     public fonction getPrenom():chaine
     Debut
     retourner this.prenom
     Fin
     public procedure setPrenom(D prenom:chaine)
     Debut
     this.prenom←prenom
     Fin

## Getters & Setters  tel
     public fonction getTel():chaine
     Debut
     retourner this.tel
     Fin
     public procedure setTel(D tel:chaine)
     Debut
     this.tel←tel
     Fin

## Getters & Setters  email
     public fonction getEmail():chaine
     Debut
     retourner this.email
     Fin
     public procedure setEmail(D email:chaine)
     Debut
     this.email←email
     Fin

## Gestion des produits du vendeur 
     public procedure getProduits(R produits:TabProduit, R nb:entier)
     var i:entier
     Debut
          pour(i←1;i<=this.nbreProduits;i←i+1) faire
               produits[i]←this.tProduits[i]
          Fpour
          nb←this.nbreProduits
     Fin

     public procedure addProduit(D produit:Produit)
     Debut
          this.nbreProduits←this.nbreProduits+1
          this.tProduits[this.nbreProduits]←produit
     Fin

     public procedure removeProduit(D produit:Produit)
     var i,pos:entier
     Debut
          pos←0
          pour(i←1;i<=this.nbreProduits;i←i+1) faire
               si(this.tProduits[i].equals(produit)) alors
                    pos←i
               Fsi
          Fpour
          si(pos>0) alors
               pour(i←pos;i<this.nbreProduits;i←i+1) faire
                    this.tProduits[i]←this.tProduits[i+1]
               Fpour
               this.nbreProduits←this.nbreProduits-1
          Fsi
     Fin

## Gestion des commandes recues par le vendeur 
     public procedure getCommandes(R commandes:TabCommande, R nb:entier)
     var i:entier
     Debut
          pour(i←1;i<=this.nbreCommandes;i←i+1) faire
               commandes[i]←this.tCommandes[i]
          Fpour
          nb←this.nbreCommandes
     Fin

     public procedure addCommande(D commande:Commande)
     Debut
          this.nbreCommandes←this.nbreCommandes+1
          this.tCommandes[this.nbreCommandes]←commande
     Fin

     public procedure removeCommande(D commande:Commande)
     var i,pos:entier
     Debut
          pos←0
          pour(i←1;i<=this.nbreCommandes;i←i+1) faire
               si(this.tCommandes[i].equals(commande)) alors
                    pos←i
               Fsi
          Fpour
          si(pos>0) alors
               pour(i←pos;i<this.nbreCommandes;i←i+1) faire
                    this.tCommandes[i]←this.tCommandes[i+1]
               Fpour
               this.nbreCommandes←this.nbreCommandes-1
          Fsi
     Fin
## toString()
     public fonction toString():chaine
     Debut
          retourner "Vendeur [id="+this.id+", nom="+this.nom+", prenom="+this.prenom+", tel="+this.tel+", email="+this.email+"]"
     Fin

     public fonction equals(D vendeur:Vendeur):booleen
     Debut
          retourner (this.id=vendeur.getId())
     Fin

FinClasse
## Déclaration du tableau Vendeur
const Taille=100
Type TabVendeur=Tableau[1..Taille] Vendeur
