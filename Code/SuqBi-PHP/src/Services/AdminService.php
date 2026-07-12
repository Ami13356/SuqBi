<?php
namespace App\Services;

use App\Entity\Admin;

final class AdminService
{
    private function __construct()
    {
    }

    // Compte Admin unique, code en dur (pas de table "admin", pas de cas
    // d'utilisation "Creer un compte" pour l'Admin) -- meme logique que la
    // version Java.
    private static function getAdmin(): Admin
    {
        return (new Admin())
            ->setId(1)
            ->setNom("Admin")
            ->setPrenom("SuqBi")
            ->setTel("770000000")
            ->setEmail("admin@suqbi.com")
            ->setMdp("admin");
    }

    // Cas d'utilisation : Se connecter (Admin)
    public static function seConnecter(string $email, string $mdp): ?Admin
    {
        $admin = self::getAdmin();
        if (strcasecmp($admin->getEmail(), $email) === 0 && $admin->getMdp() === $mdp) {
            return $admin;
        }
        return null;
    }
}
