package com.example.MyClub.views;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.MyClub.Adaptadores.ListUserAdapter;
import com.example.MyClub.controlers.UserControler;
import com.example.MyClub.interfaces.GetUsersCallback;
import com.example.MyClub.models.User;
import com.example.conectarapi.R;

import java.util.ArrayList;
import java.util.List;


public class ListUserActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    UserControler userControler = new UserControler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);

        recyclerView = findViewById(R.id.recyclerView_users);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userControler.getDirectivos(new GetUsersCallback() {
            @Override
            public void onSuccess(ArrayList<User> users) {
                ListUserAdapter listUserAdapter = new ListUserAdapter(users);
                recyclerView.setAdapter(listUserAdapter);
            }

            @Override
            public void onFailure(String errorMessage) {
                Log.d("ErrorListView",errorMessage);
                Toast.makeText(ListUserActivity.this, errorMessage, Toast.LENGTH_SHORT).show();

            }
        });



    }



}