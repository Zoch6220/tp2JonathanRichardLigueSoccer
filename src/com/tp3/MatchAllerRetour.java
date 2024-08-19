/*
*MatchAllerRetour.java
*permet de créé l'object MatchAllerRetour et de le manipuler
*@author Jonathan Richard
*/
package com.tp3;

import com.tp1.EquipeSoccer;
import com.tp2.MatchSimple;

import java.time.LocalDate;

public class MatchAllerRetour extends MatchSimple {//le le match retour est identifier par le parent
    private LocalDate date_match_aller;
    private int nbr_but_equipeA_match_aller=-1;
    private int nbr_but_equipeB_match_aller=-1;
    public MatchAllerRetour(EquipeSoccer p_equipeA, EquipeSoccer p_equipeB, int p_numero, LocalDate p_date_match_aller,LocalDate p_date_match_retour){
        super( p_equipeA,  p_equipeB,  p_numero,  p_date_match_retour);
        date_match_aller=p_date_match_aller;
    }

    public LocalDate getDate_match_aller() {
        return date_match_aller;
    }

    public void setDate_match_aller(LocalDate date_match_aller) {
        this.date_match_aller = date_match_aller;
    }

    public void enregistrer_resultat_match_aller(int p_butsEquipeA,int p_butsEquipeB)throws RuntimeException{//enfant
        if(p_butsEquipeA==p_butsEquipeB)
            throw new RuntimeException("Les match ne peuvent pas finir a egalit�");
        if(nbr_but_equipeA_match_aller==-1&&nbr_but_equipeB_match_aller==-1){
            this.nbr_but_equipeA_match_aller=p_butsEquipeA;
            this.nbr_but_equipeB_match_aller=p_butsEquipeB;
        }
    }
    // enregistre le resultat dans le parent et verifie que les match ne sont pas egal avec le throws
    public void enregistrer_resultat_match(int p_butsEquipeA,int p_butsEquipeB,boolean p_match_de_saison_reg)throws RuntimeException{
        if(p_butsEquipeA==p_butsEquipeB)
            throw new RuntimeException("Les match ne peuvent pas finir a egalit�");
        if(nbr_buts_equipeA==-1&&nbr_buts_equipeB==-1){
            super.nbr_buts_equipeA=p_butsEquipeA;
            super.nbr_buts_equipeB=p_butsEquipeB;
        }

    }
    //methode qui return un boolean pour savoir qui a gagn� selon les regle du matchAllerRetour
    public boolean equipeA_a_gagne(){
        int totalButA = nbr_buts_equipeA + nbr_but_equipeA_match_aller;
        int totalButB = nbr_buts_equipeB + nbr_but_equipeB_match_aller;
        boolean resultat=false;
        if((nbr_buts_equipeA!=-1&&nbr_buts_equipeB!=-1)&&(nbr_but_equipeA_match_aller!=-1&&nbr_but_equipeB_match_aller!=-1)) {

            if (totalButA > totalButB)
                resultat = true;
            else if (totalButA == totalButB) {
                if (nbr_but_equipeA_match_aller > nbr_but_equipeB_match_aller)
                    resultat = true;
            } else
                resultat = true;
        }else
            resultat=false;

        return resultat;
    }
    public boolean equipeB_a_gagne(){
        boolean resultat=false;
        int totalButA = nbr_buts_equipeA + nbr_but_equipeA_match_aller;
        int totalButB = nbr_buts_equipeB + nbr_but_equipeB_match_aller;
        if((nbr_buts_equipeA!=-1&&nbr_buts_equipeB!=-1)&&(nbr_but_equipeA_match_aller!=-1&&nbr_but_equipeB_match_aller!=-1)) {

            if (totalButA < totalButB)
                resultat = true;
            else if (totalButA == totalButB) {
                if (nbr_buts_equipeB>nbr_buts_equipeA)
                    resultat = true;
            } else
                resultat = true;
        }else
            resultat=false;

        return resultat;
    }


    public boolean match_est_nulle() {
        return false;
    }
//reformatage pour afficher selon le document du tp
    public String toString(){
        String enrA="Resultat Equipe A pas enregistrer",enrB="Resultat Equipe B pas enregistrer";


        String msg="Match de championat\n=========================\n";
        msg+="\nMatch Aller-Retour ["+this.getNumero()+"] "+this.getEquipeA().getNom_equipe()+" vs "+this.getEquipeB().getNom_equipe();
        String aller="\nAller: ("+this.getDate_match_aller()+") : "+enrA+" - "+enrB;
        if(nbr_but_equipeA_match_aller!=-1&&nbr_but_equipeB_match_aller!=-1) {
            aller=aller.replace(enrA,Integer.toString(this.nbr_but_equipeA_match_aller));
            aller=aller.replace(enrB,Integer.toString(this.nbr_but_equipeB_match_aller));
        }
        String retour="\nRetour: ("+this.getDate_du_match()+") : "+enrA+" - "+enrB;
        if(nbr_buts_equipeA!=-1&&nbr_buts_equipeB!=-1) {
            retour=retour.replace(enrA,Integer.toString(this.nbr_buts_equipeA));
            retour=retour.replace(enrB,Integer.toString(this.nbr_buts_equipeB));
        }
        msg+=aller;
        msg+=retour;
        if((nbr_buts_equipeA==-1&&nbr_buts_equipeB==-1)||(nbr_but_equipeA_match_aller==-1&&nbr_but_equipeB_match_aller==-1))
            msg+="\nCombin�: "+enrA+" - "+enrB;
        else
            msg+="\nCombin�: "+(nbr_but_equipeA_match_aller+nbr_buts_equipeA)+" - "+(nbr_but_equipeB_match_aller+nbr_buts_equipeB);
        if(!this.resultat_est_enregistrer()){
            msg+="\nGagnant: Aucun";

        }else {
            if(this.equipeA_a_gagne()){
            msg+="\nGagnant:"+this.equipeA.getNom_equipe();

            }else{
                msg+="\nGagnant:"+this.equipeB.getNom_equipe();

            }
        }
        return msg;
    }
}
