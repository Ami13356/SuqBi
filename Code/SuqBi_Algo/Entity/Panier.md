## NB : "date" est utilise ici comme type primitif (equivalent LocalDate), avec la
## fonction predefinie dateDuJour() qui retourne la date systeme courante (equivalent LocalDate.now())

Type  Panier =Classe
Debut
   prive id:entier
   prive dateCreation:date
   prive client:Client
   prive tLignes:TabLignePanier
   prive nbreLignes:entier
   prive valide:booleen

## Constructeur
     public  Panier()
     Debut
          this.dateCreation←dateDuJour()
          this.nbreLignes←0
          this.valide←Faux
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

## Getters & Setters  dateCreation
     public fonction getDateCreation():date
     Debut
     retourner this.dateCreation
     Fin
     public procedure setDateCreation(D dateCreation:date)
     Debut
     this.dateCreation←dateCreation
     Fin

## Getters & Setters  client
     public fonction getClient():Client
     Debut
     retourner this.client
     Fin
     public procedure setClient(D client:Client)
     Debut
     this.client←client
     Fin

## Gestion des lignes du panier 
     public procedure getLignes(R lignes:TabLignePanier, R nb:entier)
     var i:entier
     Debut
          pour(i←1;i<=this.nbreLignes;i←i+1) faire
               lignes[i]←this.tLignes[i]
          Fpour
          nb←this.nbreLignes
     Fin

     public procedure addLigne(D ligne:LignePanier)
     Debut
          this.nbreLignes←this.nbreLignes+1
          this.tLignes[this.nbreLignes]←ligne
     Fin

     public procedure removeLigne(D ligne:LignePanier)
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

## Getters  valide
     public fonction isValide():booleen
     Debut
     retourner this.valide
     Fin

## Methode metier : le panier est-il vide ?
     public fonction isVide():booleen
     Debut
          retourner (this.nbreLignes=0)
     Fin

## Methode metier : figer les lignes du panier avant de passer commande
     public procedure validerPanier()
     Debut
          this.valide←Vrai
     Fin

## Methode metier : vider le panier apres passage de commande
     public procedure vider()
     Debut
          this.nbreLignes←0
          this.valide←Faux
     Fin

## Methode metier : montant total du panier
     public fonction getMontantTotal():reel
     var i:entier
     total:reel
     Debut
          total←0
          pour(i←1;i<=this.nbreLignes;i←i+1) faire
               total←total+this.tLignes[i].getSousTotal()
          Fpour
          retourner total
     Fin
## toString()
     public fonction toString():chaine
     Debut
          retourner "Panier [nbLignes="+this.nbreLignes+", montantTotal="+this.getMontantTotal()+"]"
     Fin

FinClasse
