Type  ProduitService =Classe
Debut
   prive static tProduit:TabProduit
   prive static nbreProduit:entier
   prive static prochainId:entier

//Constructeur privé : classe utilitaire, non instanciable (equivalent du "private ProduitService()" Java)
prive ProduitService()
Debut
     ProduitService.nbreProduit←0
     ProduitService.prochainId←1
Fin

## Cas d'utilisation Vendeur : Ajouter un produit
     public static fonction ajouterProduit(D vendeur:Vendeur, nom:chaine, description:chaine, prix:reel, qteStock:entier):Produit
     var produit:Produit
     Debut
          produit←new Produit(ProduitService.prochainId,nom,description,prix,qteStock)
          ProduitService.prochainId←ProduitService.prochainId+1
          produit.setVendeur(vendeur)
          vendeur.addProduit(produit)
          ProduitService.nbreProduit←ProduitService.nbreProduit+1
          ProduitService.tProduit[ProduitService.nbreProduit]←produit
          retourner produit
     Fin

## Cas d'utilisation Vendeur/Admin : Modifier un produit
     public static procedure modifierProduit(D produit:Produit, nom:chaine, description:chaine, prix:reel, qteStock:entier)
     Debut
          produit.setNom(nom)
          produit.setDescription(description)
          produit.setPrix(prix)
          produit.setQteStock(qteStock)
     Fin

## Cas d'utilisation Vendeur/Admin : Supprimer un produit
     public static procedure supprimerProduit(D produit:Produit)
     var i,pos:entier
     Debut
          si(produit.getVendeur()<>null) alors
               produit.getVendeur().removeProduit(produit)
          Fsi
          ## Recherche de la position du produit dans le tableau
          pos←0
          pour(i←1;i<=ProduitService.nbreProduit;i←i+1) faire
               si(ProduitService.tProduit[i].equals(produit)) alors
                    pos←i
               Fsi
          Fpour
          ## Decalage des elements pour "supprimer" (equivalent remove() de l'ArrayList)
          si(pos>0) alors
               pour(i←pos;i<ProduitService.nbreProduit;i←i+1) faire
                    ProduitService.tProduit[i]←ProduitService.tProduit[i+1]
               Fpour
               ProduitService.nbreProduit←ProduitService.nbreProduit-1
          Fsi
     Fin

## Cas d'utilisation Client : Consulter les produits disponibles (qteStock>0)
     public static procedure getProduitsDisponibles(R disponibles:TabProduit, R nbDisponibles:entier)
     var i:entier
     Debut
          nbDisponibles←0
          pour(i←1;i<=ProduitService.nbreProduit;i←i+1) faire
               si(ProduitService.tProduit[i].getQteStock()>0) alors
                    nbDisponibles←nbDisponibles+1
                    disponibles[nbDisponibles]←ProduitService.tProduit[i]
               Fsi
          Fpour
     Fin

## Cas d'utilisation Client : Rechercher un produit (extend de Consulter les produits disponibles)
     public static procedure rechercherProduit(D motCle:chaine, R resultats:TabProduit, R nbResultats:entier)
     var i,nbDisp:entier
     disponibles:TabProduit
     Debut
          ProduitService.getProduitsDisponibles(disponibles,nbDisp)
          nbResultats←0
          pour(i←1;i<=nbDisp;i←i+1) faire
               si(contient(minuscule(disponibles[i].getNom()),minuscule(motCle))) alors
                    nbResultats←nbResultats+1
                    resultats[nbResultats]←disponibles[i]
               Fsi
          Fpour
     Fin

## Cas d'utilisation Admin : Consulter tous les produits (disponibles ou non)
     public static procedure getTousLesProduits(R tousProduits:TabProduit, R nb:entier)
     var i:entier
     Debut
          pour(i←1;i<=ProduitService.nbreProduit;i←i+1) faire
               tousProduits[i]←ProduitService.tProduit[i]
          Fpour
          nb←ProduitService.nbreProduit
     Fin

FinClasse
