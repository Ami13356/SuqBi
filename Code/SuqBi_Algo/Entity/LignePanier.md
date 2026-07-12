## Classe association entre Panier et Produit (equivalent Java : LignePanier)

Type  LignePanier =Classe
Debut
   prive quantite:entier
   prive produit:Produit

## Constructeurs
   public  LignePanier()
   Debut
   Fin

   public  LignePanier(D produit:Produit, quantite:entier)
   Debut
      this.produit←produit
      this.quantite←quantite
   Fin

## Getters & Setters  quantite
   public fonction getQuantite():entier
   Debut
      retourner this.quantite
   Fin
   public procedure setQuantite(D quantite:entier)
   Debut
      this.quantite←quantite
   Fin

## Getters & Setters  produit
   public fonction getProduit():Produit
   Debut
      retourner this.produit
   Fin
   public procedure setProduit(D produit:Produit)
   Debut
      this.produit←produit
   Fin

## Methode metier : sous-total de la ligne (prix courant du produit * quantite)
   public fonction getSousTotal():reel
   Debut
      retourner this.produit.getPrix()*this.quantite
   Fin
## toString()
   public fonction toString():chaine
   Debut
      retourner "LignePanier [produit="+this.produit.getNom()+", quantite="+this.quantite+", sousTotal="+this.getSousTotal()+"]"
   Fin

FinClasse
## Déclaration du tableau LignePanier
   const Taille=100
   Type TabLignePanier=Tableau[1..Taille] LignePanier
