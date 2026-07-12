<?php
namespace App\Services;

use App\Entity\Vendeur;
use App\Repository\VendeurRepository;

final class VendeurService
{
    private function __construct()
    {
    }

    // Cas d'utilisation : Creer un compte (Vendeur)
    public static function creerCompte(string $nom, string $prenom, string $tel, string $email): ?Vendeur
    {
        $repo = VendeurRepository::getInstance();
        if ($repo->selectByEmail($email) !== null) {
            return null; // email deja utilise
        }
        $vendeur = (new Vendeur())
            ->setNom($nom)
            ->setPrenom($prenom)
            ->setTel($tel)
            ->setEmail($email);

        $id = $repo->insert($vendeur);
        if ($id === 0) {
            return null;
        }
        $vendeur->setId((int) $id);
        return $vendeur;
    }

    // Cas d'utilisation : Se connecter (Vendeur)
    // NB : connexion par email seul (pas de mdp), simplification actee
    public static function seConnecter(string $email): ?Vendeur
    {
        return VendeurRepository::getInstance()->selectByEmail($email);
    }
}
