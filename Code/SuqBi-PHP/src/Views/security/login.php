<div class="row justify-content-center">
    <div class="col-md-6">
        <div class="card shadow-sm">
            <div class="card-body p-4">
                <h3 class="card-title mb-3">Se connecter</h3>

                <?php if (isset($errors['global'])): ?>
                    <div class="alert alert-danger"><?= htmlspecialchars($errors['global']) ?></div>
                <?php endif; ?>

                <form method="POST" action="/security/login">
                    <div class="mb-3">
                        <label class="form-label">Profil</label>
                        <select name="type" class="form-select">
                            <option value="client" <?= (($old['type'] ?? '') === 'client') ? 'selected' : '' ?>>Client</option>
                            <option value="vendeur" <?= (($old['type'] ?? '') === 'vendeur') ? 'selected' : '' ?>>Vendeur</option>
                            <option value="livreur" <?= (($old['type'] ?? '') === 'livreur') ? 'selected' : '' ?>>Livreur</option>
                            <option value="admin" <?= (($old['type'] ?? '') === 'admin') ? 'selected' : '' ?>>Admin</option>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Email</label>
                        <input type="text" name="email" value="<?= htmlspecialchars($old['email'] ?? '') ?>"
                               class="form-control <?= isset($errors['email']) ? 'is-invalid' : '' ?>">
                        <?php if (isset($errors['email'])): ?>
                            <div class="invalid-feedback"><?= htmlspecialchars($errors['email']) ?></div>
                        <?php endif; ?>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Mot de passe <small class="text-muted">(Client et Admin uniquement)</small></label>
                        <input type="password" name="mdp"
                               class="form-control <?= isset($errors['mdp']) ? 'is-invalid' : '' ?>">
                        <?php if (isset($errors['mdp'])): ?>
                            <div class="invalid-feedback"><?= htmlspecialchars($errors['mdp']) ?></div>
                        <?php endif; ?>
                    </div>

                    <button type="submit" class="btn btn-success w-100">Se connecter</button>
                </form>

                <hr>
                <p class="text-center mb-1">Pas encore de compte ?</p>
                <div class="d-flex gap-2">
                    <a href="/client/form" class="btn btn-outline-success btn-sm flex-fill">Client</a>
                    <a href="/vendeur/form" class="btn btn-outline-success btn-sm flex-fill">Vendeur</a>
                    <a href="/livreur/form" class="btn btn-outline-success btn-sm flex-fill">Livreur</a>
                </div>
            </div>
        </div>
    </div>
</div>
