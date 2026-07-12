## Classe client
Type  Client =Classe
Debut
   prive id:entier
   prive nom:chaine
   prive prenom:chaine
   prive tel:chaine
   prive email:chaine
   prive mdp:chaine
   prive panier:Panier
   prive tCommandes:TabCommande
   prive nbreCommandes:entier
   prive tLivraisons:TabLivraison
   prive nbreLivraisons:entier

## Constructeurs
     public  Client()
     Debut
          this.nbreCommandes←0
          this.nbreLivraisons←0
     Fin

     public  Client(D id:entier, nom:chaine, prenom:chaine, tel:chaine, email:chaine, mdp:chaine)
     Debut
          this.id←id
          this.nom←nom
          this.prenom←prenom
          this.tel←tel
          this.email←email
          this.mdp←mdp
          this.panier←new Panier()
          this.panier.setClient(this)
          this.nbreCommandes←0
          this.nbreLivraisons←0
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

## Getters & Setters  mdp
     public fonction getMdp():chaine
     Debut
     retourner this.mdp
     Fin
     public procedure setMdp(D mdp:chaine)
     Debut
     this.mdp←mdp
     Fin

## Getters & Setters  panier
     public fonction getPanier():Panier
     Debut
     retourner this.panier
     Fin
     public procedure setPanier(D panier:Panier)
     Debut
     this.panier←panier
     Fin

## Gestion des commandes du client 
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
               si(this.tCommandes[i]=commande) alors
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

## Gestion des livraisons du client 
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

## ===== Methodes metier (issues des diagrammes de sequence/activite) =====

## Cas d'utilisation : Ajouter un produit au panier
     public fonction ajouterProduitAuPanier(D produit:Produit, quantite:entier):booleen
     Debut
          si(NON produit.verifierStock(quantite)) alors
               Ecrire("Stock insuffisant pour le produit : ",produit.getNom())
               retourner Faux
          Fsi
          this.panier.addLigne(new LignePanier(produit,quantite))
          retourner Vrai
     Fin

## Cas d'utilisation : Passer une commande (a partir du panier valide)
## NB : simplification assumee -> la commande est rattachee au vendeur du premier produit du panier
     public fonction passerCommande():Commande
     var commande:Commande
     ligneCommande:LigneCommande
     lignesPanier:TabLignePanier
     nbLignesPanier,i:entier
     Debut
          si(this.panier.isVide()) alors
               Ecrire("Le panier est vide.")
               retourner null
          Fsi
          commande←new Commande()
          commande.setClient(this)
          this.panier.getLignes(lignesPanier,nbLignesPanier)
          commande.setVendeur(lignesPanier[1].getProduit().getVendeur())

          pour(i←1;i<=nbLignesPanier;i←i+1) faire
               ligneCommande←new LigneCommande(lignesPanier[i].getProduit(),lignesPanier[i].getQuantite())
               commande.addLigne(ligneCommande)
               lignesPanier[i].getProduit().diminuerStock(lignesPanier[i].getQuantite())
          Fpour
          commande.calculerMontantTotal()

          this.addCommande(commande)
          commande.getVendeur().addCommande(commande)
          this.panier.vider()
          retourner commande
     Fin
## toString()
     public fonction toString():chaine
     Debut
          retourner "Client [id="+this.id+", nom="+this.nom+", prenom="+this.prenom+", tel="+this.tel+", email="+this.email+"]"
     Fin

     public fonction equals(D client:Client):booleen
     Debut
          retourner (this.id=client.getId())
     Fin

FinClasse

## Déclaration du tableau Client 
     const Taille=100
     Type TabClient=Tableau[1..Taille] Client
