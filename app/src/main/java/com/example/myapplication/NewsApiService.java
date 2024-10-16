package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiService {
    @GET("top-headlines")
    Call<NewsResponse> getHealthNews(
            @Query("category") String category,  // e.g., "health"
            @Query("apiKey") String apiKey       // Your News API key
    );
}

