package com.example.stavroula.simpleparse;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.stavroula.simpleparse.adapter.RecyclerViewAdapter;
import com.example.stavroula.simpleparse.controller.ApiService;
import com.example.stavroula.simpleparse.entity.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.lang.String.valueOf;


public class MainActivity extends AppCompatActivity {

    private LinearLayoutManager layoutManager;
    List<User> userList = null;

    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("123", "onCreate");

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        Log.d("123", "RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler);");

        layoutManager = new LinearLayoutManager(MainActivity.this);
        Log.d("123", "layoutManager = new LinearLayoutManager(MainActivity.this);");

        recyclerView.setLayoutManager(layoutManager);
        Log.d("123", "recyclerView.setLayoutManager(layoutManager);");
        recyclerViewAdapter = new RecyclerViewAdapter(getApplicationContext(), userList);
        Log.d("123", "RecyclerViewAdapter recyclerViewAdapter =new RecyclerViewAdapter(getApplicationContext(), userList);");
        recyclerView.setAdapter(recyclerViewAdapter);
        Log.d("123", "recyclerView.setAdapter(recyclerViewAdapter);");


        getUserList();

    }

    private void getUserList() {
        Log.d("123", "getUserList");
        try {

            String url = "http://192.168.1.5:8080/";
            Log.d("123", "http://localhost/");
            Retrofit retrofit = null;
            Log.d("123", "retrofit");

            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Log.d("123", "build();");
            }


            ApiService apiService = retrofit.create(ApiService.class);
            Log.d("123", " APIService service = retrofit.create(APIService.class);");

            Call<List<User>> call = apiService.getAllUsers();
            Log.d("123", "Call<List<User>> call = service.getUserData();");

            call.enqueue(new Callback<List<User>>() {
                @Override
                public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                    Log.d("123", "onResponse");

                    userList = response.body();
                    Log.d("123",valueOf(userList.size()));

                    Log.d("123", "userList");
                    Log.d("123",userList.toString());
                    Log.d("123", "onResponseBody");

                    Log.d("123","new RecyclerViewAdapter(getApplicationContext(), userList);");
                    Log.d("123", valueOf(recyclerViewAdapter));

                    recyclerViewAdapter.setUserList(userList);
                    recyclerViewAdapter.notifyDataSetChanged();
                    Log.d("123", String.valueOf(recyclerViewAdapter.getItemCount()));
                    recyclerView.setAdapter(recyclerViewAdapter);
                    Log.d("123", "recyclerView.setAdapter(recyclerViewAdapter);");

                }

                @Override
                public void onFailure(Call<List<User>> call, Throwable t) {
                    Log.d("123", t.getMessage());
                    Toast.makeText(MainActivity.this, "something went wrong", Toast.LENGTH_LONG).show();

                }
            });
        }catch (Exception e) {Log.d("123", "Exception");}
    }
}