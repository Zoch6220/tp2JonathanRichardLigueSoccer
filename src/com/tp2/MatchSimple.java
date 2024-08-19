/*
*MatchSimple.java
*la classe permet de créé l'object MatchSimple et de le motivié et le manipulé
*@author Jonathan Richard
*/

package com.tp2;

import com.tp1.EquipeSoccer;

import java.time.LocalDate;

public class MatchSimple implements Comparable<MatchSimple> {
    //================================Attribut===========================================
    protected int numero;
    protected EquipeSoccer equipeA;
    protected EquipeSoccer equipeB;
    protected LocalDate date_du_match;
    protected int nbr_buts_equipeA = -1;
    protected int nbr_buts_equipeB = -1;

    //=============================Constructeur===========================
    public MatchSimple(EquipeSoccer p_equipeA, EquipeSoccer p_equipeB, int p_numero, LocalDate p_date) {
        equipeA = p_equipeA;
        equipeB = p_equipeB;
        numero = p_numero;
        date_du_match = p_date;
    }

    //===============================Accesseur============================


    public int getNumero() {
        return numero;
    }

    public EquipeSoccer getEquipeA() {
        return equipeA;
    }

    public EquipeSoccer getEquipeB() {
        return equipeB;
    }

    public LocalDate getDate_du_match() {
        return date_du_match;
    }

    public int getNbr_buts_equipeA() {
        return nbr_buts_equipeA;
    }

    public int getNbr_buts_equipeb() {
        return nbr_buts_equipeB;
    }

    //===========================Mutateur===========================================

    public void set_date_du_match(LocalDate date_du_match) {
        this.date_du_match = date_du_match;
    }

    public void setNumeroMatch(int numero) {
        this.numero = numero;
    }

    //===================Méthode sans retour============================================
    public void enregistrer_resultat_match(int p_buts_equipeA, int p_buts_equipeB, boolean p_match_de_saison_reg) {
        if (nbr_buts_equipeA == -1 && nbr_buts_equipeB == -1) {
            nbr_buts_equipeA = p_buts_equipeA;
            nbr_buts_equipeB = p_buts_equipeB;
            if (p_match_de_saison_reg) {
                equipeA.ajuster_stat_un_match(nbr_buts_equipeA, nbr_buts_equipeB);
                equipeB.ajuster_stat_un_match(nbr_buts_equipeB, nbr_buts_equipeA);
            }
        }
    }

    //============================methode avec retour==========================
    public boolean equipeA_a_gagne() {
        if (nbr_buts_equipeA != -1 && nbr_buts_equipeB != -1) {
            if (nbr_buts_equipeA > nbr_buts_equipeB)
                return true;

        }

        return false;
    }

    public boolean equipeB_a_gagne() {
        if (this.getNbr_buts_equipeA() != -1 && this.getNbr_buts_equipeb() != -1) {
            if (this.getNbr_buts_equipeA() < this.getNbr_buts_equipeb())
                return true;
        }
        return false;
    }

    public boolean match_est_nulle() {
        if (nbr_buts_equipeA != -1 && nbr_buts_equipeB != -1) {
            if (nbr_buts_equipeA == nbr_buts_equipeB)
                return true;
        }
        return false;
    }
    public boolean resultat_est_enregistrer(){
        boolean enregis=false;
        if(this.nbr_buts_equipeA != -1 && this.nbr_buts_equipeB != -1) {
            enregis = true;
            return enregis;
        }
        return enregis;
    }


    // CompareTo trier en mettant les match les plus recent en premier
    // si egalité le numeros de match le moins eleve en premier
    public int compareTo(MatchSimple autre) {
        if (autre.date_du_match.isAfter(this.date_du_match))
            return -1;
        else if (autre.date_du_match.isBefore(this.date_du_match)) {
            return 1;
        } else {
            if (autre.getNumero() < this.getNumero())
                return 1;
            else
                return -1;
        }
    }

    @Override
    public String toString() {
        String espace = TestMatchSimple.ajusterEspace(5);
        String nonEnr = "...";
        String msg = String.format("%s %s %s %s %s %s ", "(#" + numero + ")", date_du_match +  " >>>",equipeA.getNom_equipe(), nbr_buts_equipeA +  " -", nbr_buts_equipeB, equipeB.getNom_equipe());

        if (nbr_buts_equipeA != -1 && nbr_buts_equipeB != -1) {
            if (this.equipeA_a_gagne()) {

                msg = msg.replace(equipeA.getNom_equipe(), equipeA.getNom_equipe().toUpperCase());
            } else if (this.equipeB_a_gagne()) {
                msg = msg.replace(equipeB.getNom_equipe(), equipeB.getNom_equipe().toUpperCase());
            }

        } else {
            msg= msg.format("%s %s %s %s %s %s ", "(#" + numero + ")", date_du_match +  " >>>", equipeA.getNom_equipe(), nonEnr +  " -", nonEnr, equipeB.getNom_equipe());
        }
        return  msg.replace(" ", espace);
    }
}

