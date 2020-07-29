package com.example.fatdetector.importantStuff;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.fatdetector.importantStuff.Fat.FatEntry;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "fatlist.db";
    public static final int DATABASE_VERSION = 1;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_GROCERYLIST_TABLE = "CREATE TABLE " +
                FatEntry.TABLE_NAME + " (" +
                FatEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FatEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                FatEntry.COLUMN_AMOUNT + " INTEGER NOT NULL, " +
                FatEntry.COLUMN_DESSERT + "INTEGER NOT NULL," +
                FatEntry.COLUMN_FASTFOOD + "INTEGER NOT NULL," +
                FatEntry.COLUMN_LATESNACK + "INTEGER NOT NULL," +
                FatEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";
        db.execSQL(SQL_CREATE_GROCERYLIST_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FatEntry.TABLE_NAME);
        onCreate(db);
    }
}
