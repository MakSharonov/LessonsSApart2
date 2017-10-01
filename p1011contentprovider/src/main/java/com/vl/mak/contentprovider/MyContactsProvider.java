package com.vl.mak.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import java.util.IllegalFormatCodePointException;

/**
 * Created by Mak on 28.09.2017.
 */

public class MyContactsProvider extends ContentProvider {

    final String LOG_TAG = "myLogs";

    static final String DB_NAME = "mydb";
    static final int DB_VERSION = 1;

    static final String CONTACT_TABLE = "contacts";

    static final String CONTACT_ID = "_id";
    static final String CONTACT_NAME = "name";
    static final String CONTACT_EMAIL = "email";

    static final String DB_CREATE = "create table " + CONTACT_TABLE + "("
            + CONTACT_ID + " integer primary key autoincrement, "
            + CONTACT_NAME + " text, "
            + CONTACT_EMAIL + " text);";

    static final String AUTHORITY = "com.vl.mak.providers.AddressBook";

    static final String CONTACTS_PATH = "contacts";

    public static Uri CONTACT_CONTENT_URI = Uri.parse("content://" + AUTHORITY
    + "/" + CONTACTS_PATH);

    static final int URI_CONTACTS = 1;
    static final int URI_CONTACTS_ID = 2;

    // Типы данных
    // набор строк
    static final String CONTACT_CONTENT_TYPE = "vnd.android.cursor.dir/vnd." +
            AUTHORITY + "." + CONTACTS_PATH;
    // одна строка
    static final String CONTACT_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd." +
            AUTHORITY + "." + CONTACTS_PATH;

    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, CONTACTS_PATH, URI_CONTACTS);
        uriMatcher.addURI(AUTHORITY, CONTACTS_PATH + "/#", URI_CONTACTS_ID);
    }

    DBHelper dbHelper;
    SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        Log.d(LOG_TAG, "onCreate");
        dbHelper = new DBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Log.d(LOG_TAG, "query " + uri.toString());
        switch (uriMatcher.match(uri)) {
            case URI_CONTACTS:
                Log.d(LOG_TAG, "URI_CONTACTS");
                // make default sort if not made
                if(TextUtils.isEmpty(sortOrder)) {
                    sortOrder = CONTACT_NAME + " ASC";
                }
                break;
            case URI_CONTACTS_ID:
                String id = uri.getLastPathSegment();
                Log.d(LOG_TAG, "URI_CONTACTS_ID" + id);
                if (TextUtils.isEmpty(selection)) {
                    selection = CONTACT_ID + "=" + id;
                } else {
                    selection += " AND " + CONTACT_ID + "=" + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI" + uri);
        }
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(CONTACT_TABLE, projection, selection, selectionArgs, null, null, sortOrder);
        // просим ContentResolver уведомлять этот курсор
        // об изменениях данных в CONTACT_CONTENT_URI
        cursor.setNotificationUri(getContext().getContentResolver(), CONTACT_CONTENT_URI);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        Log.d(LOG_TAG, "getType " + uri.toString());
        switch (uriMatcher.match(uri)) {
            case URI_CONTACTS:
                return CONTACT_CONTENT_TYPE;
            case URI_CONTACTS_ID:
                return CONTACT_CONTENT_ITEM_TYPE;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        Log.d(LOG_TAG, "insert " + uri.toString());
        if (uriMatcher.match(uri)!=URI_CONTACTS) {
            throw new IllegalArgumentException("Wrong uri: " + uri.toString());
        }
        db = dbHelper.getWritableDatabase();
        long rowID = db.insert(CONTACT_TABLE, null, contentValues);
        Uri resultID = ContentUris.withAppendedId(CONTACT_CONTENT_URI, rowID);
        // уведомляем ContentResolver, что данные по адресу resultID изменились
        getContext().getContentResolver().notifyChange(resultID, null);
        return resultID;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d(LOG_TAG, "delete " + uri.toString());
        switch (uriMatcher.match(uri)) {
            case URI_CONTACTS:
                Log.d(LOG_TAG, "URI_CONTACTS");
                break;
            case URI_CONTACTS_ID:
                String id  = uri.getLastPathSegment();
                Log.d(LOG_TAG, "URI_CONTACTS_ID " + id);
                if (TextUtils.isEmpty(selection)) {
                    selection = CONTACT_ID  + "=" + id;
                } else {
                    selection += " AND "  + CONTACT_ID  + "=" + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong uri: " + uri.toString());
        }
        db = dbHelper.getWritableDatabase();
        int cnt = db.delete(CONTACT_TABLE, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return cnt;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d(LOG_TAG, "update " + uri);
        switch (uriMatcher.match(uri)) {
            case URI_CONTACTS:
                Log.d(LOG_TAG, "URI_CONTACTS");
                break;
            case URI_CONTACTS_ID:
                String id = uri.getLastPathSegment();
                Log.d(LOG_TAG, "URI_CONTACTS_ID" + id);
                if (TextUtils.isEmpty(selection)) {
                    selection = CONTACT_ID + "=" + id;
                } else {
                    selection += " AND " + CONTACT_ID + "=" + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong uri: " + uri.toString());
        }
        db = dbHelper.getWritableDatabase();
        int cnt = db.update(CONTACT_TABLE, contentValues, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return cnt;
    }

    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(DB_CREATE);
            ContentValues cv = new ContentValues();
            for (int i=0; i<3; i++) {
                cv.put(CONTACT_NAME, "name" + i);
                cv.put(CONTACT_EMAIL, "email" + i);
                sqLiteDatabase.insert(CONTACT_TABLE,null,cv);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}
