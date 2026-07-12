<?php
namespace App\Controller;

use App\Config\AbstractController;
use App\Config\Validator;
use App\Services\LivreurService;

class LivreurController extends AbstractController
{
    public function __construct()
    {
    }

    public function form(): void
    {
        $this->render('livreur/form', ['errors' => [], 'old' => []]);
    }

    public function create(): void
    {
        $nom = trim($_POST['nom'] ?? '');
        $prenom = trim($_POST['prenom'] ?? '');
        $tel = trim($_POST['tel'] ?? '');
        $email = trim($_POST['email'] ?? '');

        Validator::reset();
        Validator::isEmpty($nom, 'nom');
        Validator::isEmpty($prenom, 'prenom');
        Validator::isEmpty($tel, 'tel');
        Validator::isEmpty($email, 'email');
        Validator::isEmail($email, 'email');

        if (Validator::validate()) {
            $livreur = LivreurService::creerCompte($nom, $prenom, $tel, $email);
            if ($livreur !== null) {
                $_SESSION['userType'] = 'livreur';
                $_SESSION['userId'] = $livreur->getId();
                $_SESSION['userNom'] = $livreur->getNom()." ".$livreur->getPrenom();
                header("location:/livreur/dashboard");
                return;
            }
            Validator::isEmpty('', 'email', 'Ce compte existe deja.');
        }

        $this->render('livreur/form', [
            'errors' => Validator::getErrors(),
            'old' => $_POST,
        ]);
    }

    public function dashboard(): void
    {
        if (!isset($_SESSION['userType']) || $_SESSION['userType'] !== 'livreur') {
            header("location:/security/login");
            return;
        }
        $this->render('livreur/dashboard', []);
    }
}
