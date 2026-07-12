Type  ClientView =Classe
Debut

prive ClientView()
Debut
Fin

public static procedure afficherMenuClient(D client:Client)
var choix:entier
    motCle:chaine
Debut
     repeter
          Ecrire("")
          Ecrire("===== Menu Client (",client.getPrenom(),") =====")
          Ecrire("1. Consulter les produits disponibles")
          Ecrire("2. Rechercher un produit")
          Ecrire("3. Ajouter un produit au panier")
          Ecrire("4. Consulter le panier")
          Ecrire("5. Valider le panier et passer commande")
          Ecrire("6. Consulter mes commandes")
          Ecrire("7. Se deconnecter")
          choix←AuthView.saisieEntier("Saisir votre choix : ")

          cas(choix)
               1:
                    ClientView.afficherProduitsDisponiblesDuService()
               2:
                    motCle←AuthView.saisieChaine("Mot-cle a rechercher : ")
                    ClientView.afficherProduitsRecherches(motCle)
               3:
                    ClientView.ajouterAuPanier(client)
               4:
                    ClientView.afficherPanier(client)
               5:
                    ClientView.validerEtPasserCommande(client)
               6:
                    ClientView.afficherCommandes(client)
               7:
                    Ecrire("Deconnexion...")
               Autre:
                    Ecrire("Choix invalide. Veuillez reessayer.")
          Fcas
     jusqu'a (choix=7)
Fin

prive static procedure afficherProduits(D produits:TabProduit, nb:entier)
var i:entier
Debut
     si(nb=0) alors
          Ecrire("Aucun produit disponible pour le moment.")
     sinon
          Ecrire("--- Liste des produits ---")
          pour(i←1;i<=nb;i←i+1) faire
               Ecrire(produits[i].toString())
          Fpour
     Fsi
Fin

prive static procedure afficherProduitsDisponiblesDuService()
var produits:TabProduit
    nb:entier
Debut
     ProduitService.getProduitsDisponibles(produits,nb)
     ClientView.afficherProduits(produits,nb)
Fin

prive static procedure afficherProduitsRecherches(D motCle:chaine)
var produits:TabProduit
    nb:entier
Debut
     ProduitService.rechercherProduit(motCle,produits,nb)
     ClientView.afficherProduits(produits,nb)
Fin

prive static procedure ajouterAuPanier(D client:Client)
var produits:TabProduit
    nb,idProduit,quantite,i:entier
    produit:Produit
    succes:booleen
Debut
     ProduitService.getProduitsDisponibles(produits,nb)
     ClientView.afficherProduits(produits,nb)
     si(nb=0) alors
          retourner
     Fsi
     idProduit←AuthView.saisieEntier("Saisir l'id du produit : ")
     produit←null
     pour(i←1;i<=nb;i←i+1) faire
          si(produits[i].getId()=idProduit) alors
               produit←produits[i]
          Fsi
     Fpour
     si(produit=null) alors
          Ecrire("Produit introuvable.")
          retourner
     Fsi
     quantite←AuthView.saisieEntier("Saisir la quantite : ")
     succes←client.ajouterProduitAuPanier(produit,quantite)
     si(succes) alors
          Ecrire("Produit ajoute au panier avec succes.")
     Fsi
Fin

prive static procedure afficherPanier(D client:Client)
var lignes:TabLignePanier
    nb,i:entier
Debut
     si(client.getPanier().isVide()) alors
          Ecrire("Votre panier est vide.")
          retourner
     Fsi
     Ecrire("--- Contenu du panier ---")
     client.getPanier().getLignes(lignes,nb)
     pour(i←1;i<=nb;i←i+1) faire
          Ecrire(lignes[i].toString())
     Fpour
     Ecrire("Montant total : ",client.getPanier().getMontantTotal())
Fin

prive static procedure validerEtPasserCommande(D client:Client)
var commande:Commande
Debut
     si(client.getPanier().isVide()) alors
          Ecrire("Le panier est vide.")
          retourner
     Fsi
     ClientView.afficherPanier(client)
     client.getPanier().validerPanier()
     commande←CommandeService.passerCommande(client)
     si(commande<>null) alors
          Ecrire("Commande passee avec succes ! ",commande.toString())
     Fsi
Fin

prive static procedure afficherCommandes(D client:Client)
var commandes:TabCommande
    nb,i:entier
Debut
     client.getCommandes(commandes,nb)
     si(nb=0) alors
          Ecrire("Vous n'avez encore passe aucune commande.")
     sinon
          Ecrire("--- Vos commandes ---")
          pour(i←1;i<=nb;i←i+1) faire
               Ecrire(commandes[i].toString())
          Fpour
     Fsi
Fin

FinClasse
