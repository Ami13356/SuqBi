<?php
namespace App\Controller;

use App\Config\AbstractController;
use App\Config\Validator;
use App\Services\ClientService;

class ClientController extends AbstractController
{
    public function __construct()
    {
    }

    public function form(): void
    {
        $this->render('client/form', ['errors' => [], 'old' => []]);
    }

    public function create(): void
    {
        $nom = trim($_POST['nom'] ?? '');
        $prenom = trim($_POST['prenom'] ?? '');
        $tel = trim($_POST['tel'] ?? '');
        $email = trim($_POST['email'] ?? '');
        $mdp = $_POST['mdp'] ?? '';

        Validator::reset();
        Validator::isEmpty($nom, 'nom');
        Validator::isEmpty($prenom, 'prenom');
        Validator::isEmpty($tel, 'tel');
        Validator::isEmpty($email, 'email');
        Validator::isEmail($email, 'email');
        Validator::isEmpty($mdp, 'mdp');
        Validator::minLength($mdp, 'mdp', 6, 'Le mot de passe doit contenir au moins 6 caracteres');

        if (Validator::validate()) {
            $client = ClientService::creerCompte($nom, $prenom, $tel, $email, $mdp);
            if ($client !== null) {
                $_SESSION['userType'] = 'client';
                $_SESSION['userId'] = $client->getId();
                $_SESSION['userNom'] = $client->getNom()." ".$client->getPrenom();
                header("location:/client/dashboard");
                return;
            }
            Validator::isEmpty('', 'email', 'Ce compte existe deja.');
        }

        $this->render('client/form', [
            'errors' => Validator::getErrors(),
            'old' => $_POST,
        ]);
    }

    public function dashboard(): void
    {
        if (!isset($_SESSION['userType']) || $_SESSION['userType'] !== 'client') {
            header("location:/security/login");
            return;
        }
        $this->render('client/dashboard', []);
    }
}
