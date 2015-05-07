package test.modele.metier;

import modele.metier.Ville;


public class TestVille{
    
    public static void main(String[] args){
        Ville vlle, vlle1, vlle2;
        System.out.println("\nTest n°1 : instanciation et accesseurs");
        vlle = new Ville(1, "Saint Sébastien sur Loire", "44230");
        System.out.println("ville : " + vlle);
        System.out.println("\nTest n°2 : mutateurs");
        vlle.setCdp("44265");
        vlle.setVille("Nantes");
        System.out.println("vlle : " + vlle);
        vlle1 = new Ville(1,null, null);
        System.out.println(!vlle1.equals(vlle));
        vlle2 = new Ville(2, null, null);
        System.out.println(!vlle1.equals(vlle2));
    
    }

}