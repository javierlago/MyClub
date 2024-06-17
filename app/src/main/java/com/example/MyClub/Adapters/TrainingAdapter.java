package com.example.MyClub.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MyClub.Models.Exercice;
import com.example.conectarapi.R;

import java.util.List;

public class TrainingAdapter extends RecyclerView.Adapter<TrainingAdapter.TrainingViewHolder>{
    private final List<Exercice> exercices;

    public TrainingAdapter(List<Exercice> exercices, Context context) {
        this.exercices = exercices;
        this.context = context;
    }

    Context context;
    @NonNull
    @Override
    public TrainingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ejercicio, parent, false);
        return new TrainingViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainingViewHolder holder, int position) {
       holder.setData(exercices.get(position));
    }

    @Override
    public int getItemCount() {
        return exercices.size();
    }

    static class TrainingViewHolder extends RecyclerView.ViewHolder {
        TextView textNombre,textSeries,textUnidades,textDescripcionUnidades,textDescripcionIntensidad;



        public TrainingViewHolder(@NonNull View itemView) {
            super(itemView);
            textNombre = itemView.findViewById(R.id.text_nombre);
            textSeries = itemView.findViewById(R.id.text_series);
            textUnidades = itemView.findViewById(R.id.text_unidades);
            textDescripcionIntensidad = itemView.findViewById(R.id.text_descripcion_intensidad);
            textDescripcionUnidades = itemView.findViewById(R.id.text_descripcion_unidades);
        }

        public void setData(Exercice exercice) {
            textNombre.setText(exercice.getNombre());
            textSeries.setText(String.valueOf(exercice.getSeries()));
            textUnidades.setText(String.valueOf(exercice.getUnidades()));
            textDescripcionIntensidad.setText(exercice.getIntensidad());
            textDescripcionUnidades.setText(exercice.getDescripcionUnidades());
        }
    }

}
