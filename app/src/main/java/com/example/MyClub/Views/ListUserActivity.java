package com.example.MyClub.Views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.MyClub.Adaptadores.ListUserAdapter;
import com.example.MyClub.Constantes.Constantes;
import com.example.MyClub.Controlers.UserControler;
import com.example.MyClub.Directivo.DirectivoActivity;
import com.example.MyClub.Interfaces.AdapterViewButtonClickListener;
import com.example.MyClub.Interfaces.UserControllerCallback;
import com.example.MyClub.Interfaces.DialogListener;
import com.example.MyClub.Interfaces.GetUserById;
import com.example.MyClub.Interfaces.GetUsersCallback;
import com.example.MyClub.Models.User;
import com.example.MyClub.Dialogs.DialogWindows;
import com.example.conectarapi.R;


import java.util.ArrayList;


public class ListUserActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtTitle;
    RecyclerView recyclerView;
    UserControler userControler ;

    Button btnCreateUser;
    Button btnBack;

    int userLogedId;

    String route;
    ListUserAdapter listUserAdapter;

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_list_user);
        btnCreateUser = findViewById(R.id.btn_create_user);
        btnBack = findViewById(R.id.btn_back);
        listeners();
        // Recuperamos el String para que la llamada a la api sea diferente y asi
        // aprovechar la misma actividad para cargar los datos de cualquier tipo de usuario
        Intent intent = getIntent();
        route = intent.getStringExtra("ApiService");
        recyclerView = findViewById(R.id.recyclerView_users);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SharedPreferences sharedPreferences=  getSharedPreferences("MyAppPrefs",Context.MODE_PRIVATE);
        userLogedId = sharedPreferences.getInt("userId",0);
        userControler = new UserControler(this);
        txtTitle = findViewById(R.id.header_text);
        setHeader(route);

        userControler.getUsers(route, new GetUsersCallback() {
            @Override
            public void onSuccess(ArrayList<User> users) {
                listUserAdapter = new ListUserAdapter(users, new AdapterViewButtonClickListener() {
                    @Override
                    public void onEditButtonClick(int position) {
                        int userId = users.get(position).getId();
                        userControler.getUserById(userId, new GetUserById() {

                            @Override
                            public void onSuccess(User user) {
                                Intent intent = new Intent(ListUserActivity.this, EditUserActivity.class);
                                intent.putExtra("user",user);
                                startActivity(intent);

                            }

                            @Override
                            public void onFailure(String errorMessage) {
                                Toast.makeText(ListUserActivity.this, errorMessage, Toast.LENGTH_SHORT).show();

                            }
                        });





                    }

                    @Override
                    public void onDeleteButtonClick(int position) {
                        if(userLogedId  == users.get(position).getId()){
                            Toast.makeText(ListUserActivity.this, getResources().getString(R.string.no_delete_user_loged), Toast.LENGTH_SHORT).show();

                        }else{
                            DialogWindows dialogWindows = new DialogWindows();
                            String message =getResources().getString(R.string.delete_user) + users.get(position).getName() + "?";
                            dialogWindows.acceptCancelWindow(ListUserActivity.this,getResources().getString(R.string.delete_user_title), message, new DialogListener() {
                                @Override
                                public void onApceptSelected() {
                                    userControler.deleteUser(users.get(position).getId(), new UserControllerCallback() {
                                        @Override
                                        public void onSucces(String succes) {
                                            Toast.makeText(ListUserActivity.this, succes, Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onFailure(String errorMessage) {
                                            Toast.makeText(ListUserActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    Toast.makeText(ListUserActivity.this, "Deleteando user" + users.get(position).getId(), Toast.LENGTH_SHORT).show();
                                    users.remove(position);
                                    listUserAdapter.notifyItemRemoved(position);

                                }

                                @Override
                                public void onCancelSelected() {
                                    Toast.makeText(ListUserActivity.this,getResources().getString(R.string.cancel_action_delete_msg), Toast.LENGTH_SHORT).show();

                                }
                            });


                        }


                    }
                });
                recyclerView.setAdapter(listUserAdapter);

            }

            @Override
            public void onFailure(String errorMessage) {
                Log.d("ErrorListView",errorMessage);
                Toast.makeText(ListUserActivity.this, errorMessage, Toast.LENGTH_SHORT).show();

            }
        });



    }
    public void listeners() {
        btnCreateUser.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        if(v == btnCreateUser){
            Intent intent = new Intent(ListUserActivity.this, CreateUserActivity.class);
            intent.putExtra("ApiService", route);
            startActivity(intent);
        }else if(v == btnBack){
            Intent intent = new Intent(ListUserActivity.this, DirectivoActivity.class);
            startActivity(intent);
    }
}

public void setHeader(String title){
        String newTitle;
        if(Constantes.getEntrenador(this).equalsIgnoreCase(title)){
             newTitle = getResources().getString(R.string.list_user_header) + " " + title+ "es";
        }else{
             newTitle = getResources().getString(R.string.list_user_header) + " " + title+ "s";
        }
        txtTitle.setText(newTitle);
}


}