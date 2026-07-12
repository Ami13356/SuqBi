Type  VendeurService =Classe
Debut
   prive static tVendeurs:TabVendeur
   prive static nbreVendeurs:entier
   prive static prochainId:entier

## Constructeur privé : classe utilitaire, non instanciable
     prive VendeurService()
     Debut
          VendeurService.nbreVendeurs←0
          VendeurService.prochainId←1
     Fin

     prive static fonction emailExiste(D email:chaine):booleen
     var i:entier
     Debut
          pour(i←1;i<=VendeurService.nbreVendeurs;i←i+1) faire
               si(minuscule(VendeurService.tVendeurs[i].getEmail())=minuscule(email)) alors
                    retourner Vrai
               Fsi
          Fpour
          retourner Faux
     Fin

## Cas d'utilisation : Creer un compte (Vendeur)
     public static fonction creerCompte(D nom:chaine, prenom:chaine, tel:chaine, email:chaine):Vendeur
     var vendeur:Vendeur
     Debut
          si(VendeurService.emailExiste(email)) alors
               Ecrire("Ce compte existe deja.")
               retourner null
          Fsi
          vendeur←new Vendeur(VendeurService.prochainId,nom,prenom,tel,email)
          VendeurService.prochainId←VendeurService.prochainId+1
          VendeurService.nbreVendeurs←VendeurService.nbreVendeurs+1
          VendeurService.tVendeurs[VendeurService.nbreVendeurs]←vendeur
          retourner vendeur
     Fin

## NB : le mot de passe des acteurs Vendeur/Livreur n'est pas present dans le
## diagramme de classe valide (seul Client a l'attribut mdp). Ici on simule une
## connexion par email uniquement, comme convenu -> a confirmer avec l'encadrant
## si un mot de passe doit etre ajoute officiellement.
     public static fonction seConnecter(D email:chaine):Vendeur
     var i:entier
     Debut
          pour(i←1;i<=VendeurService.nbreVendeurs;i←i+1) faire
               si(minuscule(VendeurService.tVendeurs[i].getEmail())=minuscule(email)) alors
                    retourner VendeurService.tVendeurs[i]
               Fsi
          Fpour
          Ecrire("Compte introuvable.")
          retourner null
     Fin

     public static procedure getTousLesVendeurs(R vendeurs:TabVendeur, R nb:entier)
     var i:entier
     Debut
          pour(i←1;i<=VendeurService.nbreVendeurs;i←i+1) faire
               vendeurs[i]←VendeurService.tVendeurs[i]
          Fpour
          nb←VendeurService.nbreVendeurs
     Fin

## Cas d'utilisation Admin : Ajouter un vendeur
     public static fonction ajouterVendeur(D nom:chaine, prenom:chaine, tel:chaine, email:chaine):Vendeur
     Debut
          retourner VendeurService.creerCompte(nom,prenom,tel,email)
     Fin

## Cas d'utilisation Admin : Modifier un vendeur
     public static procedure modifierVendeur(D vendeur:Vendeur, nom:chaine, prenom:chaine, tel:chaine, email:chaine)
     Debut
          vendeur.setNom(nom)
          vendeur.setPrenom(prenom)
          vendeur.setTel(tel)
          vendeur.setEmail(email)
     Fin

## Cas d'utilisation Admin : Supprimer un vendeur
     public static procedure supprimerVendeur(D vendeur:Vendeur)
     var i,pos:entier
     Debut
          pos←0
          pour(i←1;i<=VendeurService.nbreVendeurs;i←i+1) faire
               si(VendeurService.tVendeurs[i].equals(vendeur)) alors
                    pos←i
               Fsi
          Fpour
          si(pos>0) alors
               pour(i←pos;i<VendeurService.nbreVendeurs;i←i+1) faire
                    VendeurService.tVendeurs[i]←VendeurService.tVendeurs[i+1]
               Fpour
               VendeurService.nbreVendeurs←VendeurService.nbreVendeurs-1
          Fsi
     Fin

FinClasse
