package com.example.MyClub.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MyClub.Models.Exercice;
import com.example.MyClub.Models.Post;
import com.example.MyClub.Utitities.Validations;
import com.example.conectarapi.R;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PublicacionViewHolder> {

    private final List<Post> publicaciones;
    Context context;
    // Constructor y métodos para establecer los datos

    public PostAdapter(List<Post> publicaciones, Context context, String apiService) {
        this.publicaciones = publicaciones;
        this.context = context;
    }


    @NonNull
    @Override
    public PublicacionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_post, parent, false);
        return new PublicacionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PublicacionViewHolder holder, int position) {
        Post publicacion = publicaciones.get(position);
        holder.bind(publicacion, context);
    }

    @Override
    public int getItemCount() {
        return publicaciones.size();
    }

    static class PublicacionViewHolder extends RecyclerView.ViewHolder {
        private final TextView textTitulo;
        private final TextView textDescripcion, tipoPublicacion, textViewDate;

        private final LinearLayout layoutEjercicios;

        PublicacionViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitulo = itemView.findViewById(R.id.text_titulo);
            textDescripcion = itemView.findViewById(R.id.text_descripcion);
            layoutEjercicios = itemView.findViewById(R.id.layout_ejercicios);
            tipoPublicacion = itemView.findViewById(R.id.text_tipo_publicacion);
            textViewDate = itemView.findViewById(R.id.txt_post_date);






        }

        void bind(Post post, Context context) {
            textTitulo.setText(post.getTitulo().toUpperCase());
            textViewDate.setText(Validations.dateConverter(post.getFecha()));


            // Si el array de ejercicios no está vacío, muestra los ejercicios y oculta la descripción
            if (post.getEjercicios() != null && !post.getEjercicios().isEmpty()){
                tipoPublicacion.setText(context.getResources().getString(R.string.entrenamiento));
                textTitulo.setVisibility(View.GONE);
                textDescripcion.setVisibility(View.GONE);
                layoutEjercicios.setVisibility(View.VISIBLE);
                layoutEjercicios.removeAllViews(); // Limpiar ejercicios anteriores

                for (Exercice exercice : post.getEjercicios()) {
                    View ejercicioView = LayoutInflater.from(itemView.getContext()).inflate(R.layout.item_ejercicio, layoutEjercicios, false);
                    TextView textNombre = ejercicioView.findViewById(R.id.text_nombre);
                    TextView textSeries = ejercicioView.findViewById(R.id.text_series);
                    TextView textUnidades = ejercicioView.findViewById(R.id.text_unidades);
                    TextView textDescripcionUnidades = ejercicioView.findViewById(R.id.text_descripcion_unidades);
                    TextView textDescripcionIntensidad = ejercicioView.findViewById(R.id.text_descripcion_intensidad);
                    textNombre.setText(exercice.getNombre());
                    textSeries.setText(String.valueOf(exercice.getSeries()));
                    textUnidades.setText(String.valueOf(exercice.getUnidades()));
                    textDescripcionUnidades.setText(exercice.getDescripcionUnidades());
                    textDescripcionIntensidad.setText(exercice.getIntensidad());
                    layoutEjercicios.addView(ejercicioView);
                }
            } else{
                textTitulo.setVisibility(View.VISIBLE);
                tipoPublicacion.setText(context.getResources().getString(R.string.post));
                textDescripcion.setVisibility(View.VISIBLE);
                layoutEjercicios.setVisibility(View.GONE);
                textDescripcion.setText(post.getDescripcion());
            }
        }
    }
}

