Type  LivreurView =Classe
Debut

prive LivreurView()
Debut
Fin

public static procedure afficherMenuLivreur(D livreur:Livreur)
var choix:entier
Debut
     repeter
          Ecrire("")
          Ecrire("===== Menu Livreur (",livreur.getPrenom(),") =====")
          Ecrire("1. Renseigner ma disponibilite")
          Ecrire("2. Consulter les commandes a livrer")
          Ecrire("3. Accepter une livraison")
          Ecrire("4. Marquer une livraison comme effectuee")
          Ecrire("5. Consulter mes livraisons")
          Ecrire("6. Se deconnecter")
          choix←AuthView.saisieEntier("Saisir votre choix : ")

          cas(choix)
               1:
                    LivreurView.renseignerDisponibilite(livreur)
               2:
                    LivreurView.afficherCommandesALivrer(livreur)
               3:
                    LivreurView.accepterLivraison(livreur)
               4:
                    LivreurView.marquerEffectuee(livreur)
               5:
                    LivreurView.afficherLivraisons(livreur)
               6:
                    Ecrire("Deconnexion...")
               Autre:
                    Ecrire("Choix invalide. Veuillez reessayer.")
          Fcas
     jusqu'a (choix=6)
Fin

prive static procedure renseignerDisponibilite(D livreur:Livreur)
var etat:chaine
    choix:chaine
Debut
     si(livreur.isDisponibilite()) alors
          etat←"disponible"
     sinon
          etat←"indisponible"
     Fsi
     Ecrire("Etat actuel : ",etat)
     choix←AuthView.saisieChaine("Nouveau statut (disponible/indisponible) : ")
     livreur.renseignerDisponibilite(minuscule(choix)="disponible")
     Ecrire("Disponibilite mise a jour.")
Fin

prive static procedure afficherCommandesALivrer(D livreur:Livreur)
var commandes:TabCommande
    nb,i:entier
Debut
     LivreurService.consulterCommandesALivrer(livreur,commandes,nb)
     si(nb=0) alors
          Ecrire("Aucune commande a livrer actuellement.")
     sinon
          pour(i←1;i<=nb;i←i+1) faire
               Ecrire(commandes[i].toString())
          Fpour
     Fsi
Fin

prive static procedure accepterLivraison(D livreur:Livreur)
var commandes:TabCommande
    nb,i,idCommande:entier
    commandeChoisie:Commande
    livraison:Livraison
Debut
     LivreurService.consulterCommandesALivrer(livreur,commandes,nb)
     si(nb=0) alors
          retourner
     Fsi
     pour(i←1;i<=nb;i←i+1) faire
          Ecrire(commandes[i].toString())
     Fpour
     idCommande←AuthView.saisieEntier("Saisir l'id de la commande a accepter : ")
     commandeChoisie←null
     pour(i←1;i<=nb;i←i+1) faire
          si(commandes[i].getId()=idCommande) alors
               commandeChoisie←commandes[i]
          Fsi
     Fpour
     si(commandeChoisie=null) alors
          Ecrire("Commande introuvable.")
          retourner
     Fsi
     livraison←LivraisonService.accepterLivraison(livreur,commandeChoisie)
     si(livraison<>null) alors
          Ecrire("Livraison acceptee : ",livraison.toString())
     Fsi
Fin

prive static procedure marquerEffectuee(D livreur:Livreur)
var toutes:TabLivraison
    enCours:TabLivraison
    nbTotal,nbEnCours,i,idLivraison:entier
    trouve:booleen
Debut
     livreur.getLivraisons(toutes,nbTotal)
     nbEnCours←0
     pour(i←1;i<=nbTotal;i←i+1) faire
          si(toutes[i].getStatut()="en cours") alors
               nbEnCours←nbEnCours+1
               enCours[nbEnCours]←toutes[i]
          Fsi
     Fpour
     si(nbEnCours=0) alors
          Ecrire("Aucune livraison en cours.")
          retourner
     Fsi
     pour(i←1;i<=nbEnCours;i←i+1) faire
          Ecrire(enCours[i].toString())
     Fpour
     idLivraison←AuthView.saisieEntier("Saisir l'id de la livraison effectuee : ")
     trouve←Faux
     pour(i←1;i<=nbEnCours;i←i+1) faire
          si(enCours[i].getId()=idLivraison ET NON trouve) alors
               LivraisonService.marquerEffectuee(enCours[i])
               Ecrire("Livraison effectuee avec succes.")
               trouve←Vrai
          Fsi
     Fpour
     si(NON trouve) alors
          Ecrire("Livraison introuvable.")
     Fsi
Fin

prive static procedure afficherLivraisons(D livreur:Livreur)
var livraisons:TabLivraison
    nb,i:entier
Debut
     livreur.getLivraisons(livraisons,nb)
     si(nb=0) alors
          Ecrire("Aucune livraison pour le moment.")
     sinon
          pour(i←1;i<=nb;i←i+1) faire
               Ecrire(livraisons[i].toString())
          Fpour
     Fsi
Fin

FinClasse
