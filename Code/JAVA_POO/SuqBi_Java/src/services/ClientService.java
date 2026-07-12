package services;

import java.util.ArrayList;

import entity.Client;

public final class ClientService {
    private static ArrayList<Client> clients = new ArrayList<>();
    private static int prochainId = 1;

    private ClientService() {
    }

    private static boolean emailExiste(String email) {
        for (Client c : clients) {
            if (c.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    // Cas d'utilisation : Creer un compte (Client)
    public static Client creerCompte(String nom, String prenom, String tel, String email, String mdp) {
        if (emailExiste(email)) {
            System.out.println("Ce compte existe deja.");
            return null;
        }
        Client client = new Client(prochainId, nom, prenom, tel, email, mdp);
        prochainId++;
        clients.add(client);
        return client;
    }

    // Cas d'utilisation : Se connecter (Client)
    public static Client seConnecter(String email, String mdp) {
        for (Client c : clients) {
            if (c.getEmail().equalsIgnoreCase(email)) {
                if (c.getMdp().equals(mdp)) {
                    return c;
                }
                System.out.println("Mot de passe incorrect.");
                return null;
            }
        }
        System.out.println("Compte introuvable.");
        return null;
    }

    public static ArrayList<Client> getTousLesClients() {
        return clients;
    }

    // Cas d'utilisation Admin : Modifier un client
    public static void modifierClient(Client client, String nom, String prenom, String tel, String email) {
        client.setNom(nom);
        client.setPrenom(prenom);
        client.setTel(tel);
        client.setEmail(email);
    }

    // Cas d'utilisation Admin : Supprimer un client
    public static void supprimerClient(Client client) {
        clients.remove(client);
    }
}
