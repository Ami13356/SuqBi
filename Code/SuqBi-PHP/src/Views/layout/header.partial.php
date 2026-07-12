<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SuqBi - Vos courses de marche, livrees simplement</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <nav class="navbar navbar-expand-lg navbar-dark bg-success">
        <div class="container">
            <a class="navbar-brand fw-bold" href="/security/login">SuqBi</a>
            <?php if (isset($_SESSION['userType'])): ?>
                <span class="navbar-text text-white me-3">
                    Connecte en tant que <?= htmlspecialchars(ucfirst($_SESSION['userType'])) ?>
                    (<?= htmlspecialchars($_SESSION['userNom']) ?>)
                </span>
                <a href="/security/logout" class="btn btn-outline-light btn-sm">Se deconnecter</a>
            <?php endif; ?>
        </div>
    </nav>
    <div class="container py-4">
