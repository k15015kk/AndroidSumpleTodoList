package com.example.kyotonagoya.todolist.TodoDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class TodoDatabaseAdapter {
    SQLiteDatabase db;
    TodoDatabaseOpenHelper todoDatabase;

    public TodoDatabaseAdapter(Context mContext) {
        todoDatabase = new TodoDatabaseOpenHelper(mContext);
        db = todoDatabase.getWritableDatabase();
    }

    public ArrayList<String> selectTaskAll(){
        Cursor cursor = db.rawQuery("select * from " + TodoDatabaseOpenHelper.tableName,null);

        ArrayList<String> result = new ArrayList<String>();

        try {
            if(cursor.moveToFirst()) {
                do {
                    result.add(cursor.getString(cursor.getColumnIndex("task")));
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        return result;
    }

    public void addRecord(String task) {
        ContentValues values = new ContentValues();

        values.put("task",task);
        db.insertOrThrow(TodoDatabaseOpenHelper.tableName,null,values);
    }

    public void deleteRecord(String data) {
        String[] deleteWhere = {data};

        db.delete(TodoDatabaseOpenHelper.tableName,"task=?",deleteWhere);
    }
}
