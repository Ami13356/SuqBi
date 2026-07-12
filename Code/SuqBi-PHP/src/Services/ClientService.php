<?php
namespace App\Services;

use App\Entity\Client;
use App\Repository\ClientRepository;

final class ClientService
{
    private function __construct()
    {
    }

    // Cas d'utilisation : Creer un compte (Client)
    public static function creerCompte(string $nom, string $prenom, string $tel, string $email, string $mdp): ?Client
    {
        $repo = ClientRepository::getInstance();
        if ($repo->selectByEmail($email) !== null) {
            return null; // email deja utilise
        }
        $client = (new Client())
            ->setNom($nom)
            ->setPrenom($prenom)
            ->setTel($tel)
            ->setEmail($email)
            ->setMdp(password_hash($mdp, PASSWORD_DEFAULT));

        $id = $repo->insert($client);
        if ($id === 0) {
            return null;
        }
        $client->setId((int) $id);
        return $client;
    }

    // Cas d'utilisation : Se connecter (Client)
    public static function seConnecter(string $email, string $mdp): ?Client
    {
        $repo = ClientRepository::getInstance();
        $client = $repo->selectByEmail($email);
        if ($client === null) {
            return null; // compte introuvable
        }
        if (!password_verify($mdp, $client->getMdp())) {
            return null; // mot de passe incorrect
        }
        return $client;
    }
}
