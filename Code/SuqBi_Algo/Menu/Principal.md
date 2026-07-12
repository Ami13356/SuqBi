## menu principal
Type  Principal =Classe
Debut

     public procedure execute()
     var profil:entier
     Debut
          repeter
               Ecrire("")
               Ecrire("===== Bienvenue sur SuqBi =====")
               Ecrire("Vos courses de marche, livrees simplement.")
               Ecrire("1. Client")
               Ecrire("2. Vendeur")
               Ecrire("3. Livreur")
               Ecrire("4. Admin")
               Ecrire("5. Quitter")
               profil←AuthView.saisieEntier("Choisir votre profil : ")

               cas(profil)
                    1:
                         this.lancerClient()
                    2:
                         this.lancerVendeur()
                    3:
                         this.lancerLivreur()
                    4:
                         this.lancerAdmin()
                    5:
                         Ecrire("Au revoir !")
                    Autre:
                         Ecrire("Choix invalide. Veuillez reessayer.")
               Fcas
          jusqu'a (profil=5)
     Fin

     prive procedure lancerClient()
     var action:entier
     nom,prenom,tel,email,mdp:chaine
     client:Client
     Debut
          action←AuthView.saisieEntier("1.Creer un compte  2.Se connecter  : ")
          client←null
          repeter
               si(action=1) alors
                    nom←AuthView.saisieChaine("Nom : ")
                    prenom←AuthView.saisieChaine("Prenom : ")
                    tel←AuthView.saisieChaine("Tel : ")
                    email←AuthView.saisieChaine("Email : ")
                    mdp←AuthView.saisieChaine("Mot de passe : ")
                    client←ClientService.creerCompte(nom,prenom,tel,email,mdp)
               sinon
                    email←AuthView.saisieChaine("Email : ")
                    mdp←AuthView.saisieChaine("Mot de passe : ")
                    client←ClientService.seConnecter(email,mdp)
               Fsi
          jusqu'a (client<>null)
          Ecrire("Compte pret. Bienvenue ",client.getPrenom()," !")
          ClientView.afficherMenuClient(client)
     Fin

prive procedure lancerVendeur()
var action:entier
    nom,prenom,tel,email:chaine
    vendeur:Vendeur
     Debut
          action←AuthView.saisieEntier("1.Creer un compte  2.Se connecter  : ")
          vendeur←null
          repeter
               si(action=1) alors
                    nom←AuthView.saisieChaine("Nom : ")
                    prenom←AuthView.saisieChaine("Prenom : ")
                    tel←AuthView.saisieChaine("Tel : ")
                    email←AuthView.saisieChaine("Email : ")
                    vendeur←VendeurService.creerCompte(nom,prenom,tel,email)
               sinon
                    email←AuthView.saisieChaine("Email : ")
                    vendeur←VendeurService.seConnecter(email)
               Fsi
          jusqu'a (vendeur<>null)
          Ecrire("Compte pret. Bienvenue ",vendeur.getPrenom()," !")
          VendeurView.afficherMenuVendeur(vendeur)
     Fin

     prive procedure lancerLivreur()
     var action:entier
     nom,prenom,tel,email:chaine
     livreur:Livreur
     Debut
          action←AuthView.saisieEntier("1.Creer un compte  2.Se connecter  : ")
          livreur←null
          repeter
               si(action=1) alors
                    nom←AuthView.saisieChaine("Nom : ")
                    prenom←AuthView.saisieChaine("Prenom : ")
                    tel←AuthView.saisieChaine("Tel : ")
                    email←AuthView.saisieChaine("Email : ")
                    livreur←LivreurService.creerCompte(nom,prenom,tel,email)
               sinon
                    email←AuthView.saisieChaine("Email : ")
                    livreur←LivreurService.seConnecter(email)
               Fsi
          jusqu'a (livreur<>null)
          Ecrire("Compte pret. Bienvenue ",livreur.getPrenom()," !")
          LivreurView.afficherMenuLivreur(livreur)
     Fin

     prive procedure lancerAdmin()
     var email,mdp:chaine
     admin:Admin
     Debut
          admin←null
          repeter
               email←AuthView.saisieChaine("Email : ")
               mdp←AuthView.saisieChaine("Mot de passe : ")
               admin←AdminService.seConnecter(email,mdp)
          jusqu'a (admin<>null)
          Ecrire("Connexion reussie. Bienvenue Admin !")
          AdminView.afficherMenuAdmin()
     Fin

FinClasse

### ===== Programme principal =====
     principal:Principal
     principal←new Principal()
     principal.execute()
