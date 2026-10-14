# SuqBi

**Vos courses de marché, livrées simplement.**

SuqBi est une application qui met en relation trois acteurs : les **clients**, les **vendeurs** des marchés et les **livreurs**. Projet personnel conçu en juin 2026 à partir d'une idée proposée dans le cadre du cours de développement (ISM Dakar), et toujours en cours de développement.

## Pourquoi SuqBi ?

Quand on ne peut pas se rendre au marché (Sandaga, à Dakar, par exemple), il est difficile de faire ses courses. À l'inverse, certains vendeurs sont peu visibles parce que leur emplacement est éloigné ou en retrait. Des livreurs, eux, attendent souvent dans la rue sans mission. SuqBi réunit ces trois besoins sur une même plateforme.

## Ce que contient le dépôt

| Dossier | Contenu |
|---|---|
| `Code/SuqBi_Algo` | Conception en algorithmique (entités, services, vues, menus) |
| `Code/JAVA_POO/SuqBi_Java` | Version **console en Java** avec la logique métier |
| `Code/SuqBi-PHP` | Version **web en PHP/MySQL**, en architecture MVC |
| `UML` | Diagrammes UML du projet |
| `maquette_SuqBi` | Logo et éléments de maquette |

## Fonctionnalités

**Version Java (console)**
- Quatre profils : client, vendeur, livreur, administrateur
- Gestion des produits, du panier, des commandes et des livraisons

**Version web PHP (phase 1 : authentification)**
- Inscription et connexion pour chaque profil, avec un tableau de bord par rôle
- Mot de passe haché pour les clients (`password_hash`)
- Connexion des vendeurs et livreurs par e-mail seul, une simplification volontaire pour cette première phase

## Technologies

| Élément | Choix |
|---|---|
| Langages | Java, PHP, SQL |
| Base de données | MySQL |
| Architecture | MVC (contrôleurs, services, dépôts, entités, vues) |
| Conception | UML (StarUML), algorithmique |
| Prototype | Figma (en cours) |

## Lancer la version PHP

Prérequis : un serveur local avec PHP et MySQL (par exemple MAMP).

1. Copier le dossier `Code/SuqBi-PHP` dans le dossier web du serveur (`htdocs`).
2. Importer le fichier `sql/01_auth.sql` avec phpMyAdmin : la base `suqbi` est créée automatiquement.
3. Vérifier les paramètres de connexion à la base de données dans `src/Config/AbstractRepository.php` (hôte, port, utilisateur) et les adapter à votre configuration.
4. Ouvrir le projet dans le navigateur : la page de connexion s'affiche.

## Lancer la version Java

Ouvrir le dossier `Code/JAVA_POO/SuqBi_Java` dans VS Code ou IntelliJ, puis exécuter la classe `App.java`.

## Suite du projet

- Prototype de l'interface sur Figma
- Évolution de la version web avec de nouvelles fonctionnalités et le framework Symfony

## Auteure

**Aminata CISSE**, étudiante en L3 Génie Logiciel et Réseaux, ISM Dakar.
GitHub : [Ami13356](https://github.com/Ami13356)
