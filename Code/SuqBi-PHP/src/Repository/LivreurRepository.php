<?php
namespace App\Repository;

use App\Config\AbstractRepository;
use App\Entity\Livreur;

class LivreurRepository extends AbstractRepository
{
    private static ?LivreurRepository $instance = null;

    public static function getInstance(): LivreurRepository
    {
        if (self::$instance == null) {
            self::$instance = new LivreurRepository();
        }
        return self::$instance;
    }

    private function __construct()
    {
        parent::__construct();
        $this->tableName = "livreur";
        $this->className = "App\\Entity\\Livreur";
    }

    public function insert(Livreur $livreur): int
    {
        try {
            $pdo = parent::getConnection();
            $cursor = $pdo->prepare("insert into {$this->tableName} (nom,prenom,tel,email,disponibilite) values (:nom,:prenom,:tel,:email,:disponibilite)");
            $cursor->execute([
                ':nom' => $livreur->getNom(),
                ':prenom' => $livreur->getPrenom(),
                ':tel' => $livreur->getTel(),
                ':email' => $livreur->getEmail(),
                ':disponibilite' => (int) $livreur->isDisponibilite(),
            ]);
            $lastInsertId = $pdo->lastInsertId();
            parent::closeConnection();
            return $lastInsertId;
        } catch (\Throwable $th) {
            return 0;
        }
    }

    public function update(Livreur $livreur): bool
    {
        $pdo = parent::getConnection();
        $cursor = $pdo->prepare("update {$this->tableName} set disponibilite=:disponibilite where id=:id");
        $result = $cursor->execute([
            ':disponibilite' => (int) $livreur->isDisponibilite(),
            ':id' => $livreur->getId(),
        ]);
        parent::closeConnection();
        return $result;
    }

    public function selectById(int $id): ?Livreur
    {
        $pdo = parent::getConnection();
        $cursor = $pdo->prepare("select * from {$this->tableName} where id=:id");
        $cursor->execute([':id' => $id]);
        $livreur = $cursor->fetchObject($this->className);
        parent::closeConnection();
        return $livreur == false ? null : $livreur;
    }

    public function selectByEmail(string $email): ?Livreur
    {
        $pdo = parent::getConnection();
        $cursor = $pdo->prepare("select * from {$this->tableName} where email=:email");
        $cursor->execute([':email' => $email]);
        $livreur = $cursor->fetchObject($this->className);
        parent::closeConnection();
        return $livreur == false ? null : $livreur;
    }
}
