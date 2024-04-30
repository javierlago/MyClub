package interfaces;

import models.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface UserApi {
    @GET("user.php")
    Call<User> find(@Query("nombre") String nombre, @Query("accion") String accion);

    @GET("user.php")
    Call<String> validateUser(@Query("email") String email, @Query("password") String password, @Query("accion") String accion);

}


