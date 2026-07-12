<?php
namespace App\Repository;

use App\Config\AbstractRepository;
use App\Entity\Vendeur;

class VendeurRepository extends AbstractRepository
{
    private static ?VendeurRepository $instance = null;

    public static function getInstance(): VendeurRepository
    {
        if (self::$instance == null) {
            self::$instance = new VendeurRepository();
        }
        return self::$instance;
    }

    private function __construct()
    {
        parent::__construct();
        $this->tableName = "vendeur";
        $this->className = "App\\Entity\\Vendeur";
    }

    public function insert(Vendeur $vendeur): int
    {
        try {
            $pdo = parent::getConnection();
            $cursor = $pdo->prepare("insert into {$this->tableName} (nom,prenom,tel,email) values (:nom,:prenom,:tel,:email)");
            $cursor->execute([
                ':nom' => $vendeur->getNom(),
                ':prenom' => $vendeur->getPrenom(),
                ':tel' => $vendeur->getTel(),
                ':email' => $vendeur->getEmail(),
            ]);
            $lastInsertId = $pdo->lastInsertId();
            parent::closeConnection();
            return $lastInsertId;
        } catch (\Throwable $th) {
            return 0;
        }
    }

    public function selectById(int $id): ?Vendeur
    {
        $pdo = parent::getConnection();
        $cursor = $pdo->prepare("select * from {$this->tableName} where id=:id");
        $cursor->execute([':id' => $id]);
        $vendeur = $cursor->fetchObject($this->className);
        parent::closeConnection();
        return $vendeur == false ? null : $vendeur;
    }

    public function selectByEmail(string $email): ?Vendeur
    {
        $pdo = parent::getConnection();
        $cursor = $pdo->prepare("select * from {$this->tableName} where email=:email");
        $cursor->execute([':email' => $email]);
        $vendeur = $cursor->fetchObject($this->className);
        parent::closeConnection();
        return $vendeur == false ? null : $vendeur;
    }
}
