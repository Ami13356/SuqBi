## classe produit
Type  Produit =Classe
Debut
   prive id:entier
   prive nom:chaine
   prive description:chaine
   prive prix:reel
   prive qteStock:entier
   prive vendeur:Vendeur

## Constructeurs
public  Produit()
Debut
Fin

public  Produit(D id:entier, nom:chaine, description:chaine, prix:reel, qteStock:entier)
Debut
     this.id←id
     this.nom←nom
     this.description←description
     this.prix←prix
     this.qteStock←qteStock
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

## Getters & Setters  description
   public fonction getDescription():chaine
   Debut
      retourner this.description
   Fin
   public procedure setDescription(D description:chaine)
   Debut
      this.description←description
   Fin

## Getters & Setters  prix
   public fonction getPrix():reel
   Debut
      retourner this.prix
   Fin
   public procedure setPrix(D prix:reel)
   Debut
      this.prix←prix
   Fin

## Getters & Setters  qteStock
   public fonction getQteStock():entier
   Debut
      retourner this.qteStock
   Fin
   public procedure setQteStock(D qteStock:entier)
   Debut
      this.qteStock←qteStock
   Fin

## Getters & Setters  vendeur (reference vers l'objet Vendeur)
   public fonction getVendeur():Vendeur
   Debut
      retourner this.vendeur
   Fin
   public procedure setVendeur(D vendeur:Vendeur)
   Debut
      this.vendeur←vendeur
   Fin

## Methode metier : verifie si la quantite demandee est disponible en stock
   public fonction verifierStock(D quantite:entier):booleen
   Debut
      retourner (this.qteStock>=quantite)
   Fin

## Methode metier : diminue le stock apres une commande
   public procedure diminuerStock(D quantite:entier)
   Debut
      this.qteStock←this.qteStock-quantite
   Fin

## Serialisation Objet convertir en chaine
   public fonction toString():chaine
   Debut
      retourner "Produit [id="+this.id+", nom="+this.nom+", description="+this.description+", prix="+this.prix+", qteStock="+this.qteStock+"]"
   Fin

## Comparaison de deux produits (equals Java)
   public fonction equals(D produit:Produit):booleen
   Debut
      retourner (this.id=produit.getId())
   Fin

FinClasse

## Tableau de references d'objets Produit

   const Taille=100
   Type TabProduit=Tableau[1..Taille] Produit  