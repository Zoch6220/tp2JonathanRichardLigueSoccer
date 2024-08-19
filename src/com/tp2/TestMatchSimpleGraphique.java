/*
*TestMatchSimpleGraphique.java
*permet de test la classe MatchSimple et d'affiché graphiquement le classement
*@author Jonathan Richard
*/
package com.tp2;

import com.tp1.EquipeSoccer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestMatchSimpleGraphique {
    public static void main(String[] args) {
        StringBuilder matchliste=new StringBuilder();
        List<EquipeSoccer>listeEquipe;
        listeEquipe=creeListeEquipe();
        List<MatchSimple> listeMatch;
        listeMatch=creeListeMatch(listeEquipe);
        ajouterEnregistrement(listeMatch);
        matchliste.append("(#)Match     Date        EquipeA         ButsA       ButsB       EquipeB\n");
        matchliste.append("===========================================================================\n");
        for(MatchSimple match:listeMatch){
            matchliste.append(match+"\n");
        }
        matchliste.append("===========================================================================\n");

        EquipeSoccer.afficherGraphique(matchliste,"Match");
    }
    public static List<EquipeSoccer> creeListeEquipe() {
        List<EquipeSoccer>listeEquipe = new ArrayList<>();
        listeEquipe.add(new EquipeSoccer("FC_Toronto"));
        listeEquipe.add(new EquipeSoccer("Impact_MTL"));
        listeEquipe.add(new EquipeSoccer("Comette_Gatineau"));
        listeEquipe.add(new EquipeSoccer("Senateur_d'Ottawa"));
        return listeEquipe;

    }
    public static List<MatchSimple> creeListeMatch(List<EquipeSoccer> listeEquipe){
        List<MatchSimple> listeMatch = new ArrayList<>();
        listeMatch.add(new MatchSimple(listeEquipe.get(0),listeEquipe.get(1),10, LocalDate.of(2022,10,12)));
        listeMatch.add(new MatchSimple(listeEquipe.get(2),listeEquipe.get(3),5,LocalDate.of(2021,5,23)));
        listeMatch.add(new MatchSimple(listeEquipe.get(0),listeEquipe.get(3),9,LocalDate.of(2023,1,25)));
        listeMatch.add(new MatchSimple(listeEquipe.get(1),listeEquipe.get(2),4,LocalDate.of(2023,1,25)));
        return listeMatch;
    }
    public static void ajouterEnregistrement(List<MatchSimple> listeMatch){
        boolean reg=true,nonReg=false;
        listeMatch.get(0).enregistrer_resultat_match(5,2,nonReg);
        listeMatch.get(1).enregistrer_resultat_match(1,1,reg);
        listeMatch.get(2).enregistrer_resultat_match(2,3,reg);
    }
}
