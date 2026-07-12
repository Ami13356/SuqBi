<?php
namespace App\Controller;

use App\Config\AbstractController;
use App\Config\Validator;
use App\Services\AdminService;
use App\Services\ClientService;
use App\Services\LivreurService;
use App\Services\VendeurService;

class SecurityController extends AbstractController
{
    public function __construct()
    {
    }

    public function login(): void
    {
        if ($_SERVER['REQUEST_METHOD'] === 'POST') {
            $type = $_POST['type'] ?? 'client';
            $email = trim($_POST['email'] ?? '');
            $mdp = $_POST['mdp'] ?? '';

            Validator::reset();
            Validator::isEmpty($email, 'email');
            Validator::isEmail($email, 'email');
            if ($type === 'client' || $type === 'admin') {
                Validator::isEmpty($mdp, 'mdp', 'Le mot de passe est obligatoire');
            }

            if (Validator::validate()) {
                $user = match ($type) {
                    'client' => ClientService::seConnecter($email, $mdp),
                    'vendeur' => VendeurService::seConnecter($email),
                    'livreur' => LivreurService::seConnecter($email),
                    'admin' => AdminService::seConnecter($email, $mdp),
                    default => null,
                };

                if ($user !== null) {
                    $_SESSION['userType'] = $type;
                    $_SESSION['userId'] = $user->getId();
                    $_SESSION['userNom'] = $user->getNom()." ".$user->getPrenom();
                    header("location:/$type/dashboard");
                    return;
                }
                Validator::reset();
                Validator::isEmpty('', 'global', 'Identifiants incorrects ou compte introuvable.');
            }

            $this->render('security/login', [
                'errors' => Validator::getErrors(),
                'old' => $_POST,
            ]);
            return;
        }

        $this->render('security/login', ['errors' => [], 'old' => []]);
    }

    public function logout(): void
    {
        $_SESSION = [];
        session_destroy();
        header("location:/security/login");
    }
}
