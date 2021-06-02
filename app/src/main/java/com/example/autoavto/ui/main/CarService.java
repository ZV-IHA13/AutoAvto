package com.example.autoavto.ui.main;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CarService {
    @GET("/cars/getto")
    Call<To_information[]> getTo(@Query("to") String to,@Query("car")String carname);

    @GET("/cars/getcar")
    Call<CarNames[]> getCar();
}
