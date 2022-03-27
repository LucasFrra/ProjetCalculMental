package com.example.projetcalculmental.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.projetcalculmental.R;

public class MainAccueil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button boutonNouvellePartie = findViewById(R.id.boutonNouvellePartie);
        boutonNouvellePartie.setOnClickListener(view -> lancerActivityEcranJeu());
        Button boutonScore = findViewById(R.id.boutonScore);
        boutonScore.setOnClickListener(view -> lancerActivityScore());
        Button boutonAPropos = findViewById(R.id.boutonAPropos);
        boutonAPropos.setOnClickListener(view -> lancerActivityAPropos());
    }

    private void lancerActivityEcranJeu(){
        Intent intent = new Intent(this, MainEcranJeu.class);
        startActivity(intent);
    }

    private void lancerActivityAPropos(){
        Intent intent = new Intent(this, MainAPropos.class);
        startActivity(intent);
    }

    private void lancerActivityScore(){
        Intent intent = new Intent(this, MainScore.class);
        startActivity(intent);
    }
}