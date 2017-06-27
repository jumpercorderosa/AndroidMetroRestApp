package com.example.logonrm.metrorestapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.SortedList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.logonrm.metrorestapp.adapter.MetroAdapter;
import com.example.logonrm.metrorestapp.adapter.OnItemClickListener;
import com.example.logonrm.metrorestapp.api.APIUtils;
import com.example.logonrm.metrorestapp.api.MetroAPI;
import com.example.logonrm.metrorestapp.model.Linha;
import com.example.logonrm.metrorestapp.model.ResponseAndroid;

import com.example.logonrm.metrorestapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvLinhas;
    private MetroAdapter metroAdapter;
    private MetroAPI metroAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvLinhas = (RecyclerView) findViewById(R.id.rvLinhas);

        metroAdapter = new MetroAdapter(new ArrayList<Linha>(),
                new OnItemClickListener() {

            public void onItemClick(Linha item) {
                Toast.makeText(getApplicationContext(),
                        item.getCor(), Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvLinhas.setLayoutManager(layoutManager);
        rvLinhas.setAdapter(metroAdapter);
        rvLinhas.setHasFixedSize(true);

        carregaDados();

    }

    private void carregaDados() {
        metroAPI = APIUtils.getMetroAPILinha();

        metroAPI.getLinhas().enqueue(new Callback<List<Linha>>() {

            @Override
            public void onResponse(Call<List<Linha>> call, Response<List<Linha>> response) {
                if(response.isSuccessful()) {
                    metroAdapter.update(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Linha>> call, Throwable t) {

            }
        });

    }

}
