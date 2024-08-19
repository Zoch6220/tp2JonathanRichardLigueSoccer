package com.tp1;

import com.tp3.LigueSoccer;

import javax.swing.*;
import java.text.Normalizer;
import java.util.List;
import java.util.Scanner;

public class EquipeSoccer implements Comparable<EquipeSoccer>  {
    //Attribut de class
    private String nom_equipe;
    private int nbr_victoire,nbr_defaite,nbr_nulle,total_but_pour,total_but_contre,match_jouer,pts,diff;
    private static Scanner sc=null;
    //Constructeur
   public EquipeSoccer(String unNom_equipe,int unNbr_victoire,int unNbr_defaite,int unNbr_nulle,int unTotal_but_pour,int unTotal_but_contre){
        nom_equipe=unNom_equipe;
        nbr_victoire=unNbr_victoire;
        nbr_defaite=unNbr_defaite;
        nbr_nulle=unNbr_nulle;
        total_but_pour=unTotal_but_pour;
        total_but_contre=unTotal_but_contre;
        match_jouer=calculer_match_joues();
        pts=calculer_points();
        diff=calculer_differentiel_buts();
    }
    public EquipeSoccer(String unNom){
        this.nom_equipe=unNom;
    }
//Acceseur

    public String getNom_equipe() {
        return nom_equipe;
    }

    public int getMatch_jouer() {
        return match_jouer;
    }

    // methode spécifique sans retour
//méthode permet l'affichage en console du classement
public static   void affiche_classement(List<EquipeSoccer> ligue){
    System.out.println("===========================================================================================================\n");
    for(EquipeSoccer equipe:ligue){
        System.out.println(equipe);
    }
    System.out.println("\n===========================================================================================================");
}
// Méthode permet de remettre à 0 la saison
public void reinitialiser_saison(){
    nbr_victoire=0;
    nbr_defaite=0;
    nbr_nulle=0;
    total_but_pour=0;
    total_but_contre=0;
    match_jouer=calculer_match_joues();
    pts=calculer_points();
    diff=calculer_differentiel_buts();
}
//méthode permet d'ajouté un match avec les but pour et contre comme parametre et d'ajuster le les statistiques de l'équipe
public void ajuster_stat_un_match(int but_pour,int but_contre){

    if(but_pour>but_contre)
        nbr_victoire++;
    else if(but_pour==but_contre)
        nbr_nulle++;
    else
        nbr_defaite++;
    total_but_contre+=but_contre;
    total_but_pour+=but_pour;
    match_jouer=calculer_match_joues();
    pts=calculer_points();
    diff=calculer_differentiel_buts();



}
//méthode permet l'affichage graphique, ayant comme parametre un StringBuilder,
public static void afficherGraphique(StringBuilder classement,String titre){
    JOptionPane.showMessageDialog(null,classement,titre,JOptionPane.INFORMATION_MESSAGE);
}
//methode spécifique avec retour
// methode utilisé pour créé un StringBuilder du classement, est utilisé avec afficherGraphique

    public  static StringBuilder listeLigueToString(List<EquipeSoccer> ligue){

        StringBuilder classement=new StringBuilder();

        classement.append(afficherTitre()+"\n");
        classement.append(afficherSeparateur()+"\n");
        for(EquipeSoccer equipeSoccer :ligue){
            classement.append(equipeSoccer);
            classement.append("\n");
        }

       return classement;
    }
    //permet de calculer le nombre de match jouer
    public int calculer_match_joues(){
        return nbr_defaite+nbr_nulle+nbr_victoire;
   }
    //permet de calculer le nombre de points
   public int calculer_points(){
        return  (nbr_victoire*3)+(nbr_nulle);
   }
   //Calcule le différencielle de but
   public int calculer_differentiel_buts(){
        return total_but_pour-total_but_contre;
   }
   @Override
   //permet de standardisé l'affichage
   public String toString() {
       String message = "";
       message = String.format(" %-25s : %5d %5d %5d %5d %5d %6d %5d %7d", nom_equipe,this.calculer_match_joues(),
               nbr_victoire,nbr_defaite,nbr_nulle,this.calculer_points(), total_but_pour,
               total_but_contre,this.calculer_differentiel_buts());
       return message;
   }

    public static String afficherTitre(){
        String titre="";
        titre = String.format("%-27s :  %5s %4s  %4s %5s %6s %5s %5s %7s"," NOM DE L'EQUIPE     ","MJ",
                "V","D","N","PTS", "BP","BC","DIFF");
        return titre;
    }
    public static String afficherSeparateur(){
       String sep;
       sep=String.format("%-26s :  %5s %4s  %4s %5s %6s %5s %5s %7s","=========================","===","===","===","===","===","===","===","===");
       return sep;
    }
   @Override
// permet de Sort la Liste de facon personnalisé
   public int compareTo(EquipeSoccer autre){

        int retour=0;
        if(this.pts<autre.pts){
            retour=1;
        } else if (this.pts> autre.pts) {
           retour=-1;
        }else{
            if(this.nbr_victoire<autre.nbr_victoire){
                retour=1;
            } else if (this.nbr_victoire>autre.nbr_victoire) {
                retour=-1;
            }else {
                if(this.diff<autre.diff){
                    retour=1;
                } else if (this.diff>autre.diff) {
                    retour = -1;
                }else {
// le normalizer permet d'ignoré l'accent durant le trie Alphabétique
                    if ((Normalizer.normalize(this.nom_equipe, Normalizer.Form.NFD)).replaceAll("\\p{M}]\"+", "")
                            .compareTo( Normalizer.normalize(autre.nom_equipe, Normalizer.Form.NFD).replaceAll("\\p{M}]\"+", ""))<0){
                            retour=-1;
                    } else if ((Normalizer.normalize(this.nom_equipe, Normalizer.Form.NFD)).replaceAll("\\p{M}]\"+", "")
                            .compareTo( Normalizer.normalize(autre.nom_equipe, Normalizer.Form.NFD).replaceAll("\\p{M}]\"+", ""))>0) {
                        retour=1;
                    }
                }
            }
        }
       return retour;
   }
}
