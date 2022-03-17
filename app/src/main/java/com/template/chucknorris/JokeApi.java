package com.template.chucknorris;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface JokeApi {
    @GET("random")
    Observable<Joke> randomJoke();

}
