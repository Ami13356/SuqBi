<div class="row justify-content-center">
    <div class="col-md-6">
        <div class="card shadow-sm">
            <div class="card-body p-4">
                <h3 class="card-title mb-3">Creer un compte Client</h3>

                <form method="POST" action="/client/create">
                    <div class="mb-3">
                        <label class="form-label">Nom</label>
                        <input type="text" name="nom" value="<?= htmlspecialchars($old['nom'] ?? '') ?>"
                               class="form-control <?= isset($errors['nom']) ? 'is-invalid' : '' ?>">
                        <?php if (isset($errors['nom'])): ?>
                            <div class="invalid-feedback"><?= htmlspecialchars($errors['nom']) ?></div>
                        <?php endif; ?>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Prenom</label>
                        <input type="text" name="prenom" value="<?= htmlspecialchars($old['prenom'] ?? '') ?>"
                               class="form-control <?= isset($errors['prenom']) ? 'is-invalid' : '' ?>">
                        <?php if (isset($errors['prenom'])): ?>
                            <div class="invalid-feedback"><?= htmlspecialchars($errors['prenom']) ?></div>
                        <?php endif; ?>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Telephone</label>
                        <input type="text" name="tel" value="<?= htmlspecialchars($old['tel'] ?? '') ?>"
                               class="form-control <?= isset($errors['tel']) ? 'is-invalid' : '' ?>">
                        <?php if (isset($errors['tel'])): ?>
                            <div class="invalid-feedback"><?= htmlspecialchars($errors['tel']) ?></div>
                        <?php endif; ?>
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
                        <label class="form-label">Mot de passe</label>
                        <input type="password" name="mdp"
                               class="form-control <?= isset($errors['mdp']) ? 'is-invalid' : '' ?>">
                        <?php if (isset($errors['mdp'])): ?>
                            <div class="invalid-feedback"><?= htmlspecialchars($errors['mdp']) ?></div>
                        <?php endif; ?>
                    </div>

                    <button type="submit" class="btn btn-success w-100">Creer mon compte</button>
                </form>

                <p class="text-center mt-3 mb-0">
                    <a href="/security/login">Deja un compte ? Se connecter</a>
                </p>
            </div>
        </div>
    </div>
</div>
