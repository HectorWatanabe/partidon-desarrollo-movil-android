package pe.edu.upc.partidon.datasource;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.GsonBuilder;

import pe.edu.upc.partidon.datasource.network.PartidonService;
import pe.edu.upc.partidon.models.Court;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Hector on 28/06/2017.
 */

public class CourtWallRepository {

    private final PartidonService service;
    private Context context;

    public CourtWallRepository(Context context){
        this.context = context;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.partidon.pe.hu/api/v1/")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .build();

        service = retrofit.create(PartidonService.class);
    }


    public interface CourtWallCallback{
        void onComplete(Court court);
        void onError(String message);
    }

    public void getCourtWall(final int id,final CourtWallRepository.CourtWallCallback callback){
        SharedPreferences references = getDefaultSharedPreferences();
        service.getCourtWall(id,references.getString("api_token",null)).enqueue(new Callback<Court>() {
            @Override
            public void onResponse(Call<Court> call, Response<Court> response) {
                Court court = response.body();
                callback.onComplete(court);
            }

            @Override
            public void onFailure(Call<Court> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });


    }


    private SharedPreferences getDefaultSharedPreferences(){
        return context.getSharedPreferences("PARTIDON",Context.MODE_PRIVATE);
    }


}
