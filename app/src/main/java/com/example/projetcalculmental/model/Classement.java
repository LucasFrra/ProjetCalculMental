package com.example.projetcalculmental.model;

import com.example.projetcalculmental.controller.BaseEntity;

public class Classement extends BaseEntity {

    Integer score;
    String pseudo;


    public Integer getScore() {
        return score;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
}

