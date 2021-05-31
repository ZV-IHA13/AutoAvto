package com.example.autoavto.ui;

import com.example.autoavto.ui.settings.CarNames;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CarService {
    @GET("/KIA_Sportage_2010_4WD/getallinformation")
    Call<To_information[]> getAllInformation();

    @GET("/KIA_Sportage_2010_4WD/getinformation")
    Call<To_information> getToInfo(@Query("id") String id);

    @GET("/KIA_Sportage_2010_4WD/getto")
    Call<To_information[]> getTo(@Query("to") String to);

    @GET("/KIA_Sportage_2010_4WD/getcar")
    Call<CarNames[]> getCar();

}
