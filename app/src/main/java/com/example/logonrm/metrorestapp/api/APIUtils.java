package com.example.logonrm.metrorestapp.api;


//acessa o AndroidAPI
public class APIUtils {

    public static final String BASE_URL = "13.3.1.2:3000";

    public static MetroAPI getMetroAPILinha() {
        return RetrofitClient.getClient(BASE_URL).create(MetroAPI.class);
    }
}
