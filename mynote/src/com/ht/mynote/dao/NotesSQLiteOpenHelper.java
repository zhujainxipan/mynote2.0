package com.ht.mynote.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by IntelliJ IDEA
 * Project: com.ht.mynote.utils
 * Author: 安诺爱成长
 * Email: 1399487511@qq.com
 * Date: 2015/5/4
 */
public class NotesSQLiteOpenHelper extends SQLiteOpenHelper {
    public NotesSQLiteOpenHelper(Context context) {
        super(context, "notes", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE mynotes (_id INTEGER PRIMARY KEY ASC AUTOINCREMENT, " +
                "title TEXT NOT NULL, content TEXT, time STRING NOT NULL);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
