package com.template.chucknorris

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private var TAG = "MainActivity"
    private var currentJokeLiked = false
    private lateinit var jokeObservable: Observable<String>
    private lateinit var jokeObserver: Observer<String>
    private lateinit var jokeService: JokeService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rxBtn = findViewById<Button>(R.id.generateRandomJokeBtn)
        jokeObserver = getObserver()

        jokeService = JokeService()

        rxBtn.setOnClickListener { startRStream() }

    }

    private fun startRStream(){
        jokeObservable = jokeService.randomJoke

        jokeObservable
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(jokeObserver)

    }

    private fun getObserver(): Observer<String> {
        return object: Observer<String>{
            override fun onSubscribe(d: Disposable?) {
                val loadingCard = View.inflate(this@MainActivity, R.layout.loading_joke_card, null)
                val ll = findViewById<LinearLayout>(R.id.card_container)
                ll.removeAllViews()
                ll.addView(loadingCard)
            }

            override fun onNext(t: String?) {
                Log.d(TAG, "onNext: $t")
                val ll = findViewById<LinearLayout>(R.id.card_container)
                ll.removeViewAt(0)
                val jokeCard = View.inflate(this@MainActivity, R.layout.joke_card, null)
                jokeCard.findViewById<TextView>(R.id.jokeText).text = t
                jokeCard.findViewById<ImageButton>(R.id.jokeLikeButton).setOnClickListener {
                    currentJokeLiked = !currentJokeLiked
                    if (currentJokeLiked){
                        (it as ImageButton).setImageResource(R.drawable.like_image_red)
                    }else{
                        (it as ImageButton).setImageResource(R.drawable.like_image_white)
                    }
                }
                ll.addView(jokeCard)
            }

            override fun onError(e: Throwable?) {
                Log.e(TAG, "onError: " + e?.message)
                Toast.makeText(this@MainActivity, "ErrorDuringInternetRequest", Toast.LENGTH_SHORT).show()
            }

            override fun onComplete() {
                Log.d(TAG, "onComplete")
            }

        }
    }
}