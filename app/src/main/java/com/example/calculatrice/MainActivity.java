package com.example.calculatrice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static private Calculatrice calc_; //Calculatrice en static afin de garder l'historique lors d'un changement d'orientation
    private TextView historique_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Création de l'objet calculatrice s'il n'existe pas
        if (calc_ == null){
            calc_ = new Calculatrice();
        }

        //Récupération de l'historique s'il l'appareil est en mode paysage
        historique_ = (TextView)findViewById(R.id.historique);
        if(historique_ != null) {
            historique_.setText(calc_.getHistorique());
        }
    }

    public void saisir(View vue) {
        //Récupération des appuis sur les boutons
        Button bouton = (Button)vue;
        //Récupération du nom de la touche
        char touche = bouton.getText().charAt(0);

        TextView afficheur = (TextView)findViewById(R.id.afficheur);

        //Appel d'action en fontion de la touche appuyé
        if (touche == '.'){
            calc_.virgule();
        }else if (touche == '+' || touche == '-' || touche == '*' || touche == '/') {
            calc_.operateur(touche);
        }else if(touche == '='){
            calc_.operateur(touche);
        }else{
            System.out.println("touche = " + touche);
            calc_.chiffre(Character.getNumericValue(touche));
        }

        //Mise à jour des affichages lors d'un appuis sur un bouton
        afficheur.setText(calc_.getAffichage());
        if(historique_ != null) {
            historique_.setText(calc_.getHistorique());
        }
    }
}