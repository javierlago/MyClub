package com.example.MyClub.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.MyClub.Interfaces.AdapterViewButtonClickListener;
import com.example.MyClub.Models.User;
import com.example.conectarapi.R;

import java.util.ArrayList;

public class ListUserAdapter extends RecyclerView.Adapter<ListUserAdapter.ViewHolder> {

    ArrayList<User> listUserData;

    public ListUserAdapter(ArrayList<User> listUserData, AdapterViewButtonClickListener adapterViewButtonClickListener) {
        this.listUserData = listUserData;
        this.adapterViewButtonClickListener = adapterViewButtonClickListener;
    }


    public ListUserAdapter(ArrayList<User> listUserData) {
        this.listUserData = listUserData;
    }

    AdapterViewButtonClickListener adapterViewButtonClickListener;

    /*public ListUserAdapter(ArrayList<User> listUserData) {
        this.listUserData = listUserData;
    }*/

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_users, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(listUserData.get(position));

    }

    @Override
    public int getItemCount() {
        return listUserData.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView userName;
        TextView userSurname;
        TextView rolName;

        Button buttonEditUser;
        Button buttonDeleteUser;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = (TextView) itemView.findViewById(R.id.card_view_name_user);
            userSurname = (TextView) itemView.findViewById(R.id.card_view_surname_user);
            rolName = (TextView) itemView.findViewById(R.id.card_view_rol_name);
            buttonEditUser = (Button) itemView.findViewById(R.id.card_view_btn_edit_user);
            buttonDeleteUser = (Button) itemView.findViewById(R.id.card_view_btn_delete_user);


            buttonEditUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (adapterViewButtonClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            adapterViewButtonClickListener.onEditButtonClick(position);
                        }
                    }

                }
            });

            buttonDeleteUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (adapterViewButtonClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            adapterViewButtonClickListener.onDeleteButtonClick(position);
                        }
                    }

                }
            });


        }

        public void setData(User user) {
            rolName.setText(user.getRol().toUpperCase());
            userName.setText(user.getName());
            userSurname.setText(user.getApellido() + " " + user.getApellidosegundo());

        }
    }
}
