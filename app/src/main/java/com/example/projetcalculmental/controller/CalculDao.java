package com.example.projetcalculmental.controller;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.projetcalculmental.model.Classement;

public class CalculDao extends com.example.projetcalculmental.controller.BaseDao<Classement> {
    static String cleScore = "score";
    static String clePseudo = "pseudo";

    public CalculDao(com.example.projetcalculmental.controller.DataBaseHelper helper) {
        super(helper);
    }

    @Override
    protected String getTableName() {
        return "classement";
    }

    @Override
    protected void putValues(ContentValues values, Classement entity) {
        values.put(cleScore,entity.getScore());
        values.put(clePseudo,entity.getPseudo());
    }

    @Override
    protected Classement getEntity(Cursor cursor) {
        cursor.moveToFirst();
        Integer indexScore = cursor.getColumnIndex(cleScore);
        Integer indexPseudo = cursor.getColumnIndex(clePseudo);

        Classement classement = new Classement();
        classement.setScore(cursor.getInt(indexScore));
        classement.setPseudo(cursor.getString(indexPseudo));
        cursor.close();
        return null;
    }

}
