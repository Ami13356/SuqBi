Type  LivreurService =Classe
Debut
   prive static tLivreurs:TabLivreur
   prive static nbreLivreurs:entier
   prive static prochainId:entier

prive LivreurService()
Debut
     LivreurService.nbreLivreurs←0
     LivreurService.prochainId←1
Fin

prive static fonction emailExiste(D email:chaine):booleen
var i:entier
Debut
     pour(i←1;i<=LivreurService.nbreLivreurs;i←i+1) faire
          si(minuscule(LivreurService.tLivreurs[i].getEmail())=minuscule(email)) alors
               retourner Vrai
          Fsi
     Fpour
     retourner Faux
Fin

//Cas d'utilisation : Creer un compte (Livreur)
public static fonction creerCompte(D nom:chaine, prenom:chaine, tel:chaine, email:chaine):Livreur
var livreur:Livreur
Debut
     si(LivreurService.emailExiste(email)) alors
          Ecrire("Ce compte existe deja.")
          retourner null
     Fsi
     livreur←new Livreur(LivreurService.prochainId,nom,prenom,tel,email)
     LivreurService.prochainId←LivreurService.prochainId+1
     LivreurService.nbreLivreurs←LivreurService.nbreLivreurs+1
     LivreurService.tLivreurs[LivreurService.nbreLivreurs]←livreur
     retourner livreur
Fin

public static fonction seConnecter(D email:chaine):Livreur
var i:entier
Debut
     pour(i←1;i<=LivreurService.nbreLivreurs;i←i+1) faire
          si(minuscule(LivreurService.tLivreurs[i].getEmail())=minuscule(email)) alors
               retourner LivreurService.tLivreurs[i]
          Fsi
     Fpour
     Ecrire("Compte introuvable.")
     retourner null
Fin

public static procedure getTousLesLivreurs(R livreurs:TabLivreur, R nb:entier)
var i:entier
Debut
     pour(i←1;i<=LivreurService.nbreLivreurs;i←i+1) faire
          livreurs[i]←LivreurService.tLivreurs[i]
     Fpour
     nb←LivreurService.nbreLivreurs
Fin

## Cas d'utilisation Livreur : Consulter les commandes a livrer
## Regle de gestion : uniquement si le livreur est disponible
     public static procedure consulterCommandesALivrer(D livreur:Livreur, R resultat:TabCommande, R nbResultat:entier)
     var i,nbTotal:entier
     toutes:TabCommande
     Debut
          nbResultat←0
          si(NON livreur.isDisponibilite()) alors
               Ecrire("Vous devez etre disponible pour accepter une livraison.")
               retourner
          Fsi
          CommandeService.getToutesLesCommandes(toutes,nbTotal)
          pour(i←1;i<=nbTotal;i←i+1) faire
               si(toutes[i].getStatut()="en attente de livraison" ET toutes[i].getLivraison()=null) alors
                    nbResultat←nbResultat+1
                    resultat[nbResultat]←toutes[i]
               Fsi
          Fpour
     Fin

## Cas d'utilisation Admin : Ajouter un livreur
     public static fonction ajouterLivreur(D nom:chaine, prenom:chaine, tel:chaine, email:chaine):Livreur
     Debut
          retourner LivreurService.creerCompte(nom,prenom,tel,email)
     Fin

## Cas d'utilisation Admin : Modifier un livreur
     public static procedure modifierLivreur(D livreur:Livreur, nom:chaine, prenom:chaine, tel:chaine, email:chaine)
     Debut
          livreur.setNom(nom)
          livreur.setPrenom(prenom)
          livreur.setTel(tel)
          livreur.setEmail(email)
     Fin

## Cas d'utilisation Admin : Supprimer un livreur
     public static procedure supprimerLivreur(D livreur:Livreur)
     var i,pos:entier
     Debut
          pos←0
          pour(i←1;i<=LivreurService.nbreLivreurs;i←i+1) faire
               si(LivreurService.tLivreurs[i].equals(livreur)) alors
                    pos←i
               Fsi
          Fpour
          si(pos>0) alors
               pour(i←pos;i<LivreurService.nbreLivreurs;i←i+1) faire
                    LivreurService.tLivreurs[i]←LivreurService.tLivreurs[i+1]
               Fpour
               LivreurService.nbreLivreurs←LivreurService.nbreLivreurs-1
          Fsi
     Fin

FinClasse
