Type  LivraisonService =Classe
Debut
   prive static tLivraisons:TabLivraison
   prive static nbreLivraisons:entier
   prive static prochainId:entier
     prive LivraisonService()
     Debut
          LivraisonService.nbreLivraisons←0
          LivraisonService.prochainId←1
     Fin

## Cas d'utilisation Livreur : Accepter une livraison
     public static fonction accepterLivraison(D livreur:Livreur, commande:Commande):Livraison
     var livraison:Livraison
     Debut
          livraison←livreur.accepterLivraison(commande)
          si(livraison=null) alors
               retourner null
          Fsi
          livraison.setId(LivraisonService.prochainId)
          LivraisonService.prochainId←LivraisonService.prochainId+1
          LivraisonService.nbreLivraisons←LivraisonService.nbreLivraisons+1
          LivraisonService.tLivraisons[LivraisonService.nbreLivraisons]←livraison
          retourner livraison
     Fin

## Cas d'utilisation Livreur : Marquer livraison comme effectuee
     public static procedure marquerEffectuee(D livraison:Livraison)
     Debut
          livraison.marquerEffectuee()
     Fin

## Cas d'utilisation Livreur : Consulter ses livraisons
     public static procedure getLivraisonsParLivreur(D livreur:Livreur, R livraisons:TabLivraison, R nb:entier)
     Debut
          livreur.getLivraisons(livraisons,nb)
     Fin

FinClasse
