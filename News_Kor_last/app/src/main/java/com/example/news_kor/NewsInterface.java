package com.example.news_kor;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsInterface {

    //가장 기본이 되는 상위 주소를 등록
    String BASE_URL = "https://newsapi.org/v2/";

    @GET("top-headlines")
    Call<MainNews> getNews(
            //국가에 해당하는 뉴스 목록 가져오기
            @Query("country") String country,
            @Query("pageSize") int pagesize,
            @Query("apiKey") String apikey
    );

    @GET("top-headlines")
    Call<MainNews> getCategoryNews(
            //뉴스 카테고리 정보를 가져오기
            @Query("country") String country,
            @Query("category") String category,
            @Query("pageSize") int pagesize,
            @Query("apiKey") String apikey
    );
}
