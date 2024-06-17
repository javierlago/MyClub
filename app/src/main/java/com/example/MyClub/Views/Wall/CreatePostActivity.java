package com.example.MyClub.Views.Wall;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.MyClub.Controlers.ApiClient;
import com.example.MyClub.Controlers.PostController;
import com.example.MyClub.Dialogs.DialogWindow;
import com.example.MyClub.Interfaces.CallbackController;
import com.example.MyClub.Models.Post;
import com.example.conectarapi.R;

import java.time.LocalDate;
import java.util.Date;


@RequiresApi(api = Build.VERSION_CODES.O)
public class CreatePostActivity extends AppCompatActivity {
    Button btnCreatePost,btnCleanPost;
    TextView txtTitle,txtDescription;

    DialogWindow dialogWindow = new DialogWindow();
    Date date = java.sql.Date.valueOf(String.valueOf(LocalDate.now()));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_post_activity);
        initViews();
        listener();

    }
    public void initViews(){
        btnCreatePost = findViewById(R.id.btn_save_post);
        btnCleanPost = findViewById(R.id.btn_post_clean);
        txtDescription = findViewById(R.id.txt_post_description);
        txtTitle = findViewById(R.id.txt_post_title);
    }
    public void listener(){
        btnCreatePost.setOnClickListener(v -> {
            if (txtTitle.getText().toString().isEmpty() || txtDescription.getText().toString().isEmpty()){
                dialogWindow.infoWindow(this,getString(R.string.warnig),getString(R.string.error_empty_fields));
        }else{
            Post post = new Post(txtTitle.getText().toString(),txtDescription.getText().toString(),date);
                PostController postController = new PostController(this);
                postController.createPost(post, new CallbackController() {
                    @Override
                    public void onSucces(String succes) {
                        dialogWindow.infoWindow(CreatePostActivity.this,getString(R.string.warnig),succes);
                    }

                    @Override
                    public void onFailure(String message) {
                        dialogWindow.infoWindow(CreatePostActivity.this,getString(R.string.warnig),message);
                    }
                });
            }
    });
        btnCleanPost.setOnClickListener(v -> {
            txtTitle.setText("");
            txtDescription.setText("");
        });
    }
}
