package com.example.projetcalculmental.view;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ActionMenuItem;
import androidx.appcompat.view.menu.ActionMenuItemView;

import com.bumptech.glide.Glide;
import com.example.projetcalculmental.R;
import com.example.projetcalculmental.controller.CalculBaseHelper;
import com.example.projetcalculmental.controller.CalculDao;
import com.example.projetcalculmental.controller.CalculService;
import com.example.projetcalculmental.model.Classement;
import com.example.projetcalculmental.model.TypeOperationEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainEcranJeu extends AppCompatActivity {

    private Menu menu;
    private TextView textViewReponse;
    private Integer BORNE_HAUTE = 9999;
    private Integer premierElement = 0;
    private int max = 10;
    private int random1 = new Random().nextInt(max);
    private int random2 = new Random().nextInt(max);
    private boolean palier = false;
    private int essai = 0;
    private int result;
    protected Integer compteurBonnesReponses =0;
    private List<String> vie = new ArrayList<>();
    private TypeOperationEnum negatifOrNot = null;
    private TypeOperationEnum symbolEnum = null;
    protected CalculService calculService;
    protected String pseudo = "NoName";


    //Animation oiseau | Size écran | Timer
    private ImageView oiseau;
    private int screenWidth, screenHeight , frameHeight;
    private float oiseauX, oiseauY;
    private Timer timer = new Timer();
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecranjeu);
        // AJOUTER LE DAO ICI

        oiseau = findViewById(R.id.oiseauJeu);
        Glide
                .with(this)
                .load(R.drawable.oiseau)
                .into(oiseau);


        oiseau.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                Toast.makeText(MainEcranJeu.this, "Bien joué !",Toast.LENGTH_SHORT).show();
                ajoutPoint(5);
                oiseau.setClickable(false);

            }

        });

        FrameLayout frameLayout = findViewById(R.id.frame);
        frameHeight = frameLayout.getHeight();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        changePos();
                    }
                });
            }
        },200,50);

        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        screenWidth = size.x;
        screenHeight = size.y;




        calculService = new CalculService(new CalculDao(new CalculBaseHelper(this)));
        textViewReponse = findViewById(R.id.textViewReponse);
        Button bouton1 = findViewById(R.id.bouton1);
        bouton1.setOnClickListener(view -> ajouterNombre(1));
        Button bouton2 = findViewById(R.id.bouton2);
        bouton2.setOnClickListener(view -> ajouterNombre(2));
        Button bouton3 = findViewById(R.id.bouton3);
        bouton3.setOnClickListener(view -> ajouterNombre(3));
        Button bouton4 = findViewById(R.id.bouton4);
        bouton4.setOnClickListener(view -> ajouterNombre(4));
        Button bouton5 = findViewById(R.id.bouton5);
        bouton5.setOnClickListener(view -> ajouterNombre(5));
        Button bouton6 = findViewById(R.id.bouton6);
        bouton6.setOnClickListener(view -> ajouterNombre(6));
        Button bouton7 = findViewById(R.id.bouton7);
        bouton7.setOnClickListener(view -> ajouterNombre(7));
        Button bouton8 = findViewById(R.id.bouton8);
        bouton8.setOnClickListener(view -> ajouterNombre(8));
        Button bouton9 = findViewById(R.id.bouton9);
        bouton9.setOnClickListener(view -> ajouterNombre(9));
        Button bouton0 = findViewById(R.id.bouton0);
        bouton0.setOnClickListener(view -> ajouterNombre(0));


        Button boutonMoins = findViewById(R.id.boutonMoins);
        boutonMoins.setOnClickListener(view -> ajouterSymboleVal(TypeOperationEnum.SUBSTRACT));
        Button boutonEffacer = findViewById(R.id.boutonEffacer);
        boutonEffacer.setOnClickListener(view -> nettoyerTextView());
        Button boutonOK = findViewById(R.id.boutonOK);
        boutonOK.setOnClickListener(view -> verificationReponse());
        Button boutonValidationPseudo = findViewById(R.id.boutonValidationPseudo);
        boutonValidationPseudo.setOnClickListener(view -> creationPseudo());

        vie.add("❤");
        vie.add("❤");
        vie.add("❤");
        affichageCalcul();


    }

    public void changePos(){
        //oiseau
        oiseauX += 16;
        if (oiseauX >= screenWidth){
            oiseau.setClickable(true);
            oiseauX = 0;
            oiseauY = (float)Math.floor(Math.random()*(frameHeight - oiseau.getHeight()))-50;
        }
        oiseau.setX(oiseauX);
        oiseau.setY(oiseauY);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.toolbar,menu);
        this.menu =menu;
        return true;
    }



    public void ajouterNombre(Integer valeur) {
        if (10 * premierElement + valeur > BORNE_HAUTE) {
            Toast.makeText(this, getString(R.string.ERROR_VALEUR_TROP_GRANDE), Toast.LENGTH_LONG).show();
        } else {
            premierElement = 10 * premierElement + valeur;
        }
        majText();
    }

    public void ajouterSymboleVal(TypeOperationEnum typeOperation){
        this.negatifOrNot = typeOperation;
        majText();
    }
    public void ajouterSymbole(TypeOperationEnum typeOperation) {
        this.symbolEnum = typeOperation;
        majText();
    }

    private void majText() {
        String textAAfficher = "";
        if(negatifOrNot != null){
            textAAfficher = negatifOrNot.getSymbol() + " " + premierElement.toString();
        }else{
            textAAfficher = premierElement.toString();
        }


        textViewReponse.setText(textAAfficher);
    }

    private boolean nettoyerTextView() {
        textViewReponse.setText("");
        premierElement = 0;
        negatifOrNot = null;
        return true;
    }


    private void affichageCalcul() {
        List<String> list = new ArrayList<String>();
        list.add("+");
        list.add("-");
        list.add("x");
        Random randomizer = new Random();
        String randomElement = list.get(randomizer.nextInt(list.size()));

        switch (randomElement) {
            case "+":
                ajouterSymbole(TypeOperationEnum.ADD);
                break;
            case "-":
                ajouterSymbole(TypeOperationEnum.SUBSTRACT);
                break;
            case "x":
                ajouterSymbole(TypeOperationEnum.MULTIPLY);
                break;
            default:
                break;
        }
        TextView textViewCalcul = findViewById(R.id.textViewCalcul);
        String textCalcul = "";
        if(compteurBonnesReponses == 100 || compteurBonnesReponses == 200 || compteurBonnesReponses == 500 || compteurBonnesReponses == 700 || compteurBonnesReponses ==1000){
            random1 = new Random().nextInt(20);
            random2 = new Random().nextInt(20);
            Toast.makeText(this, "QUESTION BONUS !", Toast.LENGTH_LONG).show();
            palier = true;

        }
        textCalcul = random1 + " " + symbolEnum.getSymbol() + " " + random2;

        textViewCalcul.setText(textCalcul);

    }

    private void ajoutPoint(int nb){
        this.compteurBonnesReponses += nb;
        MenuItem score = menu.findItem(R.id.score);
        score.setTitle(compteurBonnesReponses.toString());
    }
    private void retireVie(){
        vie.remove(0);
        MenuItem echec = menu.findItem(R.id.erreurs);
        echec.setTitle(vie.toString());
    }

    private void creationPseudo(){
        EditText editTextTextPersonName = (EditText) findViewById(R.id.editTextTextPersonName);
        this.pseudo = editTextTextPersonName.getText().toString();
        Toast.makeText(this, pseudo,Toast.LENGTH_LONG).show();
    }

    private void verificationReponse() {
        if (negatifOrNot != null) {
            premierElement *= -1;
        }
        switch (symbolEnum) {
            case ADD:
                result = random1 + random2;
                break;
            case SUBSTRACT:
                result = random1 - random2;
                break;
            case MULTIPLY:
                result = random1 * random2;
                break;
        }
        if (result == premierElement) {
            if(!palier){
                random1 = new Random().nextInt(10);
                random2 = new Random().nextInt(10);
                ajoutPoint(10);
                Toast.makeText(this, "Bravo !  Tu gagnes 10 points !", Toast.LENGTH_SHORT).show();
            }else{
                random1 = new Random().nextInt(10);
                random2 = new Random().nextInt(10);
                ajoutPoint(20);
                Toast.makeText(this, "Bravo ! Tu gagnes 20 points !", Toast.LENGTH_LONG).show();
                palier = false;
            }


            affichageCalcul();
            nettoyerTextView();
        } else {
                if(vie.size() == 0){
                    Intent intent = new Intent(this, MainScore.class);
                    intent.putExtra("pseudo",pseudo);
                    intent.putExtra("compteurBonnesReponses",compteurBonnesReponses);
                    Classement classement = new Classement();
                    classement.setScore(compteurBonnesReponses);
                    classement.setPseudo(pseudo);
                    calculService.storeInDB(classement);
                    startActivity(intent);
                }else{
                    if(palier){
                        Toast.makeText(this, "Un point de compensation...", Toast.LENGTH_LONG).show();
                        ajoutPoint(1);
                        random1 = new Random().nextInt(10);
                        random2 = new Random().nextInt(10);
                        palier = false;
                        affichageCalcul();
                        nettoyerTextView();
                    }else{
                        retireVie();
                        Toast.makeText(this, "Non pas bon !", Toast.LENGTH_SHORT).show();
                    }
                };
        }
    }
}