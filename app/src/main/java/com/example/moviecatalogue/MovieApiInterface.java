package com.example.moviecatalogue;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApiInterface {
    @GET("now_playing")
    Call<NowPlayingResponse> getNowPlaying(
            @Query("api_key") String apiKey
    );
}
