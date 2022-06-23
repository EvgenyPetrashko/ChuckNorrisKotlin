package com.template.chucknorris

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.template.chucknorris.databinding.ActivityMainBinding
import com.template.chucknorris.domain.usecase.GetJokeUseCase
import com.template.chucknorris.presentation.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel by viewModels<MainViewModel>()
    private var activityMainBinding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        this.activityMainBinding = activityMainBinding
        setContentView(activityMainBinding.root)
        activityMainBinding.generateRandomJokeBtn.setOnClickListener {
            mainViewModel.loadJoke()
        }

        mainViewModel.joke.observe(this) {
            if (it != null) {
                activityMainBinding.cardContainer.jokeText.text = it
            }
        }

        mainViewModel.error.observe(this) {
            if (it != null) {
                Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
            }
        }

        mainViewModel.loading.observe(this) {
            changeCardVisibility(hidden = it)
        }
    }

    private fun changeCardVisibility(hidden: Boolean) {
        if (hidden) {
            activityMainBinding?.cardContainer?.loadingIndicator?.visibility = View.VISIBLE
            activityMainBinding?.cardContainer?.jokeText?.visibility = View.GONE
            activityMainBinding?.cardContainer?.jokeTitle?.visibility = View.GONE
        } else {
            activityMainBinding?.cardContainer?.loadingIndicator?.visibility = View.GONE
            activityMainBinding?.cardContainer?.jokeText?.visibility = View.VISIBLE
            activityMainBinding?.cardContainer?.jokeTitle?.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        activityMainBinding = null
    }
}