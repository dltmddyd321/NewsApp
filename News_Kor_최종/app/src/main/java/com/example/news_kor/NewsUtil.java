package com.example.news_kor;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsUtil {

    private static Retrofit retrofit = null;

    public static NewsInterface getApiInterface() {

        if(retrofit == null) {
            //Retrofit 데이터가 존재한다면 Gson을 처리하고 BASE_URL을 통해 서버에서 데이터를 받아올 수 있도록 Build
            retrofit = new Retrofit.Builder().baseUrl(NewsInterface.BASE_URL).addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(NewsInterface.class);
    }
}
