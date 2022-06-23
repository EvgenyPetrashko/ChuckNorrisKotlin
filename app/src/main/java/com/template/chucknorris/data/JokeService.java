package com.template.chucknorris.data;


import com.template.chucknorris.data.entities.JokeFromServer;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class JokeService {
    private JokeApi jokeApi;

    JokeService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.chucknorris.io/jokes/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        jokeApi = retrofit.create(JokeApi.class);
    }

    Observable<String> getRandomJoke(){
        return jokeApi.randomJoke().map(JokeFromServer::getValue);
    }
}
