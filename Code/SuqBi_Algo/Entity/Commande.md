## Classe commande
Type  Commande =Classe
Debut
   prive id:entier
   prive dateCommande:date
   prive montantTotal:reel
   prive statut:chaine  // "en attente de livraison", "livree"
   prive client:Client
   prive vendeur:Vendeur
   prive tLignes:TabLigneCommande
   prive nbreLignes:entier
   prive livraison:Livraison

## Constructeur
     public  Commande()
     Debut
          this.dateCommande←dateDuJour()
          this.statut←"en attente de livraison"
          this.nbreLignes←0
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

## Getters & Setters  dateCommande
     public fonction getDateCommande():date
     Debut
     retourner this.dateCommande
     Fin
     public procedure setDateCommande(D dateCommande:date)
     Debut
     this.dateCommande←dateCommande
     Fin

## Getters & Setters  montantTotal
     public fonction getMontantTotal():reel
     Debut
     retourner this.montantTotal
     Fin
     public procedure setMontantTotal(D montantTotal:reel)
     Debut
     this.montantTotal←montantTotal
     Fin

## Getters & Setters  statut
     public fonction getStatut():chaine
     Debut
     retourner this.statut
     Fin
     public procedure setStatut(D statut:chaine)
     Debut
     this.statut←statut
     Fin

## Getters ,Setters  client
     public fonction getClient():Client
     Debut
     retourner this.client
     Fin
     public procedure setClient(D client:Client)
     Debut
     this.client←client
     Fin

## Getters & Setters  vendeur
     public fonction getVendeur():Vendeur
     Debut
     retourner this.vendeur
     Fin
     public procedure setVendeur(D vendeur:Vendeur)
     Debut
     this.vendeur←vendeur
     Fin

## Gestion des lignes de la commande 
     public procedure getLignes(R lignes:TabLigneCommande, R nb:entier)
     var i:entier
     Debut
          pour(i←1;i<=this.nbreLignes;i←i+1) faire
               lignes[i]←this.tLignes[i]
          Fpour
          nb←this.nbreLignes
     Fin

     public procedure addLigne(D ligne:LigneCommande)
     Debut
          this.nbreLignes←this.nbreLignes+1
          this.tLignes[this.nbreLignes]←ligne
     Fin

     public procedure removeLigne(D ligne:LigneCommande)
     var i,pos:entier
     Debut
          pos←0
          pour(i←1;i<=this.nbreLignes;i←i+1) faire
               si(this.tLignes[i]=ligne) alors
                    pos←i
               Fsi
          Fpour
          si(pos>0) alors
               pour(i←pos;i<this.nbreLignes;i←i+1) faire
                    this.tLignes[i]←this.tLignes[i+1]
               Fpour
               this.nbreLignes←this.nbreLignes-1
          Fsi
     Fin

## Getters & Setters  livraison
     public fonction getLivraison():Livraison
     Debut
     retourner this.livraison
     Fin
     public procedure setLivraison(D livraison:Livraison)
     Debut
     this.livraison←livraison
     Fin

## Methode metier : calcule le montant total a partir des lignes de commande
     public procedure calculerMontantTotal()
     var i:entier
     total:reel
     Debut
          total←0
          pour(i←1;i<=this.nbreLignes;i←i+1) faire
               total←total+this.tLignes[i].getSousTotal()
          Fpour
          this.montantTotal←total
     Fin
## toString()
     public fonction toString():chaine
     Debut
          retourner "Commande [id="+this.id+", dateCommande="+this.dateCommande+", montantTotal="+this.montantTotal+", statut="+this.statut+"]"
     Fin

FinClasse
## Déclaration du tableau commande 
     const Taille=100
     Type TabCommande=Tableau[1..Taille] Commande
