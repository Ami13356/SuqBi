<?php
namespace App\Controller;

use App\Config\AbstractController;
use App\Config\Validator;
use App\Services\VendeurService;

class VendeurController extends AbstractController
{
    public function __construct()
    {
    }

    public function form(): void
    {
        $this->render('vendeur/form', ['errors' => [], 'old' => []]);
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
            $vendeur = VendeurService::creerCompte($nom, $prenom, $tel, $email);
            if ($vendeur !== null) {
                $_SESSION['userType'] = 'vendeur';
                $_SESSION['userId'] = $vendeur->getId();
                $_SESSION['userNom'] = $vendeur->getNom()." ".$vendeur->getPrenom();
                header("location:/vendeur/dashboard");
                return;
            }
            Validator::isEmpty('', 'email', 'Ce compte existe deja.');
        }

        $this->render('vendeur/form', [
            'errors' => Validator::getErrors(),
            'old' => $_POST,
        ]);
    }

    public function dashboard(): void
    {
        if (!isset($_SESSION['userType']) || $_SESSION['userType'] !== 'vendeur') {
            header("location:/security/login");
            return;
        }
        $this->render('vendeur/dashboard', []);
    }
}
