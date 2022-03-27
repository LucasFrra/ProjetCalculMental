package com.example.projetcalculmental.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public abstract  class BaseDao<T extends BaseEntity> { // Paramètre T veut dire que BaseDao prend tous les types (utilisation possible des classes etc...)
    private final com.example.projetcalculmental.controller.DataBaseHelper dbHelper;

    public BaseDao(com.example.projetcalculmental.controller.DataBaseHelper helper){
        this.dbHelper = helper;
    }

    protected abstract String getTableName();
    protected abstract void putValues(ContentValues values, T entity);
    protected abstract T getEntity(Cursor cursor);

    /**
     * @param entity : element a ajouter dans la base
     * @return : l element créait avec son ID
     */
    public T create(T entity){ // Possibilité de récupérer cette classe et les suivantes pour le projet
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        putValues(values, entity);

        long newRowId = db.insert(getTableName(), null, values);
        entity.id = newRowId;
        return entity;
    }

    protected List<T> query(String selection, String[] selectionArgs, String sortOrder){ // Éxécuter des requêtes SQL dans la BDD
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                getTableName(),
                null,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        List items = new ArrayList<T>();
        while(cursor.moveToNext()) {
            items.add(getEntity(cursor));

        }
        cursor.close();

        return items;
    }


    public T lastOrNull() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor =db.query(
                getTableName(),
                null,
                null,
                null,
                null,
                null,
                null);

        cursor.moveToLast();
        T last = this.getEntity(cursor);
        cursor.close();

        return last;
    }


    public int getScore() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("select score from "+getTableName(), null);
        cursor.moveToFirst();
        int count= cursor.getInt(0);
        cursor.close();

        return count;
    }

    public String getPseudo() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("select pseudo from "+getTableName(), null);
        cursor.moveToFirst();
        String count2 = cursor.getString(0);
        cursor.close();

        return count2;
    }

    public long count() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("select count(*) from "+getTableName(), null);
        cursor.moveToFirst();
        int count= cursor.getInt(0);
        cursor.close();

        return count;
    }
}
