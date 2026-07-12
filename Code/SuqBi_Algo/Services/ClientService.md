Type  ClientService =Classe
Debut
   prive static tClients:TabClient
   prive static nbreClients:entier
   prive static prochainId:entier

     prive ClientService()
     Debut
          ClientService.nbreClients←0
          ClientService.prochainId←1
     Fin

     prive static fonction emailExiste(D email:chaine):booleen
     var i:entier
     Debut
          pour(i←1;i<=ClientService.nbreClients;i←i+1) faire
               si(minuscule(ClientService.tClients[i].getEmail())=minuscule(email)) alors
                    retourner Vrai
               Fsi
          Fpour
          retourner Faux
     Fin

## Cas d'utilisation : Creer un compte (Client)
     public static fonction creerCompte(D nom:chaine, prenom:chaine, tel:chaine, email:chaine, mdp:chaine):Client
     var client:Client
     Debut
          si(ClientService.emailExiste(email)) alors
               Ecrire("Ce compte existe deja.")
               retourner null
          Fsi
          client←new Client(ClientService.prochainId,nom,prenom,tel,email,mdp)
          ClientService.prochainId←ClientService.prochainId+1
          ClientService.nbreClients←ClientService.nbreClients+1
          ClientService.tClients[ClientService.nbreClients]←client
          retourner client
     Fin

## Cas d'utilisation : Se connecter (Client)
     public static fonction seConnecter(D email:chaine, mdp:chaine):Client
     var i:entier
     Debut
          pour(i←1;i<=ClientService.nbreClients;i←i+1) faire
               si(minuscule(ClientService.tClients[i].getEmail())=minuscule(email)) alors
                    si(ClientService.tClients[i].getMdp()=mdp) alors
                         retourner ClientService.tClients[i]
                    Fsi
                    Ecrire("Mot de passe incorrect.")
                    retourner null
               Fsi
          Fpour
          Ecrire("Compte introuvable.")
          retourner null
     Fin

     public static procedure getTousLesClients(R clients:TabClient, R nb:entier)
     var i:entier
     Debut
          pour(i←1;i<=ClientService.nbreClients;i←i+1) faire
               clients[i]←ClientService.tClients[i]
          Fpour
          nb←ClientService.nbreClients
     Fin

## Cas d'utilisation Admin : Modifier un client
     public static procedure modifierClient(D client:Client, nom:chaine, prenom:chaine, tel:chaine, email:chaine)
     Debut
          client.setNom(nom)
          client.setPrenom(prenom)
          client.setTel(tel)
          client.setEmail(email)
     Fin

## Cas d'utilisation Admin : Supprimer un client
     public static procedure supprimerClient(D client:Client)
     var i,pos:entier
     Debut
          pos←0
          pour(i←1;i<=ClientService.nbreClients;i←i+1) faire
               si(ClientService.tClients[i].equals(client)) alors
                    pos←i
               Fsi
          Fpour
          si(pos>0) alors
               pour(i←pos;i<ClientService.nbreClients;i←i+1) faire
                    ClientService.tClients[i]←ClientService.tClients[i+1]
               Fpour
               ClientService.nbreClients←ClientService.nbreClients-1
          Fsi
     Fin

FinClasse
