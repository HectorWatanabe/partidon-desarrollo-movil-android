package pe.edu.upc.partidon.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pe.edu.upc.partidon.R;

public class freeFragment extends Fragment {



    public freeFragment() {
        // Required empty public constructor
    }


    public static freeFragment newInstance() {
        return new freeFragment();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_free, container, false);
    }


}
