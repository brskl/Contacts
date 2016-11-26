package com.example.ben.contacts;

import java.util.ArrayList;
import java.util.Date;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ben on 11/25/2016.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "my.db";
    private static final int DB_VERSION = 1;
    private static DBHelper myDb = null;
    private SQLiteDatabase db;
    public static final String DB_TASK_TABLE = "Tasks";
    private static final String CreateTasksTable = "create table "
            + DB_TASK_TABLE
            + "(ID integer primary key autoincrement, Name text, Description text); ";

    private DBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);

        open();
    }

    public static DBHelper getInstance(Context context)
    {
        if (myDb == null)
        {
            myDb = new DBHelper(context);
        }

        return myDb;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CreateTasksTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + DB_TASK_TABLE);
        onCreate(db);
    }

    private void open() throws SQLiteException
    {
        db = getWritableDatabase();
    }

    public long addTask(String name, String description)
    {
        ContentValues values = new ContentValues();

        values.put("Name", name);
        values.put("Description", description);

        long rowId = db.insert(DB_TASK_TABLE, null, values);
        return rowId;
    }

    public DBTask[] getTasks()
    {
        ArrayList<DBTask> tasks = new ArrayList<DBTask>();

        Cursor cursor = db.query(DB_TASK_TABLE, null, null, null, null,
                null, null);

        if (cursor.moveToFirst()) {
            while (true)
            {
                String name, description;

                name = cursor.getString(cursor.getColumnIndex("Name"));
                description = cursor.getString(cursor.getColumnIndex("Description"));

                DBTask task = new DBTask(name, description);

                task.setId(cursor.getInt(cursor.getColumnIndex("ID")));

                tasks.add(task);
                if (!cursor.moveToNext())
                {
                    break;
                }
            }
        }

        cursor.close();

        DBTask[] dbTasks = new DBTask[tasks.size()];

        tasks.toArray(dbTasks);

        return dbTasks;
    }

    public void updateTask(long id, String name, String description)
    {
        ContentValues values = new ContentValues();

        values.put("Name", name);
        values.put("Description", description);

        db.update(DB_TASK_TABLE, values, "ID=" + id, null);
    }

    public void deleteTask(long id)
    {
        db.delete(DB_TASK_TABLE, "ID=" + id, null);
    }

    public void deleteTasks()
    {
        db.delete(DB_TASK_TABLE, null, null);
    }

}
