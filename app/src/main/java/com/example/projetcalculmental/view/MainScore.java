package com.example.projetcalculmental.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetcalculmental.R;
import com.example.projetcalculmental.controller.CalculBaseHelper;
import com.example.projetcalculmental.controller.CalculDao;
import com.example.projetcalculmental.controller.CalculService;

public class MainScore extends AppCompatActivity {

    protected CalculService calculService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        calculService = new CalculService(new CalculDao(new CalculBaseHelper(this)));
        TextView pseudo1 = findViewById(R.id.textViewPseudo1);
        TextView score1 = findViewById(R.id.textViewScore1);
        pseudo1.setText("Il y a " + calculService.getComputeCount() + " calculs dans la base");
        Intent intent = getIntent();
        score1.setText(intent.getStringExtra("pseudo") + " | score :" + intent.getIntExtra("compteurBonnesReponses",0));
        //pseudo1.setText(calculService.getPseudo());
        //score1.setText(calculService.getScore());
    }


}
