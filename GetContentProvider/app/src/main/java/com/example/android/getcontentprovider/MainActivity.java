package com.example.android.getcontentprovider;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    public static final String AUTHORITY ="com.example.android.retrofit";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ContentResolver cr = getContentResolver();
        Log.e("TAG", cr.toString());
        Cursor cursor = cr.query(CONTENT_URI,null,null,null,null);
        while(cursor.moveToNext()) {
            Log.e("TAG", "" + cursor.getString(cursor.getColumnIndex("id")));
        }
    }
}
