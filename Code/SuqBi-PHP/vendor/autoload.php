<?php
// Autoloader PSR-4 fait main, equivalent au vendor/autoload.php genere par
// composer. Evite d'avoir a lancer "composer install" (pas de dependance
// externe dans ce projet) tout en respectant la meme convention App\ => src/.

spl_autoload_register(function (string $class): void {
    $prefix = 'App\\';
    $baseDir = __DIR__ . '/../src/';

    $len = strlen($prefix);
    if (strncmp($prefix, $class, $len) !== 0) {
        return;
    }

    $relativeClass = substr($class, $len);
    $file = $baseDir . str_replace('\\', '/', $relativeClass) . '.php';

    if (file_exists($file)) {
        require_once $file;
    }
});
