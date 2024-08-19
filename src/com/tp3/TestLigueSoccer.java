/*
*TestLigueSoccer.java
*permet de test la classe LigueSoccer
*@author Jonathan Richard
*/
package com.tp3;

import com.tp1.EquipeSoccer;
import com.tp2.MatchSimple;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class TestLigueSoccer {
    public static void main(String[] args) throws RuntimeException{
        //3.3.2 preparation des donn�es
        LigueSoccer ligue=new LigueSoccer(lireString("Veuillez entrer le nom de la nouvelle ligue"));
        ligue.telecharger_equipe("Equipes.txt");
        System.out.println(ligue);
        boolean continuer=false;
        while (!continuer) {
            String reponse = lireString("voulez vous commencer une nouvelle saison oui/non");
            if (reponse.equalsIgnoreCase("oui")) {
                //cree une nouvelle saison
                ligue.creer_nouvelle_saison(lireEntier("Nombre de match a domicile"),lectureDate("Date de debut de saison"));


                boolean nouveauMatch=true;
                while(nouveauMatch){
                    try {
                        String choix=lireString("Voulez vous ajouter un match");
                        if (choix.equalsIgnoreCase("oui")){
                            boolean fin=false,exeptControl=true;
                            int compteur_numero_match=500;
                            do{
                                EquipeSoccer equipeA=null,equipeB=null;
                                while(exeptControl) {
                                    try {
                                        equipeA = ligue.chercherEquipeNom(lireString("Equipe a domicile"));
                                        exeptControl=false;
                                    }catch (NullPointerException e){
                                        System.out.println(e);
                                    }
                                }while (!exeptControl){
                                    try {
                                        equipeB = ligue.chercherEquipeNom(lireString("Equipe a l'etranger"));
                                        if(equipeA.equals(equipeB))
                                            System.out.println("Veuillez choisir une equipe diff�rente de la premiere");
                                        else {
                                            exeptControl=true;}
                                    }catch (NullPointerException e){
                                        System.out.println(e);
                                    }
                                }
                                //ajoute des matchs a la demande de l'utilisateur, les match commence avec le numero de match 500 et les enregistres immediatement
                                ligue.ajouterMatch(new MatchSimple(equipeA,equipeB,compteur_numero_match,lectureDate("Veuillez entr� la date du match")));
                                ligue.chercherMatchsNumeros(compteur_numero_match).enregistrer_resultat_match(lireEntier("But pour equipe domicile"),lireEntier("But equipe exterieur"),true);
                                compteur_numero_match++;
                                boolean fin2=false;
                                while(!fin2) {
                                    reponse = lireString("Voulez vous continuer oui/non a ajouter des matchs");
                                    if (reponse.equalsIgnoreCase("non")){
                                        fin2 = true;
                                        fin=true;
                                        nouveauMatch=false;
                                    }
                                    else if (reponse.equalsIgnoreCase("oui")) {
                                        fin2=true;
                                    }else
                                        System.out.println("le choix est invalide");
                                }
                            }while (!fin);

                        } else if (choix.equalsIgnoreCase("non")) {
                         nouveauMatch=false;

                        } else
                            throw new RuntimeException("Veuillez faire un choix valide");
                    }catch (RuntimeException e){
                        System.out.println(e);
                    }

                }
                continuer=true;
            } else if (reponse.equalsIgnoreCase("non")) {
                continuer = true;
            } else
                System.out.println("le choix est invalide");
        }
        //3.3.3 gestion des matchs
        continuer=false;
        Boolean controleExeption=false,enregisMatch=true;
        while(!continuer){
            System.out.println("\n*************Menu*************\n");
            System.out.println("\nOption A: Afficher la ligue\nOption B: Afficher match de la semaine\nOption C: Match d'uns equipe entre 2 dates");
            System.out.println("Option D: Enregistr� resulat d'un match\nOption Q: QUITTER le programme");

                    char choix = lireString("Selection").toLowerCase().charAt(0);
                    switch (choix) {
                        case 'a':
                            //affichage du classement ainsi que la liste des matchs
                            System.out.println(ligue);
                            System.out.println("\n***************** Match de la saison *****************\n");
                            for (MatchSimple match:ligue.getLst_matchs())
                                System.out.println(match);
                            break;
                        case 'b':
                            //affiche les match de la semaine
                            LocalDate dateAjourdhui=LocalDate.now();
                            System.out.println("************ Match de la semaine ************\n");
                            System.out.println("================================================");
                            for(MatchSimple match: ligue.getLst_matchs()){
                                if((dateAjourdhui.minusDays(3).isBefore(match.getDate_du_match())||dateAjourdhui.minusDays(3).equals(match.getDate_du_match()))
                                        &&(dateAjourdhui.plusDays(3).isAfter(match.getDate_du_match())||dateAjourdhui.plusDays(3).isEqual(match.getDate_du_match())))
                                    System.out.println(match);
                            }
                            break;
                        case 'c':
                            //affiche les match d'un equipe selon un interval de date
                            System.out.println("Recherche par interval de  date et par equipe");
                            LocalDate dateApres=lectureDate("Veuillez entr� la premiere date");
                            LocalDate dateAvant=lectureDate("Veuillez entr� la deuxieme date");
                            EquipeSoccer equipeRecherche;
                            List<MatchSimple>listeMatchRechercher;

                            listeMatchRechercher=ligue.chercherMatchEntreDate(dateApres,dateAvant);
                            do{
                                equipeRecherche=ligue.chercherEquipeNom(lireString("Veuillez choisir une equipe"));
                                if(equipeRecherche==null)
                                    System.out.println("Veuillez choisir une equipe qui existe");
                            }while(equipeRecherche==null);

                            if(listeMatchRechercher!=null){

                            for(MatchSimple match:listeMatchRechercher) {
                                    if (match.getEquipeA().getNom_equipe().equals(equipeRecherche.getNom_equipe()) || match.getEquipeB().getNom_equipe().equals(equipeRecherche.getNom_equipe()))
                                        System.out.println(match);

                             }

                            }else
                                System.out.println("Aucun match trouve");
                            break;
                        case 'd':
                            while (enregisMatch) {
                                while (!controleExeption) {

                                    try {
                                        MatchSimple match = ligue.chercherMatchsNumeros(lireEntier("Veuillez entr� un numeros de match"));
                                        System.out.println(match);
                                        if(!match.resultat_est_enregistrer()){
                                        match.enregistrer_resultat_match(lireEntier("Match numero: " + match.getNumero() + "\nBut pour l'�quipe " + match.getEquipeA().getNom_equipe()), lireEntier("But pour l'�quipe " + match.getEquipeB().getNom_equipe()), true);
                                            System.out.println(match);
                                        controleExeption = true;
                                        }else
                                            System.out.println("D�sol� le match est deja enregistr�");
                                    } catch (RuntimeException e) {
                                        System.out.println(e);
                                    }
                                }
                                String choixContinuer=lireString("Voulez vous continuer a enregistrer des matchs");
                                 if ((choixContinuer.equalsIgnoreCase("non"))) {
                                    enregisMatch=false;
                                }
                                 else if(choixContinuer.equalsIgnoreCase("oui"))
                                    controleExeption=false;
                                 else
                                    System.out.println("Choix non valide");

                            }
                            break;
                        case 'q':
                            continuer=true;
                            System.out.println("Au revoir!!!");
                            break;
                        default:
                            System.out.println("Veuillez choisir un choix valide");
                    }


            }
        ligue.sauvegarde_equipe("test.txt");


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
