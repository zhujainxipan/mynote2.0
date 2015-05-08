package com.ht.mynote.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.ht.mynote.pojo.Notes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * Project: com.ht.mynote.dao
 * Author: 安诺爱成长
 * Email: 1399487511@qq.com
 * Date: 2015/5/4
 */
public class DbManager {
    private static DbManager dbManager;
    private static NotesSQLiteOpenHelper notesSQLiteOpenHelper;

    public static void createInstance(Context context) {
        if (dbManager == null) {
            notesSQLiteOpenHelper =
                    new NotesSQLiteOpenHelper(context);
            dbManager = new DbManager();
        }
    }

    public static DbManager getInstance() {
        if (dbManager == null) {
            throw new IllegalStateException("必须首先创建");
        }
        return dbManager;
    }

    private DbManager() {

    }

    //往数据库中添加一条记录
    public boolean add(Notes notes) {
        SQLiteDatabase db = notesSQLiteOpenHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.beginTransaction();
            ContentValues values = new ContentValues();
            values.put("title", notes.getTitle());
            values.put("content", notes.getContent());
            values.put("time", notes.getTime());
            long rid = db.insert("mynotes", null, values);
            db.setTransactionSuccessful();
            db.endTransaction();
            db.close();
            return true;
        }
        else
            return false;
    }


    //更新数据库中的一条记录
    public boolean update(Notes notes) {
        SQLiteDatabase db = notesSQLiteOpenHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.beginTransaction();
            ContentValues values = new ContentValues();
            values.put("title", notes.getTitle());
            values.put("content", notes.getContent());
            values.put("time", notes.getTime());
            long rid = db.update("mynotes", values, "_id = ?", new String[]{notes.getId() + ""});
            db.setTransactionSuccessful();
            db.endTransaction();
            db.close();
            return true;
        }
        else
            return false;
    }




    //删除数据库中的一条记录
    public Boolean delete(Notes notes) {
        SQLiteDatabase db = notesSQLiteOpenHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.beginTransaction();
            long rid = db.delete("mynotes", "_id = ?", new String[]{notes.getId() + ""});
            db.setTransactionSuccessful();
            db.endTransaction();
            db.close();
            return true;
        }
        else
            return false;
    }

    //得到对象的集合
    public List<Notes> getList() {
        SQLiteDatabase db = notesSQLiteOpenHelper.getWritableDatabase();
        List<Notes> list = new ArrayList<>();
        Cursor cursor = db.query("mynotes", null, null, null, null, null, "time desc");
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Notes notes = new Notes();
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String content = cursor.getString(2);
                String time = cursor.getString(3);
                notes.setId(id);
                notes.setTitle(title);
                notes.setContent(content);
                notes.setTime(time);
                list.add(notes);
            }
            cursor.close();
            db.close();
        }
        return list;
    }

}
