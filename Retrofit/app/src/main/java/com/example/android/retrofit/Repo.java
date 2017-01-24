package com.example.android.retrofit;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Seo on 2017-01-23.
 */
public class Repo extends RealmObject {
   @PrimaryKey
    String id;

    String name;
    String full_name;
    Owner owner;

}
