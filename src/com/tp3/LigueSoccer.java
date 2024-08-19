
/*
*LigueSoccer.java
*permet de créé l'object LigueSoccer et de le manipuler
*@author Jonathan Richard
*/
package com.tp3;

import com.tp1.EquipeSoccer;
import com.tp2.MatchSimple;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.*;

public class LigueSoccer {
    //attribut
    private String nom_ligue;
    private List<EquipeSoccer> lst_equipe=null;
    private List<MatchSimple> lst_matchs=null;
//==================constructeur=========================
    public LigueSoccer(String unNom){
        nom_ligue=unNom;


    }
//==================asseseur================================
    public String getNom_ligue() {
        return nom_ligue;
    }

    public List<EquipeSoccer> getLst_equipe() {
        return lst_equipe;
    }

    public List<MatchSimple> getLst_matchs() {
        return lst_matchs;
    }
// ==========================methodes sans retour================================
    public void ajouterEquipe(EquipeSoccer p_equipe){
        lst_equipe.add(p_equipe);
    }

    public void ajouterMatch(MatchSimple p_match){
        lst_matchs.add(p_match);
    }
    //permet la sauvegarde du classement
    public void sauvegarde_equipe(String p_nomFichier)  {

        try{

            FileWriter fw=new FileWriter(p_nomFichier);
            BufferedWriter sortie= new BufferedWriter(fw);

            sortie.write("**************"+nom_ligue+"**************");
            sortie.newLine();
            sortie.write(EquipeSoccer.afficherTitre());
            sortie.newLine();
            sortie.write(EquipeSoccer.afficherSeparateur());
            sortie.newLine();
            for(EquipeSoccer equipe:this.lst_equipe){
                sortie.write(String.valueOf(equipe));
                sortie.newLine();
            }
            sortie.close();

        }catch (IOException e){
            System.out.println("Incapable de cr�� le fichier");
        }

    }
    //permet de telecharger les donnés du fichier text et les assigner a un objet
    public void telecharger_equipe(String p_nomFichier) {
        BufferedReader entree;

        try {
            //creation du flux d'entr�e
            entree = new BufferedReader(new FileReader(p_nomFichier));
            String uneLigne;
            int cpt = 0;
            if(lst_equipe!=null)
                lst_equipe.clear();
            else
                lst_equipe=new ArrayList<>();
            if(lst_matchs!=null)
                lst_matchs.clear();
            else
                lst_matchs=new ArrayList<>();


            while ((uneLigne = entree.readLine()) != null) {
                if (cpt > 3) {

                    uneLigne = uneLigne.replace("   ", " ");
                    uneLigne = uneLigne.replace("  ", " ");
                    String[] table1 = uneLigne.split(":");
                    table1[0] = table1[0].trim();
                    table1[1] = table1[1].trim();

                    String[] table2 = table1[1].split(" ");
                    for (int i = 0; i < table2.length; i++) {
                        table2[i] = table2[i].trim();
                    }
                    lst_equipe.add(new EquipeSoccer(table1[0], Integer.parseInt(table2[1]), Integer.parseInt(table2[2]), Integer.parseInt(table2[3]), Integer.parseInt(table2[5]), Integer.parseInt(table2[6])));


                }
                cpt++;
            }
            entree.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Fichier introuvable");
        }catch (IOException e){
            System.out.println("autre erreur");
        }
    }
    //cree une nouvelle saison ou le p_nombreMatchDomicile contre chaque autre equipe, si il ya 5 equipe il aura 4 match a domicile pour chaque equipe
    public void creer_nouvelle_saison(int p_nombreMatchDomicile,LocalDate p_dateDebut) {
        int cpt = 1, idx = 0;
        int cptdate = 0;
        if(this.lst_matchs==null)
            this.lst_matchs=new ArrayList<>();
        else
            this.lst_matchs.clear();
        if(lst_equipe==null)
            lst_equipe=new ArrayList<>();
        else {
            for (EquipeSoccer equipe : this.lst_equipe) {
                equipe.reinitialiser_saison();
            }
        }
        int y=p_nombreMatchDomicile;
        for(int i=1;i<=p_nombreMatchDomicile;i++){
            for(EquipeSoccer equipeA:lst_equipe){
                for(EquipeSoccer equipeB:lst_equipe){
                    if(!equipeA.equals(equipeB))
                        lst_matchs.add(new MatchSimple(equipeA,equipeB,cpt,p_dateDebut));
                }
            }
        }
        Collections.shuffle(this.lst_matchs);
        for(MatchSimple match:this.lst_matchs){
            match.set_date_du_match(p_dateDebut.plusDays(cptdate));
            cptdate+=2;
        }
        for(MatchSimple match:lst_matchs){
            match.setNumeroMatch(cpt);
            cpt++;
        }

        Collections.sort(this.lst_matchs);
    }
    //cree un match de championat et l'ajoute a la lst_match 
    public void creer_match_championat(LocalDate p_date_aller,LocalDate p_date_retour){
        MatchSimple match=new MatchAllerRetour(lst_equipe.get(0),lst_equipe.get(1),9999,p_date_aller,p_date_retour);
        lst_matchs.add(match);
    }
    //===========================methode avec un retour======================================
    public EquipeSoccer chercherEquipeNom (String p_nom)throws NullPointerException{
        EquipeSoccer equipeTrouve=null;
        for(EquipeSoccer equipe:lst_equipe){
            if(equipe.getNom_equipe().equals(p_nom))
                equipeTrouve=equipe;
        }
        if(equipeTrouve==null)
            throw new NullPointerException("L'�quipe que vous chercher n'existe pas");
        return equipeTrouve;
    }
    public MatchSimple chercherMatchsNumeros(int p_numero)throws RuntimeException{
        MatchSimple matchTrouve=null;
        if(p_numero<0)
            throw new RuntimeException("Le num�ros de match ne peux �tre n�gatif");
        for(MatchSimple match:lst_matchs){
            if(match.getNumero()==p_numero)
                matchTrouve=match;
        }
        if(matchTrouve==null)
            throw new RuntimeException("le match n'existe pas");
        return matchTrouve;
    }
    //permet de chercher les match entre 2 dates inclusivement
    public List<MatchSimple> chercherMatchEntreDate(LocalDate p_date1,LocalDate p_date2){
        List<MatchSimple> listeMatchDate=new ArrayList<>();
        for(MatchSimple match:lst_matchs){
            if(match.getDate_du_match().isAfter(p_date1.plusDays(1))&& match.getDate_du_match().isBefore(p_date2.plusDays(1)))
                listeMatchDate.add(match);

        }
        if(listeMatchDate.isEmpty())
            listeMatchDate=null;

        return listeMatchDate;
    }
// chercher tous les match de la liste de match, et retour le match qui est une instance de la classe MatchAllerRetour
    public MatchAllerRetour obtenir_match_championat()throws RuntimeException{
        MatchAllerRetour matchAllerRetour=null;
        for(MatchSimple match:lst_matchs) {
            if (match instanceof MatchAllerRetour)
                matchAllerRetour = (MatchAllerRetour) match;
        }
        if(matchAllerRetour==null)
            throw new RuntimeException("Aucun match de championat trouv�");
        return matchAllerRetour;
    }
    public String toString(){
        String message="**************"+nom_ligue+"**************\n\n";

        //reutilise la methode du tp1 pour transformer la lst_equipe en string
        message+=EquipeSoccer.listeLigueToString(lst_equipe);


        return message;
    }




}
