Type  VendeurView =Classe
Debut

prive VendeurView()
Debut
Fin

public static procedure afficherMenuVendeur(D vendeur:Vendeur)
var choix:entier
Debut
     repeter
          Ecrire("")
          Ecrire("===== Menu Vendeur (",vendeur.getPrenom(),") =====")
          Ecrire("1. Ajouter un produit")
          Ecrire("2. Modifier un produit")
          Ecrire("3. Supprimer un produit")
          Ecrire("4. Consulter mes produits")
          Ecrire("5. Consulter les commandes recues")
          Ecrire("6. Se deconnecter")
          choix←AuthView.saisieEntier("Saisir votre choix : ")

          cas(choix)
               1:
                    VendeurView.ajouterProduit(vendeur)
               2:
                    VendeurView.modifierProduit(vendeur)
               3:
                    VendeurView.supprimerProduit(vendeur)
               4:
                    VendeurView.afficherMesProduits(vendeur)
               5:
                    VendeurView.afficherCommandes(vendeur)
               6:
                    Ecrire("Deconnexion...")
               Autre:
                    Ecrire("Choix invalide. Veuillez reessayer.")
          Fcas
     jusqu'a (choix=6)
Fin

prive static procedure afficherProduits(D produits:TabProduit, nb:entier)
var i:entier
Debut
     si(nb=0) alors
          Ecrire("Aucun produit trouve.")
     sinon
          pour(i←1;i<=nb;i←i+1) faire
               Ecrire(produits[i].toString())
          Fpour
     Fsi
Fin

prive static procedure afficherMesProduits(D vendeur:Vendeur)
var produits:TabProduit
    nb:entier
Debut
     vendeur.getProduits(produits,nb)
     VendeurView.afficherProduits(produits,nb)
Fin

prive static procedure ajouterProduit(D vendeur:Vendeur)
var nom,description:chaine
    prix:reel
    qteStock:entier
Debut
     nom←AuthView.saisieChaine("Nom du produit : ")
     description←AuthView.saisieChaine("Description : ")
     prix←AuthView.saisieDouble("Prix : ")
     qteStock←AuthView.saisieEntier("Quantite en stock : ")
     ProduitService.ajouterProduit(vendeur,nom,description,prix,qteStock)
     Ecrire("Produit ajoute avec succes.")
Fin

prive static fonction selectionnerProduit(D vendeur:Vendeur):Produit
var produits:TabProduit
    nb,id,i:entier
Debut
     vendeur.getProduits(produits,nb)
     VendeurView.afficherProduits(produits,nb)
     si(nb=0) alors
          retourner null
     Fsi
     id←AuthView.saisieEntier("Saisir l'id du produit : ")
     pour(i←1;i<=nb;i←i+1) faire
          si(produits[i].getId()=id) alors
               retourner produits[i]
          Fsi
     Fpour
     Ecrire("Produit introuvable.")
     retourner null
Fin

prive static procedure modifierProduit(D vendeur:Vendeur)
var produit:Produit
    nom,description:chaine
    prix:reel
    qteStock:entier
Debut
     produit←VendeurView.selectionnerProduit(vendeur)
     si(produit=null) alors
          retourner
     Fsi
     nom←AuthView.saisieChaine("Nouveau nom : ")
     description←AuthView.saisieChaine("Nouvelle description : ")
     prix←AuthView.saisieDouble("Nouveau prix : ")
     qteStock←AuthView.saisieEntier("Nouvelle quantite en stock : ")
     ProduitService.modifierProduit(produit,nom,description,prix,qteStock)
     Ecrire("Produit modifie avec succes.")
Fin

prive static procedure supprimerProduit(D vendeur:Vendeur)
var produit:Produit
    confirmation:chaine
Debut
     produit←VendeurView.selectionnerProduit(vendeur)
     si(produit=null) alors
          retourner
     Fsi
     confirmation←AuthView.saisieChaine("Confirmer la suppression ? (oui/non) : ")
     si(minuscule(confirmation)="oui") alors
          ProduitService.supprimerProduit(produit)
          Ecrire("Produit supprime avec succes.")
     sinon
          Ecrire("Suppression annulee.")
     Fsi
Fin

prive static procedure afficherCommandes(D vendeur:Vendeur)
var commandes:TabCommande
    nb,i:entier
Debut
     CommandeService.getCommandesParVendeur(vendeur,commandes,nb)
     si(nb=0) alors
          Ecrire("Aucune commande recue pour le moment.")
     sinon
          pour(i←1;i<=nb;i←i+1) faire
               Ecrire(commandes[i].toString())
          Fpour
     Fsi
Fin

FinClasse
