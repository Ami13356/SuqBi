Type  AdminView =Classe
Debut

prive AdminView()
Debut
Fin

public static procedure afficherMenuAdmin()
var choix:entier
Debut
     repeter
          Ecrire("")
          Ecrire("===== Menu Admin =====")
          Ecrire("1. Gerer les utilisateurs")
          Ecrire("2. Gerer les produits")
          Ecrire("3. Consulter les commandes")
          Ecrire("4. Se deconnecter")
          choix←AuthView.saisieEntier("Saisir votre choix : ")

          cas(choix)
               1:
                    AdminView.gererUtilisateurs()
               2:
                    AdminView.gererProduits()
               3:
                    AdminView.afficherCommandes()
               4:
                    Ecrire("Deconnexion...")
               Autre:
                    Ecrire("Choix invalide. Veuillez reessayer.")
          Fcas
     jusqu'a (choix=4)
Fin

## ===== Gerer les utilisateurs =====
     prive static procedure gererUtilisateurs()
     var type:entier
     Debut
          Ecrire("Type d'utilisateur : 1.Clients  2.Vendeurs  3.Livreurs")
          type←AuthView.saisieEntier("Choix : ")
          cas(type)
               1:
                    AdminView.gererClients()
               2:
                    AdminView.gererVendeurs()
               3:
                    AdminView.gererLivreurs()
               Autre:
                    Ecrire("Choix invalide.")
          Fcas
     Fin

     prive static procedure gererClients()
     var clients:TabClient
     nb,action,id,i:entier
     client:Client
     nom,prenom,tel,email,confirmation:chaine
     Debut
          ClientService.getTousLesClients(clients,nb)
          AdminView.afficherListeClients(clients,nb)
          Ecrire("1.Modifier  2.Supprimer  3.Retour")
          action←AuthView.saisieEntier("Choix : ")
          si(nb=0 OU action=3) alors
               retourner
          Fsi
          id←AuthView.saisieEntier("Id du client : ")
          client←null
          pour(i←1;i<=nb;i←i+1) faire
               si(clients[i].getId()=id) alors
                    client←clients[i]
               Fsi
          Fpour
          si(client=null) alors
               Ecrire("Client introuvable.")
               retourner
          Fsi
          si(action=1) alors
               nom←AuthView.saisieChaine("Nouveau nom : ")
               prenom←AuthView.saisieChaine("Nouveau prenom : ")
               tel←AuthView.saisieChaine("Nouveau tel : ")
               email←AuthView.saisieChaine("Nouvel email : ")
               ClientService.modifierClient(client,nom,prenom,tel,email)
               Ecrire("Client modifie avec succes.")
          sinon
               si(action=2) alors
                    confirmation←AuthView.saisieChaine("Confirmer la suppression ? (oui/non) : ")
                    si(minuscule(confirmation)="oui") alors
                         ClientService.supprimerClient(client)
                         Ecrire("Client supprime avec succes.")
                    Fsi
               Fsi
          Fsi
     Fin

     prive static procedure gererVendeurs()
     var vendeurs:TabVendeur
     nb,action,id,i:entier
     vendeur:Vendeur
     nom,prenom,tel,email,confirmation:chaine
     Debut
          VendeurService.getTousLesVendeurs(vendeurs,nb)
          AdminView.afficherListeVendeurs(vendeurs,nb)
          Ecrire("1.Ajouter  2.Modifier  3.Supprimer  4.Retour")
          action←AuthView.saisieEntier("Choix : ")
          si(action=1) alors
               nom←AuthView.saisieChaine("Nom : ")
               prenom←AuthView.saisieChaine("Prenom : ")
               tel←AuthView.saisieChaine("Tel : ")
               email←AuthView.saisieChaine("Email : ")
               VendeurService.ajouterVendeur(nom,prenom,tel,email)
               Ecrire("Vendeur ajoute avec succes.")
               retourner
          Fsi
          si(nb=0 OU action=4) alors
               retourner
          Fsi
          id←AuthView.saisieEntier("Id du vendeur : ")
          vendeur←null
          pour(i←1;i<=nb;i←i+1) faire
               si(vendeurs[i].getId()=id) alors
                    vendeur←vendeurs[i]
               Fsi
          Fpour
          si(vendeur=null) alors
               Ecrire("Vendeur introuvable.")
               retourner
          Fsi
          si(action=2) alors
               nom←AuthView.saisieChaine("Nouveau nom : ")
               prenom←AuthView.saisieChaine("Nouveau prenom : ")
               tel←AuthView.saisieChaine("Nouveau tel : ")
               email←AuthView.saisieChaine("Nouvel email : ")
               VendeurService.modifierVendeur(vendeur,nom,prenom,tel,email)
               Ecrire("Vendeur modifie avec succes.")
          sinon
               si(action=3) alors
                    confirmation←AuthView.saisieChaine("Confirmer la suppression ? (oui/non) : ")
                    si(minuscule(confirmation)="oui") alors
                         VendeurService.supprimerVendeur(vendeur)
                         Ecrire("Vendeur supprime avec succes.")
                    Fsi
               Fsi
          Fsi
     Fin

     prive static procedure gererLivreurs()
     var livreurs:TabLivreur
     nb,action,id,i:entier
     livreur:Livreur
     nom,prenom,tel,email,confirmation:chaine
     Debut
          LivreurService.getTousLesLivreurs(livreurs,nb)
          AdminView.afficherListeLivreurs(livreurs,nb)
          Ecrire("1.Ajouter  2.Modifier  3.Supprimer  4.Retour")
          action←AuthView.saisieEntier("Choix : ")
          si(action=1) alors
               nom←AuthView.saisieChaine("Nom : ")
               prenom←AuthView.saisieChaine("Prenom : ")
               tel←AuthView.saisieChaine("Tel : ")
               email←AuthView.saisieChaine("Email : ")
               LivreurService.ajouterLivreur(nom,prenom,tel,email)
               Ecrire("Livreur ajoute avec succes.")
               retourner
          Fsi
          si(nb=0 OU action=4) alors
               retourner
          Fsi
          id←AuthView.saisieEntier("Id du livreur : ")
          livreur←null
          pour(i←1;i<=nb;i←i+1) faire
               si(livreurs[i].getId()=id) alors
                    livreur←livreurs[i]
               Fsi
          Fpour
          si(livreur=null) alors
               Ecrire("Livreur introuvable.")
               retourner
          Fsi
          si(action=2) alors
               nom←AuthView.saisieChaine("Nouveau nom : ")
               prenom←AuthView.saisieChaine("Nouveau prenom : ")
               tel←AuthView.saisieChaine("Nouveau tel : ")
               email←AuthView.saisieChaine("Nouvel email : ")
               LivreurService.modifierLivreur(livreur,nom,prenom,tel,email)
               Ecrire("Livreur modifie avec succes.")
          sinon
               si(action=3) alors
                    confirmation←AuthView.saisieChaine("Confirmer la suppression ? (oui/non) : ")
                    si(minuscule(confirmation)="oui") alors
                         LivreurService.supprimerLivreur(livreur)
                         Ecrire("Livreur supprime avec succes.")
                    Fsi
               Fsi
          Fsi
     Fin

