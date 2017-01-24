package com.example.android.retrofit;

import io.realm.RealmObject;

/**
 * Created by Seo on 2017-01-23.
 */
public class Owner extends RealmObject {
    String login;
    String id;
    String avartar_url;
    String gravatar_id;
    String url;
    String html_url;
    String followers_url;
    String gists_url;
    String starred_url;
    String subscriptions_url;
    String organizations_url;
    String repos_url;
    String events_url;
    String received_events_url;
    String type;
    boolean site_admin;

}
