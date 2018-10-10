package com.example.stavroula.simpleparse.controller;

import com.example.stavroula.simpleparse.entity.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {


    @GET("user")
    Call<List<User>> getAllUsers();
}
