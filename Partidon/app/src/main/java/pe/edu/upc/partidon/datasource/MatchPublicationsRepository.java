package pe.edu.upc.partidon.datasource;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.GsonBuilder;

import java.util.List;

import pe.edu.upc.partidon.datasource.network.PartidonService;
import pe.edu.upc.partidon.models.NewsComments;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Hector on 01/07/2017.
 */

public class MatchPublicationsRepository {

    private final PartidonService service;
    private Context context;

    public MatchPublicationsRepository(Context context){
        this.context = context;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.partidon.pe.hu/api/v1/")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .build();

        service = retrofit.create(PartidonService.class);
    }


    public interface MatchPublicationsCallback{
        void onComplete(List<NewsComments> publications);
        void onError(String message);
    }

    public void getMatchPublications(int id,final MatchPublicationsRepository.MatchPublicationsCallback callback){

        SharedPreferences references = getDefaultSharedPreferences();
        service.getMatchPublications(id,references.getString("api_token",null)).enqueue(new Callback<List<NewsComments>>() {
            @Override
            public void onResponse(Call<List<NewsComments>> call, Response<List<NewsComments>> response) {
                List<NewsComments> publications = response.body();
                callback.onComplete(publications);
            }

            @Override
            public void onFailure(Call<List<NewsComments>> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });


    }


    private SharedPreferences getDefaultSharedPreferences(){
        return context.getSharedPreferences("PARTIDON",Context.MODE_PRIVATE);
    }

}
