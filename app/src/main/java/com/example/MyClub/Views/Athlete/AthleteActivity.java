package com.example.MyClub.Views.Athlete;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MyClub.Adapters.PostAdapter;
import com.example.MyClub.Constants.Constantes;
import com.example.MyClub.Controlers.PostController;
import com.example.MyClub.Controlers.UserControler;
import com.example.MyClub.Interfaces.AllPostCallBack;
import com.example.MyClub.Interfaces.GetUserById;
import com.example.MyClub.Models.Post;
import com.example.MyClub.Models.User;
import com.example.conectarapi.R;

import java.util.List;

public class AthleteActivity extends AppCompatActivity {
    PostAdapter postAdapter;
    PostController postController;

    RecyclerView recyclerView;
    UserControler userControler;



    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.athlete_activity);
        recyclerView = findViewById(R.id.recyclerView_posts_athelete);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        postController = new PostController(this);
        userControler = new UserControler(this);
        textView = findViewById(R.id.header_text_athlete);
        setTextView(textView,this);
        postController.getAllPosts(new AllPostCallBack() {
            @Override
            public void onSuccess(List<Post> postList) {
                postAdapter = new PostAdapter(postList, AthleteActivity.this, Constantes.getAthlete(AthleteActivity.this));
                recyclerView.setAdapter(postAdapter);
            }

            @Override
            public void onFailure(String errorMessage) {
                Toast.makeText(AthleteActivity.this, errorMessage, Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void setTextView(TextView textView,Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId", 0);
        userControler.getUserById(userId, new GetUserById() {

            @Override
            public void onSuccess(User user) {
                textView.setText(user.getFullName());
            }
            @Override
            public void onFailure(String errorMessage) {
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();

            }
        });

    }
}