Type  CommandeService =Classe
Debut
   prive static tCommandes:TabCommande
   prive static nbreCommandes:entier
   prive static prochainId:entier

prive CommandeService()
Debut
     CommandeService.nbreCommandes←0
     CommandeService.prochainId←1
Fin

## Cas d'utilisation Client : Passer une commande
## Orchestre Client.passerCommande() (logique metier) puis l'enregistrement
     public static fonction passerCommande(D client:Client):Commande
     var commande:Commande
     Debut
          commande←client.passerCommande()
          si(commande=null) alors
               retourner null
          Fsi
          commande.setId(CommandeService.prochainId)
          CommandeService.prochainId←CommandeService.prochainId+1
          CommandeService.nbreCommandes←CommandeService.nbreCommandes+1
          CommandeService.tCommandes[CommandeService.nbreCommandes]←commande
          retourner commande
     Fin

## Cas d'utilisation Vendeur : Consulter les commandes recues
     public static procedure getCommandesParVendeur(D vendeur:Vendeur, R resultat:TabCommande, R nb:entier)
     var i:entier
     Debut
          nb←0
          pour(i←1;i<=CommandeService.nbreCommandes;i←i+1) faire
               si(CommandeService.tCommandes[i].getVendeur().equals(vendeur)) alors
                    nb←nb+1
                    resultat[nb]←CommandeService.tCommandes[i]
               Fsi
          Fpour
     Fin

## Cas d'utilisation Admin : Consulter les commandes (toutes)
     public static procedure getToutesLesCommandes(R commandes:TabCommande, R nb:entier)
     var i:entier
     Debut
          pour(i←1;i<=CommandeService.nbreCommandes;i←i+1) faire
               commandes[i]←CommandeService.tCommandes[i]
          Fpour
          nb←CommandeService.nbreCommandes
     Fin

FinClasse
