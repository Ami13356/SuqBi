<?php
namespace App\Repository;

use App\Config\AbstractRepository;
use App\Entity\Client;

class ClientRepository extends AbstractRepository
{
    private static ?ClientRepository $instance = null;

    public static function getInstance(): ClientRepository
    {
        if (self::$instance == null) {
            self::$instance = new ClientRepository();
        }
        return self::$instance;
    }

    private function __construct()
    {
        parent::__construct();
        $this->tableName = "client";
        $this->className = "App\\Entity\\Client";
    }

    public function insert(Client $client): int
    {
        try {
            $pdo = parent::getConnection();
            $cursor = $pdo->prepare("insert into {$this->tableName} (nom,prenom,tel,email,mdp) values (:nom,:prenom,:tel,:email,:mdp)");
            $cursor->execute([
                ':nom' => $client->getNom(),
                ':prenom' => $client->getPrenom(),
                ':tel' => $client->getTel(),
                ':email' => $client->getEmail(),
                ':mdp' => $client->getMdp(),
            ]);
            $lastInsertId = $pdo->lastInsertId();
            parent::closeConnection();
            return $lastInsertId;
        } catch (\Throwable $th) {
            return 0;
        }
    }

    public function selectById(int $id): ?Client
    {
        $pdo = parent::getConnection();
        $cursor = $pdo->prepare("select * from {$this->tableName} where id=:id");
        $cursor->execute([':id' => $id]);
        $client = $cursor->fetchObject($this->className);
        parent::closeConnection();
        return $client == false ? null : $client;
    }

    public function selectByEmail(string $email): ?Client
    {
        $pdo = parent::getConnection();
        $cursor = $pdo->prepare("select * from {$this->tableName} where email=:email");
        $cursor->execute([':email' => $email]);
        $client = $cursor->fetchObject($this->className);
        parent::closeConnection();
        return $client == false ? null : $client;
    }
}
