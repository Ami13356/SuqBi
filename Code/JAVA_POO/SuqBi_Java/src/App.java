import entity.Client;
import entity.Livreur;
import entity.Vendeur;
import views.AdminView;
import views.AuthView;
import views.ClientView;
import views.LivreurView;
import views.VendeurView;

public class App {
    public static void main(String[] args) throws Exception {
        int profil;
        do {
            System.out.println("\n===== Bienvenue sur SuqBi =====");
            System.out.println("Vos courses de marche, livrees simplement.");
            System.out.println("1. Client");
            System.out.println("2. Vendeur");
            System.out.println("3. Livreur");
            System.out.println("4. Admin");
            System.out.println("5. Quitter");
            profil = AuthView.saisieEntier("Choisir votre profil : ");

            switch (profil) {
                case 1:
                    lancerClient();
                    break;
                case 2:
                    lancerVendeur();
                    break;
                case 3:
                    lancerLivreur();
                    break;
                case 4:
                    lancerAdmin();
                    break;
                case 5:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez reessayer.");
            }
        } while (profil != 5);
    }

    private static void lancerClient() {
        int action = AuthView.saisieEntier("1.Creer un compte  2.Se connecter  : ");
        Client client = null;
        do {
            if (action == 1) {
                String nom = AuthView.saisieChaine("Nom : ");
                String prenom = AuthView.saisieChaine("Prenom : ");
                String tel = AuthView.saisieChaine("Tel : ");
                String email = AuthView.saisieChaine("Email : ");
                String mdp = AuthView.saisieChaine("Mot de passe : ");
                client = services.ClientService.creerCompte(nom, prenom, tel, email, mdp);
            } else {
                String email = AuthView.saisieChaine("Email : ");
                String mdp = AuthView.saisieChaine("Mot de passe : ");
                client = services.ClientService.seConnecter(email, mdp);
            }
        } while (client == null);
        System.out.println("Compte pret. Bienvenue " + client.getPrenom() + " !");
        ClientView.afficherMenuClient(client);
    }

    private static void lancerVendeur() {
        int action = AuthView.saisieEntier("1.Creer un compte  2.Se connecter  : ");
        Vendeur vendeur = null;
        do {
            if (action == 1) {
                String nom = AuthView.saisieChaine("Nom : ");
                String prenom = AuthView.saisieChaine("Prenom : ");
                String tel = AuthView.saisieChaine("Tel : ");
                String email = AuthView.saisieChaine("Email : ");
                vendeur = services.VendeurService.creerCompte(nom, prenom, tel, email);
            } else {
                String email = AuthView.saisieChaine("Email : ");
                vendeur = services.VendeurService.seConnecter(email);
            }
        } while (vendeur == null);
        System.out.println("Compte pret. Bienvenue " + vendeur.getPrenom() + " !");
        VendeurView.afficherMenuVendeur(vendeur);
    }

    private static void lancerLivreur() {
        int action = AuthView.saisieEntier("1.Creer un compte  2.Se connecter  : ");
        Livreur livreur = null;
        do {
            if (action == 1) {
                String nom = AuthView.saisieChaine("Nom : ");
                String prenom = AuthView.saisieChaine("Prenom : ");
                String tel = AuthView.saisieChaine("Tel : ");
                String email = AuthView.saisieChaine("Email : ");
                livreur = services.LivreurService.creerCompte(nom, prenom, tel, email);
            } else {
                String email = AuthView.saisieChaine("Email : ");
                livreur = services.LivreurService.seConnecter(email);
            }
        } while (livreur == null);
        System.out.println("Compte pret. Bienvenue " + livreur.getPrenom() + " !");
        LivreurView.afficherMenuLivreur(livreur);
    }

    private static void lancerAdmin() {
        entity.Admin admin = null;
        do {
            String email = AuthView.saisieChaine("Email : ");
            String mdp = AuthView.saisieChaine("Mot de passe : ");
            admin = services.AdminService.seConnecter(email, mdp);
        } while (admin == null);
        System.out.println("Connexion reussie. Bienvenue Admin !");
        AdminView.afficherMenuAdmin();
    }
}
