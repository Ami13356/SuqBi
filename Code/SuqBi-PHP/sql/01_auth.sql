-- Base de donnees SuqBi -- Phase 1 : Authentification
-- A executer dans phpMyAdmin (MAMP) -- creera la base "suqbi" si absente

CREATE DATABASE IF NOT EXISTS suqbi CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE suqbi;

CREATE TABLE IF NOT EXISTS client (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    tel VARCHAR(50) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    mdp VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS vendeur (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    tel VARCHAR(50) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
    -- NB : pas de mdp, connexion par email seul (simplification actee)
);

CREATE TABLE IF NOT EXISTS livreur (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    tel VARCHAR(50) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    disponibilite BOOLEAN NOT NULL DEFAULT FALSE
    -- NB : pas de mdp, connexion par email seul (simplification actee)
);

-- NB : pas de table "admin". Le compte Admin est unique et code en dur dans
-- AdminService.php (meme logique que dans la version Java), comme convenu.
