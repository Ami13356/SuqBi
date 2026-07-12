<?php
namespace App\Config;

abstract class AbstractRepository
{
    protected string $tableName;
    protected string $className;
    private \PDO|null $pdo = null;

    protected function __construct()
    {
    }

    public function getConnection(): \PDO
    {
        try {
            $this->pdo = new \PDO("mysql:host=localhost:8889;dbname=suqbi", "root", "root", [
                \PDO::ATTR_ERRMODE => \PDO::ERRMODE_EXCEPTION,
            ]);
        } catch (\Exception $e) {
            echo "Erreur de connexion : ";
            echo $e->getMessage();
        }
        return $this->pdo;
    }

    public function closeConnection(): void
    {
        $this->pdo = null;
    }

    public function selectAll(): array
    {
        $pdo = $this->getConnection();
        $cursor = $pdo->query("select * from {$this->tableName}");
        $resultats = $cursor->fetchAll(\PDO::FETCH_CLASS, $this->className);
        $this->closeConnection();
        return $resultats;
    }

    //selectById
    //insert
    //update
    //delete
    //count
}
