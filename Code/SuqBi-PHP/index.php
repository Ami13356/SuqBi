<?php

use App\Config\RouterIntermedaire;

require_once __DIR__."/vendor/autoload.php";
require_once __DIR__."/utils/helpers.php";
if (session_status() === PHP_SESSION_NONE) {
    session_start();
}

$uri = ($_SERVER['REQUEST_URI']);
RouterIntermedaire::route($uri);
