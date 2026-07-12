<?php
namespace App\Config;

use App\Controller\AdminController;
use App\Controller\ClientController;
use App\Controller\LivreurController;
use App\Controller\SecurityController;
use App\Controller\VendeurController;

final class RouterIntermedaire
{
    private function __construct()
    {
    }

    public static function route(string $uri): void
    {
        $uriParts = explode('/', $uri);
        $controllerName = $uriParts[1] ?? "security";
        $uriPart2 = $uriParts[2] ?? "login";
        $actionName = explode('?', $uriPart2)[0];

        switch ($controllerName) {
            case 'security':
                $ctrl = new SecurityController();
                if (method_exists($ctrl, $actionName)) {
                    $ctrl->$actionName();
                } else {
                    http_response_code(404);
                    echo "Page Not Found";
                }
                break;

            case 'client':
                $ctrl = new ClientController();
                if (method_exists($ctrl, $actionName)) {
                    $ctrl->$actionName();
                } else {
                    http_response_code(404);
                    echo "Page Not Found";
                }
                break;

            case 'vendeur':
                $ctrl = new VendeurController();
                if (method_exists($ctrl, $actionName)) {
                    $ctrl->$actionName();
                } else {
                    http_response_code(404);
                    echo "Page Not Found";
                }
                break;

            case 'livreur':
                $ctrl = new LivreurController();
                if (method_exists($ctrl, $actionName)) {
                    $ctrl->$actionName();
                } else {
                    http_response_code(404);
                    echo "Page Not Found";
                }
                break;

            case 'admin':
                $ctrl = new AdminController();
                if (method_exists($ctrl, $actionName)) {
                    $ctrl->$actionName();
                } else {
                    http_response_code(404);
                    echo "Page Not Found";
                }
                break;

            default:
                $ctrl = new SecurityController();
                $ctrl->login();
                break;
        }
    }
}
