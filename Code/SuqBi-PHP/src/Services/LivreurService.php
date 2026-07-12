<?php
namespace App\Services;

use App\Entity\Livreur;
use App\Repository\LivreurRepository;

final class LivreurService
{
    private function __construct()
    {
    }

    // Cas d'utilisation : Creer un compte (Livreur)
    public static function creerCompte(string $nom, string $prenom, string $tel, string $email): ?Livreur
    {
        $repo = LivreurRepository::getInstance();
        if ($repo->selectByEmail($email) !== null) {
            return null; // email deja utilise
        }
        $livreur = (new Livreur())
            ->setNom($nom)
            ->setPrenom($prenom)
            ->setTel($tel)
            ->setEmail($email)
            ->setDisponibilite(false); // indisponible par defaut

        $id = $repo->insert($livreur);
        if ($id === 0) {
            return null;
        }
        $livreur->setId((int) $id);
        return $livreur;
    }

    // Cas d'utilisation : Se connecter (Livreur)
    // NB : connexion par email seul (pas de mdp), simplification actee
    public static function seConnecter(string $email): ?Livreur
    {
        return LivreurRepository::getInstance()->selectByEmail($email);
    }

    // Cas d'utilisation : Renseigner sa disponibilite
    public static function renseignerDisponibilite(Livreur $livreur, bool $dispo): void
    {
        $livreur->setDisponibilite($dispo);
        LivreurRepository::getInstance()->update($livreur);
    }
}
