package com.vl.mak.xmlpullparser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String tmp;

        try {
            //XmlPullParser xpp = prepareXpp();
            XmlPullParser xpp = prepareXppNotFromFile();

            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                switch (xpp.getEventType()){
                    case XmlPullParser.START_DOCUMENT:
                        writeLog("START_DOCUMENT");
                        break;
                    case XmlPullParser.START_TAG:
                        writeLog("START name: " + xpp.getName() + " depth: " + xpp.getDepth()
                        + " attrCount: " + xpp.getAttributeCount());
                        tmp = "";
                        for (int i=0; i<xpp.getAttributeCount(); i++) {
                            tmp += xpp.getAttributeName(i) + " = " + xpp.getAttributeValue(i) + ", ";
                        }
                        if(!TextUtils.isEmpty(tmp))
                            writeLog("Attributes: " + tmp);
                        break;
                    case XmlPullParser.END_TAG:
                        writeLog("END " + xpp.getName());
                        break;
                    case XmlPullParser.TEXT:
                        writeLog("TEXT: " + xpp.getText());
                        break;
                    default:
                        break;
                }
                xpp.next();
            }
        }
        catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    XmlPullParser prepareXpp() {
        return getResources().getXml(R.xml.data);
    }

    XmlPullParser prepareXppNotFromFile() throws XmlPullParserException {
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();
        xpp.setInput(new StringReader(
                "<data><phone><company>Samsung</company></phone></data>"));
        return xpp;
    }

    void writeLog(String str) {
        Log.d(LOG_TAG, str);
    }


}
