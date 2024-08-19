package com.tp1;

import java.util.ArrayList;
import java.util.List;

public class TestGraphiqueEquipeSoccer {
    public  static void main(String[] args) {
        List<EquipeSoccer>ligueGraphique=new ArrayList<>();
        ligueGraphique.add(new EquipeSoccer("Real Longueuil        ",4,5,1,12,13));
        ligueGraphique.add(new EquipeSoccer("Laval FC              ",4,1,5,14,13));
        ligueGraphique.add(new EquipeSoccer("Real Lévis            ",4,5,1,12,13));
        ligueGraphique.add(new EquipeSoccer("Thunder de Québec     ",4,5,1,15,13));
        ligueGraphique.add(new EquipeSoccer("Red Bull de Terrebonne",5,1,2,14,13));

        EquipeSoccer.afficherGraphique(EquipeSoccer.listeLigueToString(ligueGraphique),"Classement");
    }

}
