package com.tp1;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

public class TestEquipeSoccer {
    public static void main(String args[]){
        EquipeSoccer equipeTest1=new EquipeSoccer("L'impact              ",4,5,1,12,13);
        System.out.println(equipeTest1);
        equipeTest1.ajuster_stat_un_match(5,2);
        equipeTest1.ajuster_stat_un_match(1,1);
        equipeTest1.ajuster_stat_un_match(2,3);
        System.out.println("Résultat calculer_points(): "+equipeTest1.calculer_points());
        System.out.println("Résultat calculer_différenciel_buts(): "+equipeTest1.calculer_differentiel_buts());
        System.out.println("Résultat calculer_match_joues(): "+equipeTest1.calculer_match_joues());
      System.out.println(equipeTest1);
      equipeTest1.reinitialiser_saison();
        System.out.println(equipeTest1);
       List<EquipeSoccer>ligue=new ArrayList<>();
        ligue.add(new EquipeSoccer("Real Longueuil        ",4,5,1,12,13));
        ligue.add(new EquipeSoccer("Laval FC              ",4,1,5,14,13));
        ligue.add(new EquipeSoccer("Real Lévis            ",4,5,1,12,13));
        ligue.add(new EquipeSoccer("Thunder de Québec     ",4,5,1,15,13));
        ligue.add(new EquipeSoccer("Red Bull de Terrebonne",5,1,2,14,13));

       Collections.sort(ligue);

       EquipeSoccer.affiche_classement(ligue);




    }

}
