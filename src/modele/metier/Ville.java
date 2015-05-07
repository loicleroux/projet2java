/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.metier;

/**
 *
 * @author btssio
 */
public class Ville {
    private int id; 
    private String ville;
    private String cdp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCdp() {
        return cdp;
    }

    public void setCdp(String cdp) {
        this.cdp = cdp;
    }

    public Ville(int id, String ville, String cdp) {
        this.id = id;
        this.ville = ville;
        this.cdp = cdp;
    }

    @Override
    public String toString() {
        return "Ville{" + "id=" + id + ", ville=" + ville + ", cdp=" + cdp + '}';
    }
   
   
}
