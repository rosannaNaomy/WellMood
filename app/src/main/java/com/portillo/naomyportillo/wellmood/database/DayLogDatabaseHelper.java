package com.portillo.naomyportillo.wellmood.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.portillo.naomyportillo.wellmood.model.DayLogModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DayLogDatabaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "dayLog.db";
    private static final String TABLE_NAME = "log";
    private static final int SCHEMA_VERSION = 1;

    private static DayLogDatabaseHelper singletonInstance;

    private DayLogDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
    }

    public static synchronized DayLogDatabaseHelper getInstance(Context context) {
        if (singletonInstance == null) {
            singletonInstance = new DayLogDatabaseHelper(context.getApplicationContext());
        }
        return singletonInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + TABLE_NAME +
                        "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "date_created DATETIME DEFAULT CURRENT_TIMESTAMP, dayDescription TEXT, dayThought TEXT, mood TEXT, cause TEXT);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addLog(DayLogModel dayLog) {
        Cursor cursor = getReadableDatabase().rawQuery(
                "SELECT * FROM " + TABLE_NAME + " WHERE date_created = '" + getDateTime()
                        + "' AND dayDescription = '" + dayLog.getDayDescription()
                        + "' AND  dayThought = '" + dayLog.getThoughts()
                        + "' AND mood = '" + dayLog.getMood()
                        + "' AND cause = '" + dayLog.getCause() + "';", null);

        if (cursor.getCount() != 0 || cursor.getCount() == 0) {
            getWritableDatabase().execSQL("INSERT INTO " + TABLE_NAME + "(date_created, dayDescription, dayThought) " +
                    "VALUES ('" + getDateTime() + "', '" + dayLog.getDayDescription() + "', '" + dayLog.getThoughts() + "', '" + dayLog.getMood() + "', '" + dayLog.getCause() + "');");
        }

        cursor.close();
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    public List<DayLogModel> getLogList() {
        List<DayLogModel> logList = new ArrayList<>();

        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_NAME + ";", null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    DayLogModel logModel = new DayLogModel(cursor.getString(cursor.getColumnIndex("date_created")),
                            cursor.getString(cursor.getColumnIndex("dayDescription")),
                            cursor.getString(cursor.getColumnIndex("dayThought")),
                            cursor.getString(cursor.getColumnIndex("mood")),
                            cursor.getString(cursor.getColumnIndex("cause")));
                    logList.add(logModel);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return logList;
    }


    public void clearLogList() {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.execSQL("delete from " + TABLE_NAME);
        db.close();

    }


}
