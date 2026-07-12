## Classe Admin
Type  Admin =Classe
Debut
   prive id:entier
   prive nom:chaine
   prive prenom:chaine
   prive tel:chaine
   prive email:chaine
   prive mdp:chaine
## Constructeurs
   public  Admin()
   Debut
   Fin

   public  Admin(D id:entier, nom:chaine, prenom:chaine, tel:chaine, email:chaine, mdp:chaine)
   Debut
      this.id←id
      this.nom←nom
      this.prenom←prenom
      this.tel←tel
      this.email←email
      this.mdp←mdp
   Fin

## Getters & Setters  id
   public fonction getId():entier
   Debut
      retourner this.id
   Fin
   public procedure setId(D id:entier)
   Debut
      this.id←id
   Fin

## Getters & Setters  nom
   public fonction getNom():chaine
   Debut
      retourner this.nom
   Fin
   public procedure setNom(D nom:chaine)
   Debut
      this.nom←nom
   Fin

## Getters ,Setters  prenom
   public fonction getPrenom():chaine
   Debut
      retourner this.prenom
   Fin
   public procedure setPrenom(D prenom:chaine)
   Debut
      this.prenom←prenom
   Fin

## Getters & Setters  tel
   public fonction getTel():chaine
   Debut
      retourner this.tel
   Fin
   public procedure setTel(D tel:chaine)
   Debut
      this.tel←tel
   Fin

## Getters & Setters  email
   public fonction getEmail():chaine
   Debut
      retourner this.email
   Fin
   public procedure setEmail(D email:chaine)
   Debut
      this.email←email
   Fin

## Getters & Setters  mdp
   public fonction getMdp():chaine
   Debut
      retourner this.mdp
   Fin
   public procedure setMdp(D mdp:chaine)
   Debut
      this.mdp←mdp
   Fin
## toString()
   public fonction toString():chaine
   Debut
      retourner "Admin [id="+this.id+", nom="+this.nom+", prenom="+this.prenom+", tel="+this.tel+", email="+this.email+"]"
   Fin

FinClasse
