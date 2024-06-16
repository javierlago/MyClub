package com.example.MyClub.Views.Wall;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MyClub.Adapters.PostAdapter;
import com.example.MyClub.Controlers.PostController;
import com.example.MyClub.Interfaces.AllPostCallBack;
import com.example.MyClub.Models.Post;
import com.example.conectarapi.R;

import java.util.List;

public class ListWallActivity extends AppCompatActivity {

    PostAdapter postAdapter;
    PostController postController;

    RecyclerView recyclerView;
    Intent intent;
    String userRol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wall_activity);
        recyclerView = findViewById(R.id.recyclerView_posts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        postController = new PostController(this);
        intent = getIntent();
        userRol = intent.getStringExtra("ApiService");

        Toast.makeText(this, userRol, Toast.LENGTH_SHORT).show();
        if (!userRol.equalsIgnoreCase(getResources().getString(R.string.directivo))|| userRol==null){
            postController.getAllPosts(new AllPostCallBack() {
                @Override
                public void onSuccess(List<Post> postList) {
                    postAdapter = new PostAdapter(postList, ListWallActivity.this,userRol);
                    recyclerView.setAdapter(postAdapter);
                }

                @Override
                public void onFailure(String errorMessage) {
                    Toast.makeText(ListWallActivity.this, errorMessage, Toast.LENGTH_SHORT).show();

                }
            });

        }else{
            postController.getAllPostsWhitoutTrainig(new AllPostCallBack() {
                @Override
                public void onSuccess(List<Post> postList) {
                    postAdapter = new PostAdapter(postList, ListWallActivity.this,userRol);
                    recyclerView.setAdapter(postAdapter);
                }

                @Override
                public void onFailure(String errorMessage) {
                    Toast.makeText(ListWallActivity.this, errorMessage, Toast.LENGTH_SHORT).show();

                }
            });



        }



    }
}