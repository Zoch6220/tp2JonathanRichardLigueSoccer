/*
*TestMatchSimple.java
*permet de test la classe MatchSimple
*@author Jonathan Richard
*/

package com.tp2;


import com.tp1.EquipeSoccer;
import com.tp2.MatchSimple;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestMatchSimple {
    public static void main(String[] args) {
        boolean reg=true,nonReg=false;
        //initialisation liste de 4 equipe avec 0 statistic
        List<EquipeSoccer> listeEquipe = new ArrayList<>();
        listeEquipe.add(new EquipeSoccer("FC_Toronto"));
        listeEquipe.add(new EquipeSoccer("Impact_MTL"));
        listeEquipe.add(new EquipeSoccer("Comette_Gatineau"));
        listeEquipe.add(new EquipeSoccer("Senateur_d'Ottawa"));
        // initiation liste de 4 match     
        List<MatchSimple> listeMatch = new ArrayList<>();
        listeMatch.add(new MatchSimple(listeEquipe.get(0),listeEquipe.get(1),10,LocalDate.of(2022,10,12)));
        listeMatch.add(new MatchSimple(listeEquipe.get(2),listeEquipe.get(3),5,LocalDate.of(2021,5,23)));
        listeMatch.add(new MatchSimple(listeEquipe.get(0),listeEquipe.get(3),9,LocalDate.of(2023,1,25)));
        listeMatch.add(new MatchSimple(listeEquipe.get(1),listeEquipe.get(2),4,LocalDate.of(2023,1,25)));
        //triage de la liste
        Collections.sort(listeMatch);
        //affichage des match
        for(MatchSimple match:listeMatch)
            System.out.println(match);
        //enregistrement des matchs
        listeMatch.get(0).enregistrer_resultat_match(5,2,nonReg);
        listeMatch.get(1).enregistrer_resultat_match(1,1,reg);
        listeMatch.get(2).enregistrer_resultat_match(2,3,reg);
        System.out.println(listeMatch.get(2).equipeB_a_gagne());
        System.out.println("======================================================================================================================");
        //affichage chaque match trier
        for(MatchSimple match:listeMatch)
            System.out.println(match);
        System.out.println("========================================Equipe non trier================================================================");
        // affiche les Equipes non trier
        System.out.println(EquipeSoccer.afficherTitre());
        for(EquipeSoccer equipe:listeEquipe)
            System.out.println(equipe);
        Collections.sort(listeEquipe);// trie les equipes
        System.out.println("=================================================Equipe Triee=====================================================================");
        // affiche les equipe triee
        System.out.println(EquipeSoccer.afficherTitre());
        for(EquipeSoccer equipe:listeEquipe)
            System.out.println(equipe);
//====================reutillisation de la methode pour afficher la listeEquipe au complet de la class EquipeSoccer
//        System.out.println(EquipeSoccer.listeLigueToString(listeEquipe));
        System.out.println("===========================================Match inversé===========================================================");
        Collections.sort(listeEquipe,Collections.reverseOrder()); //triee en inverse equipe
        Collections.sort(listeMatch,Collections.reverseOrder());// triee en inverse
        //affichage liste de match
        for(MatchSimple match:listeMatch)
            System.out.println(match);
        System.out.println("=============================Equipe Inversé=======================================================");

        //affiche les equipes trier inversement
        System.out.println(EquipeSoccer.afficherTitre());
        for(EquipeSoccer equipe:listeEquipe)
            System.out.println(equipe);
        System.out.println("======================================Information Match================================");
        //affichage informaion
        for(MatchSimple match:listeMatch){
            System.out.println("Le match: "+match);
            System.out.println("Si le match a été enregistré: "+match.resultat_est_enregistrer());
            System.out.println("Si le match a été gagnée par l'équipeA: "+match.equipeA_a_gagne());
            System.out.println("Si le match a été gagnée par l'équipeB: "+match.equipeB_a_gagne());
            System.out.println("Si le match est nul: "+match.match_est_nulle());
            System.out.println("La date du match: "+match.getDate_du_match());
            System.out.println("Le numero du match: "+match.getNumero());
            System.out.println("Le nombre de buts, pour ce match, de l'équipeA: "+match.getNbr_buts_equipeA());
            System.out.println("Le nombre de buts, pour ce match, de l'équipeB: "+match.getNbr_buts_equipeb());
            System.out.println("Le nom de l'équipeA: "+match.getEquipeA().getNom_equipe());
            System.out.println("Le nom de l'équipeB: "+match.getEquipeB().getNom_equipe());
            System.out.println("============================================================================");
        }
    }

    public static String ajusterEspace(int esp) {
        String espace = "";
        for (int i = 1; i <= esp; i++)
            espace += " ";
        return espace;
    }
}
