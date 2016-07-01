package com.example.parcel;

import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "ma_log";
    Parcel p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        writeParcel();
        readParcel();
    }

    private void readParcel() {

        readLog("Before reading");
        p.setDataPosition(0);
        readLog("byte: " + p.readByte());
        readLog("int: " + p.readInt());
        readLog("long: " + p.readLong());
        readLog("float: " + p.readFloat());
        readLog("double: " + p.readDouble());
        readLog("String: " + p.readString());
    }

    void readLog(String txt) {
        Log.d(LOG_TAG, txt + " position: " + p.dataPosition());
    }

    public void writeParcel(){

        p = Parcel.obtain();
        byte b = 1;
        p.writeByte(b);
        writeLog("byte");
        int i = 2;
        p.writeInt(i);
        writeLog("int");
        long l = 3;
        p.writeLong(l);
        writeLog("long");
        float f = 4;
        p.writeFloat(f);
        writeLog("float");
        double d = 5;
        p.writeDouble(d);
        writeLog("double");
        String s = "abcdef";
        p.writeString(s);
        writeLog("String");

    }

    public void writeLog(String txt) {
        Log.d(LOG_TAG, "write " + txt + " Size: " +p.dataSize());
    }
}
