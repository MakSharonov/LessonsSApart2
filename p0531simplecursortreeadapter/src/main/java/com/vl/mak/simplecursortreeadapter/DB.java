package com.vl.mak.simplecursortreeadapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mak on 16.06.2016.
 */
public class DB {

    private static final String DB_NAME = "mydb";
    private static final int DB_VERSION = 1;

    private static final String COMPANY_TABLE = "company";
    public static final String COMPANY_COLUMN_ID = "_id";
    public static final String COMPANY_COLUMN_NAME = "name";
    private static final String COMPANY_TABLE_CREATE = "create table " +
            COMPANY_TABLE + "(" + COMPANY_COLUMN_ID
            + " integer primary key, " + COMPANY_COLUMN_NAME + " text" + ");";

    private static final String PHONE_TABLE = "phone";
    public static final String PHONE_COLUMN_ID = "_id";
    public static final String PHONE_COLUMN_NAME = "name";
    public static final String PHONE_COLUMN_COMPANY = "company";
    private static final String PHONE_TABLE_CREATE = "create table "
            + PHONE_TABLE + "(" + PHONE_COLUMN_ID
            + " integer primary key autoincrement, " + PHONE_COLUMN_NAME
            + " text, " + PHONE_COLUMN_COMPANY + " integer" + ");";

    public final Context myCtx;

    private SQLiteDatabase mDB;
    private DBHelper mDBHelper;

    public DB(Context ctx) {
        myCtx = ctx;
    }

    public void open() {
        mDBHelper = new DBHelper(myCtx, DB_NAME, null, DB_VERSION);
        mDB = mDBHelper.getWritableDatabase();
    }

    public void close() {
        if (mDBHelper!=null) {
            mDBHelper.close();
        }
    }

    public Cursor getCompanyData() {
        return mDB.query(COMPANY_TABLE, null, null, null, null, null, null);
    }

    public Cursor getPhoneData(long companyID) {
        return  mDB.query(PHONE_TABLE, null, PHONE_COLUMN_COMPANY + " = " + companyID, null,
                null, null, null);
    }

    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            ContentValues cv = new ContentValues();

            String[] companies = new String[] { "HTC", "Samsung", "LG" };

            db.execSQL(COMPANY_TABLE_CREATE);
            for (int i=0; i<companies.length; i++) {
                cv.put(COMPANY_COLUMN_ID, i);
                cv.put(COMPANY_COLUMN_NAME, companies[i]);
                db.insert(COMPANY_TABLE, null, cv);
            }

            String[] phonesHTC = new String[] { "Sensation", "Desire",
                    "Wildfire", "Hero" };
            String[] phonesSams = new String[] { "Galaxy S II", "Galaxy Nexus",
                    "Wave" };
            String[] phonesLG = new String[] { "Optimus", "Optimus Link",
                    "Optimus Black", "Optimus One" };

            db.execSQL(PHONE_TABLE_CREATE);
            cv.clear();
            for (int i = 0; i < phonesHTC.length; i++) {
                cv.put(PHONE_COLUMN_COMPANY, 0);
                cv.put(PHONE_COLUMN_NAME, phonesHTC[i]);
                db.insert(PHONE_TABLE, null, cv);
            }
            for (int i = 0; i < phonesSams.length; i++) {
                cv.put(PHONE_COLUMN_COMPANY, 1);
                cv.put(PHONE_COLUMN_NAME, phonesSams[i]);
                db.insert(PHONE_TABLE, null, cv);
            }
            for (int i = 0; i < phonesLG.length; i++) {
                cv.put(PHONE_COLUMN_COMPANY, 2);
                cv.put(PHONE_COLUMN_NAME, phonesLG[i]);
                db.insert(PHONE_TABLE, null, cv);
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}