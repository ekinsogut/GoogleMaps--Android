package com.ekinsogut.gourmet;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "gourment.db";
    public static final int DB_VERSION = 1;

    public DbHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(Users.getSql());
        db.execSQL(Comment.getSql());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("DROP TABLE IF EXISTS UsersTable");
        db.execSQL("DROP TABLE IF EXISTS CommentTable");

        onCreate(db);
    }
}


