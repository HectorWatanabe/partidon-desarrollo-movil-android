package pe.edu.upc.partidon.datasource;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.GsonBuilder;

import java.util.List;

import pe.edu.upc.partidon.datasource.network.PartidonService;
import pe.edu.upc.partidon.models.CourtSection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Hector on 18/06/2017.
 */

public class CourtSectionRepository {

    private final PartidonService service;
    private Context context;

    public CourtSectionRepository(Context context) {
        this.context = context;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.partidon.pe.hu/api/v1/")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .build();

        service = retrofit.create(PartidonService.class);
    }


    public interface CourtSectionCallback{
        void onComplete(List<CourtSection> courts);
        void onError(String message);
    }

    public void getCourtSection(final int id,final CourtSectionRepository.CourtSectionCallback callback){
        SharedPreferences references = getDefaultSharedPreferences();
        service.getCourtSection(id,references.getString("api_token",null)).enqueue(new Callback<List<CourtSection>>() {
            @Override
            public void onResponse(Call<List<CourtSection>> call, Response<List<CourtSection>> response) {



                List<CourtSection> courtSection = response.body();
                callback.onComplete(courtSection);
            }

            @Override
            public void onFailure(Call<List<CourtSection>> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }


    private SharedPreferences getDefaultSharedPreferences(){
        return context.getSharedPreferences("PARTIDON",Context.MODE_PRIVATE);
    }

}
