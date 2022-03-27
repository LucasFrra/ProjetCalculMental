package com.example.projetcalculmental.controller;

import android.content.Context;

public class CalculBaseHelper extends com.example.projetcalculmental.controller.DataBaseHelper {

    public CalculBaseHelper(Context context) {
        super(context, "classement", 1);
    }

    @Override
    protected String getCreationSql() {
        return "CREATE TABLE IF NOT EXISTS classement (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                CalculDao.cleScore + " INTEGER NOT NULL," + // FAIRE ATTENTION À L'ESPACE JUSTE AVANT LE INTEGER INDISPENSABLE
                CalculDao.clePseudo + " VARCHAR(255) NOT NULL" + // FAIRE ATTENTION À L'ESPACE JUSTE AVANT LE INTEGER INDISPENSABLE
                ")";
    }

    @Override
    protected String getDeleteSql() {
        return null;
    }
}
