/*
*TestLigueChampionat.java
*permet de test la classe MatchAllerRetour et LigueSoccer
*@author Jonathan Richard
*/
package com.tp3;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Scanner;

public class TestLigueChampionat {
    public static void main(String[] args) {
        LigueSoccer ligueChampi=new LigueSoccer(lireString("Veuillez entr� le nom de la ligue: "));
        ligueChampi.telecharger_equipe("championat.txt");
        //classement de la ligue affin de prendre les 2 premiere equipe pour le match de championat
        Collections.sort(ligueChampi.getLst_equipe());
        ligueChampi.creer_match_championat(lectureDate("Date match aller"),lectureDate("Date match retour"));
        MatchAllerRetour matchChampionat=ligueChampi.obtenir_match_championat();
        // affichage de la ligue avant match championat
       System.out.println(ligueChampi);

        System.out.println(matchChampionat);
        boolean exeptionControl=false;
        while(!exeptionControl){
            try{
                matchChampionat.enregistrer_resultat_match_aller(lireEntier("Equipe exterieur: "+matchChampionat.getEquipeA().getNom_equipe()+" nombre de buts"),lireEntier("Equipe a domicile: "+matchChampionat.getEquipeB().getNom_equipe()+" nombre de buts"));
                exeptionControl=true;
            }catch (RuntimeException e){
                System.out.println(e);
            }
        }
        exeptionControl=false;
        System.out.println(matchChampionat);
        while(!exeptionControl){
            try{
                matchChampionat.enregistrer_resultat_match(lireEntier("Equipe Domicile: "+matchChampionat.getEquipeA().getNom_equipe()+" nombre de buts"),lireEntier("Equipe a l'exterieur: "+matchChampionat.getEquipeB().getNom_equipe()+" nombre de buts"),false);
                exeptionControl=true;
            }catch (RuntimeException e){
                System.out.println(e);
            }
        }
        System.out.println(matchChampionat);
        
        matchChampionat.getEquipeA().getNom_equipe();
        if(matchChampionat.equipeA_a_gagne())
            System.out.println("\n\n=================>Champion: "  +matchChampionat.getEquipeA().getNom_equipe()+" <===================\n");
        else if(matchChampionat.equipeB_a_gagne())
            System.out.println("\n\n=================>Champion: "  +matchChampionat.getEquipeB().getNom_equipe()+" <===================\n");





    }
    public static LocalDate lectureDate(String p_msg){
        LocalDate date=null;
        int jour,mois,annee;
        String input;
        boolean correct=false;
        while(!correct) {
            try{
                System.out.println(p_msg+"\n");

                input=lireString("Veuillez saisir l'ann�e");
                annee=Integer.parseInt(input);
                input=lireString("Veuillez saisir le mois");
                mois=Integer.parseInt(input);
                input=lireString("Veuillez saisir le jour");
                jour=Integer.parseInt(input);
                date=LocalDate.of(annee,mois,jour);
                correct = true;
            }catch (DateTimeException e){
                System.out.println("Veuiller entrer un format de date valide");
            }catch (NumberFormatException e){
                System.out.println("Veuillez entre un nombre\n");
            }

        }
        return date;
    }
    public static String lireString(String msg){
        String lecture;
        Scanner sc=new Scanner(System.in);
        System.out.print(msg+" : ");
        lecture=sc.nextLine();

        return lecture;
    }
    public static int lireEntier(String msg){
        int lecture=0;
        boolean fin=false;
        Scanner sc=new Scanner(System.in);
        while(!fin) {
            try {
                System.out.println(msg+" : ");
                lecture = Integer.parseInt(sc.nextLine());
                fin = true;

            } catch (RuntimeException e) {
                System.out.print("veuillez entrer un nombre");
            }
        }


        return lecture;
    }
}
