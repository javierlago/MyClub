package com.example.MyClub.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MyClub.Dialogs.DialogWindow;
import com.example.MyClub.Interfaces.AdapterViewButtonClickListener;
import com.example.MyClub.Interfaces.DialogListener;
import com.example.MyClub.Models.Exercice;
import com.example.conectarapi.R;

import java.util.List;

public class TrainingAdapter extends RecyclerView.Adapter<TrainingAdapter.TrainingViewHolder>{
    private final List<Exercice> exercices;
    Context context;

    public TrainingAdapter(List<Exercice> exercices, AdapterViewButtonClickListener adapterViewButtonClickListener) {
        this.exercices = exercices;
        this.adapterViewButtonClickListener = adapterViewButtonClickListener;
    }

    AdapterViewButtonClickListener adapterViewButtonClickListener;

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

    public class TrainingViewHolder extends RecyclerView.ViewHolder {
        TextView textNombre,textSeries,textUnidades,textDescripcionUnidades,textDescripcionIntensidad;
        ImageButton btnDelete;



        public TrainingViewHolder(@NonNull View itemView) {
            super(itemView);
            DialogWindow dialogWindow = new DialogWindow();
            textNombre = itemView.findViewById(R.id.text_nombre);
            textSeries = itemView.findViewById(R.id.text_series);
            textUnidades = itemView.findViewById(R.id.text_unidades);
            textDescripcionIntensidad = itemView.findViewById(R.id.text_descripcion_intensidad);
            textDescripcionUnidades = itemView.findViewById(R.id.text_descripcion_unidades);
            btnDelete = itemView.findViewById(R.id.btn_erase_exercise);

            btnDelete.setOnClickListener(new View.OnClickListener() {
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

        }

        public void setData(Exercice exercice) {
            textNombre.setText(exercice.getNombre());
            textSeries.setText(String.valueOf(exercice.getSeries()));
            textUnidades.setText(String.valueOf(exercice.getUnidades()));
            textDescripcionIntensidad.setText(exercice.getIntensidad());
            textDescripcionUnidades.setText(exercice.getDescripcionUnidades());
            btnDelete.setVisibility(View.VISIBLE);
        }
    }

}
