Type  AuthView =Classe
Debut

prive AuthView()
Debut
Fin

public static fonction saisieChaine(D message:chaine):chaine
var input:chaine
Debut
     repeter
          Ecrire(message)
          lire(input)
     jusqu'a (input<>"")
     retourner input
Fin

public static fonction saisieEntier(D message:chaine):entier
var input:entier
    valide:booleen
Debut
     valide←Faux
     tantque(NON valide) faire
          Ecrire(message)
          essai
               lire(input)
               valide←Vrai
          exception
               Ecrire("Veuillez saisir un nombre entier valide.")
          finessai
     Ftantque
     retourner input
Fin

public static fonction saisieDouble(D message:chaine):reel
var input:reel
    valide:booleen
Debut
     valide←Faux
     tantque(NON valide) faire
          Ecrire(message)
          essai
               lire(input)
               valide←Vrai
          exception
               Ecrire("Veuillez saisir un nombre valide.")
          finessai
     Ftantque
     retourner input
Fin

FinClasse
