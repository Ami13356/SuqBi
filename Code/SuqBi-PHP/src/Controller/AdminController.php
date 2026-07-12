<?php
namespace App\Controller;

use App\Config\AbstractController;

class AdminController extends AbstractController
{
    public function __construct()
    {
    }

    public function dashboard(): void
    {
        if (!isset($_SESSION['userType']) || $_SESSION['userType'] !== 'admin') {
            header("location:/security/login");
            return;
        }
        $this->render('admin/dashboard', []);
    }
}
