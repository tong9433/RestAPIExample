package com.example.android.retrofit;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class Myapplication extends Application {

    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

}
