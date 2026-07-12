## classe Livreur
Type  Livreur =Classe
Debut
   prive id:entier
   prive nom:chaine
   prive prenom:chaine
   prive tel:chaine
   prive email:chaine
   prive disponibilite:booleen
   prive tLivraisons:TabLivraison
   prive nbreLivraisons:entier

## Constructeurs
     public  Livreur()
     Debut
          this.nbreLivraisons←0
     Fin

     public  Livreur(D id:entier, nom:chaine, prenom:chaine, tel:chaine, email:chaine)
     Debut
          this.id←id
          this.nom←nom
          this.prenom←prenom
          this.tel←tel
          this.email←email
          this.disponibilite←Faux  //indisponible par defaut a la creation du compte
          this.nbreLivraisons←0
     Fin

## Getters ,Setters  id
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

## Getters & Setters  disponibilite
     public fonction isDisponibilite():booleen
     Debut
     retourner this.disponibilite
     Fin
     public procedure setDisponibilite(D disponibilite:booleen)
     Debut
     this.disponibilite←disponibilite
     Fin

## Gestion des livraisons du livreur
     public procedure getLivraisons(R livraisons:TabLivraison, R nb:entier)
     var i:entier
     Debut
          pour(i←1;i<=this.nbreLivraisons;i←i+1) faire
               livraisons[i]←this.tLivraisons[i]
          Fpour
          nb←this.nbreLivraisons
     Fin

     public procedure addLivraison(D livraison:Livraison)
     Debut
          this.nbreLivraisons←this.nbreLivraisons+1
          this.tLivraisons[this.nbreLivraisons]←livraison
     Fin

     public procedure removeLivraison(D livraison:Livraison)
     var i,pos:entier
     Debut
          pos←0
          pour(i←1;i<=this.nbreLivraisons;i←i+1) faire
               si(this.tLivraisons[i]=livraison) alors
                    pos←i
               Fsi
          Fpour
          si(pos>0) alors
               pour(i←pos;i<this.nbreLivraisons;i←i+1) faire
                    this.tLivraisons[i]←this.tLivraisons[i+1]
               Fpour
               this.nbreLivraisons←this.nbreLivraisons-1
          Fsi
     Fin

## ===== Methodes metier =====

## Cas d'utilisation : Renseigner sa disponibilite
     public procedure renseignerDisponibilite(D dispo:booleen)
     Debut
          this.disponibilite←dispo
     Fin

## Cas d'utilisation : Accepter une livraison
## Regle de gestion : un livreur doit etre disponible pour accepter une livraison
     public fonction accepterLivraison(D commande:Commande):Livraison
     var livraison:Livraison
     Debut
          si(NON this.disponibilite) alors
               Ecrire("Vous devez etre disponible pour accepter une livraison.")
               retourner null
          Fsi
          livraison←new Livraison()
          livraison.setLivreur(this)
          livraison.setClient(commande.getClient())
          livraison.setCommande(commande)
          commande.setLivraison(livraison)
          this.addLivraison(livraison)
          retourner livraison
     Fin
## toString()
     public fonction toString():chaine
     Debut
          retourner "Livreur [id="+this.id+", nom="+this.nom+", prenom="+this.prenom+", tel="+this.tel+", email="+this.email+", disponibilite="+this.disponibilite+"]"
     Fin

     public fonction equals(D livreur:Livreur):booleen
     Debut
          retourner (this.id=livreur.getId())
     Fin

FinClasse
## Déclaration du tableau Livreur
const Taille=100
Type TabLivreur=Tableau[1..Taille] Livreur
