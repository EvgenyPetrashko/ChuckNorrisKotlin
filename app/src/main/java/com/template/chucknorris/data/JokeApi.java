package com.template.chucknorris.data;

import com.template.chucknorris.data.entities.JokeFromServer;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface JokeApi {
    @GET("random")
    Observable<JokeFromServer> randomJoke();

}
