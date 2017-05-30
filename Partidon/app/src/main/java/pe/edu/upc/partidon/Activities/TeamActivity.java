package pe.edu.upc.partidon.Activities;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upc.partidon.Adapters.NewsFeedAdapter;
import pe.edu.upc.partidon.R;
import pe.edu.upc.partidon.datasource.UserRepository;
import pe.edu.upc.partidon.fragments.UserFragment;
import pe.edu.upc.partidon.models.NewsComments;
import pe.edu.upc.partidon.models.User;

public class TeamActivity extends AppCompatActivity {


    private static final String TAG = "TeamActivity";
    private RecyclerView teamWallRecyclerView;


    public TeamActivity() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static TeamActivity newInstance() {
        TeamActivity fragment = new TeamActivity();
        return fragment;
    }


    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);



        teamWallRecyclerView = (RecyclerView) findViewById(R.id.teamWallRecyclerView);
        teamWallRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        teamWallRecyclerView.setAdapter(new NewsFeedAdapter(this,getUserComment()));


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if( id == android.R.id.home){
            finish();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }




    private List<NewsComments> getUserComment(){
        List<NewsComments> results = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            NewsComments newsComments = new NewsComments();
            newsComments.setNameUser("Maria Fernanda Segovia Chacón " + i);
            newsComments.setComment("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut " +
                    "labore et dolore magna aliqua. Un ullamco laboris nisi ut aliquip ex ea commodo consequat. " + i);
            results.add(newsComments);
        }
        return results;
    }



}
