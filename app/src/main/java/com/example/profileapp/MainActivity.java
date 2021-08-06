package com.example.profileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.ToggleButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ToggleButton toggleButton;
    List<DataClass> usersInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        toggleButton = findViewById(R.id.tog);
        toggleButton.setChecked(false);
        usersInfo = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.tvmaze.com/shows/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserInterface userInterface = retrofit.create(UserInterface.class);

        Call<List<DataClass>> call = userInterface.UserData();

        call.enqueue(new Callback<List<DataClass>>() {
            @Override
            public void onResponse(Call<List<DataClass>> call, Response<List<DataClass>> response) {
                List<DataClass> dataClass = response.body();
                for(DataClass dataClasses: dataClass){

//                    if(!dataClasses.getPerson().getName().equals(null) && !dataClasses.getPerson().getBirthday().equals(null) && dataClasses.getPerson().getImage().getOriginal().equals(null)){
                        usersInfo.add(dataClasses);
//                    }else continue;




                }

                DataIntoRecyclerViewLinear(usersInfo);
                toggleButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(toggleButton.isChecked()){
                            DataIntoRecyclerViewGrid(usersInfo);

                        }else{
                            DataIntoRecyclerViewLinear(usersInfo);
                        }
                    }
                });

            }

            @Override
            public void onFailure(Call<List<DataClass>> call, Throwable t) {

            }
        });

//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navig);
//        bottomNavigationView.setSelectedItemId(R.id.HomeNav);

//        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                return false;
//            }
//        });


    }

    private void DataIntoRecyclerViewGrid(List<DataClass> data) {
        AdapterGrid adapterGrid = new AdapterGrid(this, data);
        RecyclerView.LayoutManager mLayout = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayout);
        recyclerView.setAdapter(adapterGrid);


    }
    private void DataIntoRecyclerViewLinear(List<DataClass> data){
        AdapterLinear adapterLinear = new AdapterLinear(this, data);
        RecyclerView.LayoutManager mLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayout);
        recyclerView.setAdapter(adapterLinear);
    }
}