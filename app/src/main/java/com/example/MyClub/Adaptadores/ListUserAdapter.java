package com.example.MyClub.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MyClub.models.User;
import com.example.conectarapi.R;

import java.util.ArrayList;
import java.util.List;

public class ListUserAdapter extends RecyclerView.Adapter<ListUserAdapter.ViewHolder> {

    ArrayList<User> listUserData;

    public ListUserAdapter(ArrayList<User> listUserData) {
        this.listUserData = listUserData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_users,parent,false);
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
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userName  = (TextView) itemView.findViewById(R.id.card_view_name_user);
            userSurname  = (TextView) itemView.findViewById(R.id.card_view_surname_user);

        }

        public void setData(User user) {
            userName.setText(user.getName());
            userSurname.setText(user.getApellido()+" "+user.getApellidosegundo());

        }
    }
}
