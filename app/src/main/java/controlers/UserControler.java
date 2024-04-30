package controlers;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import interfaces.UserApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import views.DirectivoActivity;

public class UserControler {
    public final String BASE_URL = "http://192.168.1.139/android/php/";

    public UserControler() {
    }

    public void login(Context context,String usuario,String password){

        // Crear instancia de Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create()) // Agregar el convertidor
                .build();
        String result = null;
        // Crear instancia de la interfaz DirectivoApi
        UserApi directivoApi = retrofit.create(UserApi.class);

        // Hacer la llamada a la API
        Call<String> call = directivoApi.validateUser(usuario,password,"login");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                        Toast.makeText(context, response.body(), Toast.LENGTH_SHORT).show();
                    assert response.body() != null;
                    if(response.body().equalsIgnoreCase("entrenador")){
                        Intent intent = new Intent(context, DirectivoActivity.class);
                        context.startActivity(intent);

                        }


                } else {
                    Toast.makeText(context, "Error al obtener los datos", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.println(7,"error",t.getMessage());
                Toast.makeText(context, "Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
















}
