package com.example.autoavto.ui;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CarService {
    @GET("/KIA_Sportage_2010_4WD/getcars")
    Call<Car[]> getCars();

    @GET("/KIA_Sportage_2010_4WD/getcar")
    Call<Car> getCar(@Query("id") String id);

    @GET("/KIA_Sportage_2010_4WD/getto")
    Call<Car[]> getTo(@Query("to") String to);

}
