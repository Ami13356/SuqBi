<?php
namespace App\Config;

abstract class AbstractController
{
    protected function render(string $view, array $data = []): void
    {
        extract($data);
        require_once dirname(__DIR__)."/Views/layout/header.partial.php";
        require_once dirname(__DIR__)."/Views/$view.php";
        require_once dirname(__DIR__)."/Views/layout/footer.partial.php";
    }

    protected function __construct()
    {
    }
}
