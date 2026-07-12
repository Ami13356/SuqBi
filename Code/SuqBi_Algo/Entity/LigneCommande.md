## Classe association entre Commande et Produit (equivalent Java : LigneCommande)

Type  LigneCommande =Classe
Debut
   prive quantite:entier
   prive prixUnitaire:reel
   prive produit:Produit

## Constructeurs
   public  LigneCommande()
   Debut
   Fin

## Le prix unitaire est fige au moment de la commande (copie du prix du produit)
   public  LigneCommande(D produit:Produit, quantite:entier)
   Debut
      this.produit←produit
      this.quantite←quantite
      this.prixUnitaire←produit.getPrix()
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

## Getters & Setters  prixUnitaire
   public fonction getPrixUnitaire():reel
   Debut
      retourner this.prixUnitaire
   Fin
   public procedure setPrixUnitaire(D prixUnitaire:reel)
   Debut
      this.prixUnitaire←prixUnitaire
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

## Methode metier : sous-total de la ligne
   public fonction getSousTotal():reel
   Debut
      retourner this.prixUnitaire*this.quantite
   Fin
## toString()
   public fonction toString():chaine
   Debut
      retourner "LigneCommande [produit="+this.produit.getNom()+", quantite="+this.quantite+", prixUnitaire="+this.prixUnitaire+", sousTotal="+this.getSousTotal()+"]"
   Fin

FinClasse
## Déclaration du tableau LigneCommande
   const Taille=100
   Type TabLigneCommande=Tableau[1..Taille] LigneCommande
