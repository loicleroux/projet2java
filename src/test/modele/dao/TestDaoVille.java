/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.modele.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import modele.dao.DaoVille;
import modele.dao.Jdbc;
import modele.metier.Ville;
import static test.modele.dao.TestDaoAdresse.test0_Connexion;
import static test.modele.dao.TestDaoAdresse.test1_SelectUnique;
import static test.modele.dao.TestDaoAdresse.test2_SelectMultiple;

/**
 *
 * @author btssio
 */
public class TestDaoVille {

    public static void main(String[] args) {

        java.sql.Connection cnx = null;

        try {
            test0_Connexion();
            System.out.println("Test0 effectué : connexion\n");
            test1_SelectUnique(1);
            System.out.println("Test1 effectué : sélection unique\n");
            test2_SelectMultiple();
            System.out.println("Test2 effectué : sélection multiple\n");
            test3_Insert(37000);
            System.out.println("Test3 effectué : insertion\n");
            test4_Update(37000);
            System.out.println("Test4 effectué : mise à jour\n");
            test5_Delete(37000);
            System.out.println("Test5 effectué : suppression\n");
            test6_SelectUnique("CONDEISSIAT");
            System.out.println("Test6 effectué : selection unique\n");
            test7_SelectUnique("44400");
            System.out.println("Test7 effectué : selection unique\n");            
            test8_SelectFist();
            System.out.println("Test 8 effectué : selection du premier");
        } catch (ClassNotFoundException e) {
            System.err.println("Erreur de pilote JDBC : " + e);
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e);
        } finally {
            try {
                if (cnx != null) {
                    cnx.close();
                }
            } catch (SQLException e) {
                System.err.println("Erreur de fermeture de la connexion JDBC : " + e);
            }
        }

    }

    /**
     * Vérifie qu'une connexion peut être ouverte sur le SGBD
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void test0_Connexion() throws ClassNotFoundException, SQLException {
//        Jdbc.creer("com.mysql.jdbc.Driver", "jdbc:mysql://", "localhost/", "equipe6", "root", "joliverie");
        Jdbc.creer("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:", "@localhost:1521:XE", "", "btssio", "btssio");
        Jdbc.getInstance().connecter();
        Connection cnx = Jdbc.getInstance().getConnexion();
    }

    /**
     * Affiche une adresse d'après son identifiant
     *
     * @throws SQLException
     */
    public static void test1_SelectUnique(int idVille) throws SQLException {
        Ville cetteVille = DaoVille.selectOne(idVille);
        System.out.println("Adresse d'identifiant : " + idVille + " : " + cetteVille.toString());
    }

    /**
     * Affiche toutes les villes
     *
     * @param cnx : connexion ouverte sur la base de données
     * @throws SQLException
     */
    public static void test2_SelectMultiple() throws SQLException {
        List<Ville> desVilles = DaoVille.selectAll();
        System.out.println("Les villes lues : " + desVilles.toString());
    }

    /**
     * Ajoute un client
     *
     * @throws SQLException
     */
    public static void test3_Insert(int idVille) throws SQLException {
        Ville uneVille = new Ville(idVille, "coucou", "12345");
        int nb = DaoVille.insert(idVille, uneVille);
        System.out.println("Une nouvelle ville a été insérée: " + nb);
        test1_SelectUnique(idVille);
    }

    /**
     * Modifie une adresse
     *
     * @throws SQLException
     */
    public static void test4_Update(int idVille) throws SQLException {
        Ville uneVille = new Ville(idVille, "testtt", "95458");
        int nb = DaoVille.update(idVille, uneVille);
        System.out.println("Une ville a été modifiée: " + nb);
        test1_SelectUnique(idVille);
    }

    /**
     * Supprime un enregistrement
     *
     * @throws SQLException
     */
    public static void test5_Delete(int idVille) throws SQLException {
        int nb = DaoVille.delete(idVille);
        System.out.println("Une ville a été supprimée: " + nb);
        test2_SelectMultiple();
    }

    public static void test6_SelectUnique(String ville) throws SQLException {
        Ville cetteVille = DaoVille.selectOne(ville);
        System.out.println("Ville : " + cetteVille.toString());
    }

    public static void test7_SelectUnique(String cp) throws SQLException {
        List<Ville> desVilles = DaoVille.selectOne2(cp);
        for (int i = 0; i < desVilles.size(); i++) {
            System.out.println("Ville : " + desVilles.get(i));
        }
        
    }
    
    public static void test8_SelectFist() throws SQLException{
        Ville uneVille = DaoVille.selectFirst();
        System.out.println("vile : " + uneVille);
        
    }
}