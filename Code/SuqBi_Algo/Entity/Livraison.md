## classe Livraison
Type  Livraison =Classe
Debut
   prive id:entier
   prive dateLivraison:date
   prive statut:chaine  // "en cours", "effectuee"
   prive livreur:Livreur
   prive client:Client
   prive commande:Commande

## Constructeur
   public  Livraison()
   Debut
      this.dateLivraison←dateDuJour()
      this.statut←"en cours"
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

## Getters & Setters  dateLivraison
   public fonction getDateLivraison():date
   Debut
      retourner this.dateLivraison
   Fin
   public procedure setDateLivraison(D dateLivraison:date)
   Debut
      this.dateLivraison←dateLivraison
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

## Getters & Setters  livreur
   public fonction getLivreur():Livreur
   Debut
      retourner this.livreur
   Fin
   public procedure setLivreur(D livreur:Livreur)
   Debut
      this.livreur←livreur
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

## Getters & Setters  commande
   public fonction getCommande():Commande
   Debut
      retourner this.commande
   Fin
   public procedure setCommande(D commande:Commande)
   Debut
      this.commande←commande
   Fin

## Methode metier : marquer la livraison comme effectuee
   public procedure marquerEffectuee()
   Debut
      this.statut←"effectuee"
   Fin
## toString()
   public fonction toString():chaine
   Debut
      retourner "Livraison [id="+this.id+", dateLivraison="+this.dateLivraison+", statut="+this.statut+"]"
   Fin

FinClasse
## Déclaration du tableau Livraison
   const Taille=100
   Type TabLivraison=Tableau[1..Taille] Livraison
