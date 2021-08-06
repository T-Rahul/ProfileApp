package com.example.profileapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserInterface {
    @GET("cast")
    Call<List<DataClass>> UserData();
}
