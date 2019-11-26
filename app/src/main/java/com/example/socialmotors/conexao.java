package com.example.socialmotors;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class conexao extends SQLiteOpenHelper {

    private static final String name = "socialmotors";
    private static final int version = 1;


    public conexao(@Nullable Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE cadastro (\n" +
                " id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                " marcaveiculo VARCHAR (50) NOT NULL,\n" +
                " modeloveiculo VARCHAR (50) NOT NULL,\n" +
                " tipoveiculo varchar(50) NOT NULL\n" +
                ")";

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
