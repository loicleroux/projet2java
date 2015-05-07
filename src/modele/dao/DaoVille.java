/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modele.metier.Ville;

/**
 *
 * @author btssio
 */
public class DaoVille {

    public static Ville selectFirst() throws SQLException {
        Ville uneVille = null;
        ResultSet rs;
        PreparedStatement pstmt;
        Jdbc jdbc = Jdbc.getInstance();
        // préparer la requête
        String requete = "SELECT * FROM VILLES WHERE ID = '1'";
        pstmt = jdbc.getConnexion().prepareStatement(requete);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("ID");
            String cdp = rs.getString("CDP");
            String ville = rs.getString("VILLE");
            uneVille = new Ville(id, ville, cdp);
        }
        return uneVille;
    }

    public static Ville selectOne(int idVille) throws SQLException {
        Ville uneVille = null;
        ResultSet rs;
        PreparedStatement pstmt;
        Jdbc jdbc = Jdbc.getInstance();
        // préparer la requête
        String requete = "SELECT * FROM VILLES WHERE ID = ?";
        pstmt = jdbc.getConnexion().prepareStatement(requete);
        pstmt.setInt(1, idVille);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            int id = idVille;
            String ville = rs.getString("VILLE");
            String cdp = rs.getString("CDP");
            uneVille = new Ville(id, ville, cdp);
        }
        return uneVille;
    }

    public static Ville selectOne(String ville) throws SQLException {
        Ville uneVille = null;
        ResultSet rs;
        PreparedStatement pstmt;
        Jdbc jdbc = Jdbc.getInstance();
        // préparer la requête
        String requete = "SELECT * FROM VILLES WHERE VILLE = ?";
//        String requete = "SELECT * FROM VILLES WHERE VILLE = 'CONDEISSIAT'";
        pstmt = jdbc.getConnexion().prepareStatement(requete);
        pstmt.setString(1, ville);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("ID");
            String cdp = rs.getString("CDP");
            uneVille = new Ville(id, ville, cdp);
        }
        return uneVille;
    }

    public static List<Ville> selectOne2(String cp) throws SQLException {
        List<Ville> lesVilles = new ArrayList<Ville>();
        Ville uneVille = null;
        ResultSet rs;
        PreparedStatement pstmt;
        Jdbc jdbc = Jdbc.getInstance();
        char x;
        int i = 0; //compteur
        int k = 0;
        // préparer la requête
        String requete;

        if (cp.length() < 5 && i < 1) {
            requete = "SELECT * FROM VILLES WHERE CDP like '" + cp + "%' ORDER BY ville";
        } else {
            requete = "SELECT * FROM VILLES WHERE CDP = ? ORDER BY ville";
        } 

        pstmt = jdbc.getConnexion().prepareStatement(requete);
        if (cp.length() == 5) {
            pstmt.setString(1, cp);
        }
        rs = pstmt.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("ID");
            String cdp = rs.getString("CDP");
            String ville = rs.getString("VILLE");
            uneVille = new Ville(id, ville, cdp);
            lesVilles.add(uneVille);
        }

        //taille ville = 0 alors rien dans rs a test
        if (lesVilles.isEmpty() && i < 1) {
            i++;
            String cpl = ""; // code postale long
            List<Ville> lesCpl = new ArrayList<Ville>(); // liste ayant les villes avec un code postal long
            cpl = cpl + cp.charAt(0) + cp.charAt(1);
            lesCpl = selectOne2(cpl);
            for (int j = 0; j < lesCpl.size(); j++) {
                cpl = lesCpl.get(j).getCdp();
                if (cpl.length() > 5) {
                    if (lesCpl.get(j).getCdp().contains(cp)) {
                        k = j;
                    }
                } else {
                    lesCpl.remove(lesCpl.get(j));
                }
            }
            lesVilles.add(lesCpl.get(k));
        }

        return lesVilles;
    }

    public static List<Ville> selectAll() throws SQLException {
        List<Ville> lesVilles = new ArrayList<Ville>();
        Ville uneVille;
        ResultSet rs;
        PreparedStatement pstmt;
        Jdbc jdbc = Jdbc.getInstance();
//         préparer la requête
        String requete = "SELECT * FROM VILLES";
        pstmt = jdbc.getConnexion().prepareStatement(requete);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("ID");
            String cdp = rs.getString("CDP");
            String ville = rs.getString("VILLE");
            uneVille = new Ville(id, ville, cdp);
            lesVilles.add(uneVille);
        }
        return lesVilles;
    }

    public static int insert(int idVille, Ville uneVille) throws SQLException {
        int nb;
        Jdbc jdbc = Jdbc.getInstance();
        String requete;
        ResultSet rs;
        PreparedStatement pstmt;
        requete = "INSERT INTO VILLES (ID, VILLE, CDP) VALUES (?, ?, ?)";
        pstmt = jdbc.getConnexion().prepareStatement(requete);
        pstmt.setInt(1, idVille);
        pstmt.setString(2, uneVille.getVille());
        pstmt.setString(3, uneVille.getCdp());
        nb = pstmt.executeUpdate();
        return nb;
    }

    public static int update(int idVille, Ville uneVille) throws SQLException {
        int nb;
        Jdbc jdbc = Jdbc.getInstance();
        String requete;
        ResultSet rs;
        PreparedStatement pstmt;
        requete = "UPDATE VILLES SET VILLE = ?, CDP = ?  WHERE ID = ?";
        pstmt = jdbc.getConnexion().prepareStatement(requete);
        pstmt.setString(1, uneVille.getVille());
        pstmt.setString(2, uneVille.getCdp());
        pstmt.setInt(3, idVille);
        nb = pstmt.executeUpdate();
        return nb;
    }

    public static int delete(int idVille) throws SQLException {
        int nb;
        Jdbc jdbc = Jdbc.getInstance();
        String requete;
        ResultSet rs;
        PreparedStatement pstmt;
        requete = "DELETE FROM VILLES WHERE ID = ?";
        pstmt = jdbc.getConnexion().prepareStatement(requete);
        pstmt.setInt(1, idVille);
        nb = pstmt.executeUpdate();
        return nb;
    }
}
