package com.vl.mak.contentproviderclient;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends Activity {

    static final String LOG_TAG = "myLogs";

    final Uri CONTACTS_URI = Uri.parse("content://com.vl.mak.providers.AddressBook/contacts");

    final String CONTACT_NAME = "name";
    final String CONTACT_EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lvContacts = findViewById(R.id.lvContacts);
        Cursor cursor = getContentResolver().query(CONTACTS_URI, null, null, null, null);
        startManagingCursor(cursor);

        String from[] = {CONTACT_NAME, CONTACT_EMAIL};
        int to[] = {android.R.id.text1, android.R.id.text2};
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2, cursor, from, to);
        lvContacts.setAdapter(simpleCursorAdapter);
    }

    public void onClickInsert(View v) {
        ContentValues cv = new ContentValues();
        cv.put(CONTACT_NAME, "mak");
        cv.put(CONTACT_EMAIL, "mak@germak.ru");
        Uri resultUri = getContentResolver().insert(CONTACTS_URI, cv);
        Log.d(LOG_TAG, "insert, resultUri=" + resultUri.toString());
    }

    public void onClickUpdate(View v) {
        ContentValues cv = new ContentValues();
        cv.put(CONTACT_NAME, "lolo");
        cv.put(CONTACT_EMAIL, "lolo@lo.ru");
        Uri uri = ContentUris.withAppendedId(CONTACTS_URI, 2);
        int cnt = getContentResolver().update(uri, cv, null, null);
        Log.d(LOG_TAG, "updated " + cnt + " row(s)");
    }

    public void onClickDelete(View v) {
        Uri uri = ContentUris.withAppendedId(CONTACTS_URI, 3);
        int cnt = getContentResolver().delete(uri, null, null);
        Log.d(LOG_TAG, "deleted " + cnt + " row(s)");
    }

    public void onClickError(View v) {
        Uri uri = Uri.parse("content://com.vl.mak.providers.AddressBook/people");
        try {
            getContentResolver().query(uri, null, null, null, null);
        }
        catch (IllegalArgumentException e) {
            Log.d(LOG_TAG, "illegal uri: " + uri.toString());
        }
    }
}
