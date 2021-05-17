package com.example.calculatrice;

import java.net.CacheRequest;
import java.text.DecimalFormat;

public class Calculatrice {

    private DecimalFormat format_;

    private String affichage_;
    private int courant_;
    private boolean virgule_;
    private float coef_;
    private boolean reset_;
    private float memoire_;
    private char operation_;
    private boolean egal_;

    private String CalculActuel_;
    private String historique_;

    public String getAffichage() {
        System.out.println(memoire_);
        return affichage_;
    }

    public String getHistorique(){
        return historique_;
    }

    public Calculatrice() {
        format_ = new DecimalFormat("#.####");
        memoire_ = 0;
        operation_ = '?';
        this.raz();
        CalculActuel_ = "";
        egal_ = false;
        historique_ = "";
    }

    public void raz () {
        //Fontion de remise à zéro de l'opération en cours
        affichage_ = "";
        courant_ = 0;
        virgule_ = false;
        coef_ = 1;
        reset_ = false;
    }

    public void chiffre (int n){
        //Ajout d'un chiffre à l'affichage et l'opération
        if(egal_){
            memoire_ = 0;
            operation_ = '?';
            egal_ = false;
        }
        if(reset_) raz();
        courant_ = 10*courant_+n;
        affichage_ += n;
        if (virgule_) {
            coef_ /= 10;
        }
        CalculActuel_ += n;
    }

    public void virgule (){
        //Fonction d'ajout d'une virgule
        if(reset_) raz();
        virgule_ = true;
        affichage_ += ",";
        CalculActuel_ += ".";
    }

    public void operateur(char o){
        //Fonction d'appuie sur un opérateur
        reset_ = true;
        if(egal_){
            egal_ = false;
        }

        if(operation_ != '?'){
            switch(operation_) {
                case '+':
                    memoire_ = memoire_ + (courant_ * coef_);
                    break;
                case '-':
                    memoire_ = memoire_ - (courant_ * coef_);
                    break;
                case '*':
                    memoire_ = memoire_ * (courant_ * coef_);
                    break;
                case '/':
                    memoire_ = memoire_ / (courant_ * coef_);
                    break;
                default:
                    System.out.println("Opération inconnue");
            }

            if(o == '='){
                //Résultat de l'opération
                egal_= true;
                CalculActuel_ += (" = " + format_.format(memoire_) + "\n");
                historique_ += CalculActuel_;
                CalculActuel_ = "";
                affichage_ = format_.format(memoire_);
            }
        }else{
            memoire_ =  courant_ * coef_;
        }
        operation_ = o;
        if(o != '='){
            CalculActuel_ += " " + o + " ";
        }
    }
}
