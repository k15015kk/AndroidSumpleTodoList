package com.example.kyotonagoya.todolist.TodoDatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TodoDatabaseOpenHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    public static String tableName = "todoList";

    public TodoDatabaseOpenHelper(Context context) {
        super(context,"todo.db",null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + tableName + "(" +
                "id integer primary key autoincrement," +
                "task varchar(50) not null" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
