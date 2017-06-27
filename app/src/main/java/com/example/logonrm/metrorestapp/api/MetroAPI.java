package com.example.logonrm.metrorestapp.api;


import com.example.logonrm.metrorestapp.model.Linha;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MetroAPI {

    //fazer a requisição e trazer os dados para a aplicação
    //Não chama o response, só devolve a lista
    @GET("/linhas")
    Call<List<Linha>> getLinhas();

}
