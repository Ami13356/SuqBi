Type  AdminService =Classe
Debut
   ## ompte Admin pre-defini en dur, comme le compte "chef" dans l'exemple du
   ## professeur (pas de cas d'utilisation "Creer un compte" pour l'Admin)
     prive static admin:Admin

     prive AdminService()
     Debut
          AdminService.admin←new Admin(1,"Admin","SuqBi","770000000","admin@suqbi.com","admin")
     Fin

## Cas d'utilisation : Se connecter (Admin)
     public static fonction seConnecter(D email:chaine, mdp:chaine):Admin
     Debut
          si(minuscule(AdminService.admin.getEmail())=minuscule(email) ET AdminService.admin.getMdp()=mdp) alors
               retourner AdminService.admin
          Fsi
          Ecrire("Identifiants incorrects.")
          retourner null
     Fin

FinClasse