## ===== Gerer les produits =====
     prive static procedure gererProduits()
     var produits:TabProduit
     nb,action,id,i:entier
     produit:Produit
     nom,description,confirmation:chaine
     prix:reel
     qteStock:entier
     Debut
          ProduitService.getTousLesProduits(produits,nb)
          AdminView.afficherListeProduits(produits,nb)
          Ecrire("1.Modifier  2.Supprimer  3.Retour")
          action←AuthView.saisieEntier("Choix : ")
          si(nb=0 OU action=3) alors
               retourner
          Fsi
          id←AuthView.saisieEntier("Id du produit : ")
          produit←null
          pour(i←1;i<=nb;i←i+1) faire
               si(produits[i].getId()=id) alors
                    produit←produits[i]
               Fsi
          Fpour
          si(produit=null) alors
               Ecrire("Produit introuvable.")
               retourner
          Fsi
          si(action=1) alors
               nom←AuthView.saisieChaine("Nouveau nom : ")
               description←AuthView.saisieChaine("Nouvelle description : ")
               prix←AuthView.saisieDouble("Nouveau prix : ")
               qteStock←AuthView.saisieEntier("Nouvelle quantite en stock : ")
               ProduitService.modifierProduit(produit,nom,description,prix,qteStock)
               Ecrire("Produit modifie avec succes.")
          sinon
               si(action=2) alors
                    confirmation←AuthView.saisieChaine("Confirmer la suppression ? (oui/non) : ")
                    si(minuscule(confirmation)="oui") alors
                         ProduitService.supprimerProduit(produit)
                         Ecrire("Produit supprime avec succes.")
                    Fsi
               Fsi
          Fsi
     Fin

## ===== Consulter les commandes =====
     prive static procedure afficherCommandes()
     var commandes:TabCommande
     nb,i:entier
     Debut
          CommandeService.getToutesLesCommandes(commandes,nb)
          si(nb=0) alors
               Ecrire("Aucune commande enregistree pour le moment.")
          sinon
               pour(i←1;i<=nb;i←i+1) faire
                    Ecrire(commandes[i].toString())
               Fpour
          Fsi
     Fin

## ===== Fonctions d'affichage de listes (equivalent afficherListe(ArrayList<?>) generique) =====
     prive static procedure afficherListeClients(D liste:TabClient, nb:entier)
     var i:entier
     Debut
          si(nb=0) alors
               Ecrire("Aucun element trouve.")
          sinon
               pour(i←1;i<=nb;i←i+1) faire
                    Ecrire(liste[i].toString())
               Fpour
          Fsi
     Fin

     prive static procedure afficherListeVendeurs(D liste:TabVendeur, nb:entier)
     var i:entier
     Debut
          si(nb=0) alors
               Ecrire("Aucun element trouve.")
          sinon
               pour(i←1;i<=nb;i←i+1) faire
                    Ecrire(liste[i].toString())
               Fpour
          Fsi
     Fin

     prive static procedure afficherListeLivreurs(D liste:TabLivreur, nb:entier)
     var i:entier
     Debut
          si(nb=0) alors
               Ecrire("Aucun element trouve.")
          sinon
               pour(i←1;i<=nb;i←i+1) faire
                    Ecrire(liste[i].toString())
               Fpour
          Fsi
     Fin

     prive static procedure afficherListeProduits(D liste:TabProduit, nb:entier)
     var i:entier
     Debut
          si(nb=0) alors
               Ecrire("Aucun element trouve.")
          sinon
               pour(i←1;i<=nb;i←i+1) faire
                    Ecrire(liste[i].toString())
               Fpour
          Fsi
     Fin

FinClasse
