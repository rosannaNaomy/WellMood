package com.portillo.naomyportillo.wellmood.database;

import android.content.ContentValues;
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
    private static final String COL_ID = "ID";
    private static final String COL_DATE = "DATE";
    private static final String COL_DAY_DESCRIPTION = "DAY_DESCRIPTION";
    private static final String COL_DAY_THOUGHT = "DAY_THOUGHT";
    private static final String COL_MOOD = "MOOD";
    private static final String COL_CAUSE = "CAUSE";

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
                "create table " + TABLE_NAME + " (COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "COL_DATE DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                        "COL_DAY_DESCRIPTION TEXT, COL_DAY_THOUGHT  TEXT, " +
                        "COL_MOOD TEXT, COL_CAUSE TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
//        onCreate(db);
    }

    public void addLog(DayLogModel dayLog) {
        Cursor cursor = getReadableDatabase().rawQuery(
                "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_DATE + " = '" + getDateTime()
                        + "' AND " + COL_DAY_DESCRIPTION + " = '" + dayLog.getDayDescription()
                        + "' AND  " + COL_DAY_THOUGHT + " = '" + dayLog.getThoughts()
                        + "' AND " + COL_MOOD + " = '" + dayLog.getMood()
                        + "' AND " + COL_CAUSE + " = '" + dayLog.getCause() + "';", null);

        if (cursor.getCount() != 0 || cursor.getCount() == 0) {
            getWritableDatabase().execSQL("INSERT INTO " + TABLE_NAME + "(COL_DATE, COL_DAY_DESCRIPTION, COL_DAY_THOUGHT, COL_MOOD, COL_CAUSE)" +
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
                    DayLogModel logModel = new DayLogModel(
                            cursor.getString(cursor.getColumnIndex(COL_DATE)),
                            cursor.getString(cursor.getColumnIndex(COL_DAY_DESCRIPTION)),
                            cursor.getString(cursor.getColumnIndex(COL_DAY_THOUGHT)),
                            cursor.getString(cursor.getColumnIndex(COL_MOOD)),
                            cursor.getString(cursor.getColumnIndex(COL_CAUSE)));
                    logList.add(logModel);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return logList;
    }

//    public void updateData(DayLogModel dayLogModel) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_ID, dayLogModel.getId());
//        contentValues.put(COL_DATE, dayLogModel.getDate());
//        contentValues.put(COL_DAY_DESCRIPTION, dayLogModel.getDayDescription());
//        contentValues.put(COL_DAY_THOUGHT, dayLogModel.getThoughts());
//        contentValues.put(COL_MOOD, dayLogModel.getMood());
//        contentValues.put(COL_CAUSE, dayLogModel.getCause());
//        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{dayLogModel.getId()});
//    }

    public void updateLog(String updatedCause, int id, String cause) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "UPDATE " + TABLE_NAME + " SET " + COL_CAUSE + " = '" +updatedCause + "' WHERE " + COL_ID + " = '" + id + " ' " +
                " AND " + COL_CAUSE + " = '" + cause + " ' ";
        db.execSQL(query);
    }

    public Cursor getItemID(String date ) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL_ID + " FROM " + TABLE_NAME +
                " WHERE " + COL_DATE + " = '" + date + " ' ";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void clearLogList() {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.execSQL("delete from " + TABLE_NAME);
        db.close();

    }

}
