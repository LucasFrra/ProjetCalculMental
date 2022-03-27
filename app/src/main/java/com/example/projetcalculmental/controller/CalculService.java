package com.example.projetcalculmental.controller;

import com.example.projetcalculmental.model.Classement;

public class CalculService {
    private CalculDao calculDao;

    public CalculService(CalculDao calculDao) {this.calculDao = calculDao;}

    public Long getComputeCount(){
        return calculDao.count();
    }

    public int getComputeScore(){
        return calculDao.getScore();
    }

    public String getComputePseudo(){
        return calculDao.getPseudo();
    }

    public void storeInDB(Classement classement){
        calculDao.create(classement);
    }


}
