package com.example.android.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class MainActivity extends AppCompatActivity {

    private Realm realm;
    List<Repo> repos;
    RealmResults<Repo> myRepo;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Bind(R.id.retrofit_text) TextView retro_text;
    Retrofit retrofit=  new Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        realm=Realm.getDefaultInstance();

        GitHubService service = retrofit.create(GitHubService.class);
        Call<List<Repo>> repo = service.listRepos("tong9433");
        ButterKnife.bind(this);

        repo.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                repos = response.body();

                for(Repo repo : repos) {
                    retro_text.append(String.valueOf(repo.id) + "\n");
                    retro_text.append(String.valueOf(repo.name) + "\n");
                    retro_text.append(String.valueOf(repo.full_name) + "\n\n");
                }



                realm.beginTransaction();
                realm.copyToRealmOrUpdate(repos);
                realm.commitTransaction();
                RealmResults<Repo> myRepo = realm.where(Repo.class).findAll();
                Log.e("REAL TAG" ,""+ myRepo.size());
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {

            }
        });


       // Log.d("TAG" , myRepo.get(0).id);
    }
    public interface GitHubService  {
        @GET("users/{user}/repos")
        Call<List<Repo>> listRepos(@Path("user") String user);
    }


}
