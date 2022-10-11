package com.example.apidestrozasuenyos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.apidestrozasuenyos.utility.AsyncTaskRunnerApi;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Buscador#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Buscador extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Buscador() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Buscador.
     */
    // TODO: Rename and change types and number of parameters
    public static Buscador newInstance(String param1, String param2) {
        Buscador fragment = new Buscador();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_buscador, container, false);

        Button buscar = (Button) view.findViewById(R.id.bBuscar);

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pais = "";
                String universidad = "";

                EditText etPais = (EditText)view.findViewById(R.id.etPais);
                EditText etUni = (EditText)view.findViewById(R.id.etUni);

                if(etPais != null){
                    pais = etPais.getText().toString();
                }

                if(etUni != null){
                    universidad = etUni.getText().toString();
                }

                AsyncTaskRunnerApi hiloApi = new AsyncTaskRunnerApi((MainActivity) view.getContext(), pais, universidad);

                hiloApi.execute();

                // Create new fragment and transaction
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);

                // Replace whatever is in the fragment_container view with this fragment
                transaction.replace(R.id.fcvGeneral, ContainerUniversirares.class, null);

                // Commit the transaction
                transaction.commit();

            }
        });

        return view;
    }
}