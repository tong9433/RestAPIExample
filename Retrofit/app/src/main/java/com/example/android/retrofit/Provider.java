package com.example.android.retrofit;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by Seo on 2017-01-23.
 */

public class Provider extends ContentProvider {
    public static final String AUTHORITY = "com.example.android.retrofit";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    static final String[] sColumns=new String[]{"id","name","full_name"};




    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        Realm mRealm =Realm.getDefaultInstance();
        RealmQuery<Repo> query=mRealm.where(Repo.class);
        RealmResults<Repo> myRepo=query.findAll();

        MatrixCursor matrixCursor=new MatrixCursor(sColumns);
        for(Repo repo: myRepo ) {
            String[] rowData = new String[]{repo.id, repo.name, repo.full_name};
            matrixCursor.addRow(rowData);
        }

        Log.e("TAG",""+matrixCursor.getCount());

        mRealm.close();
        return matrixCursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
