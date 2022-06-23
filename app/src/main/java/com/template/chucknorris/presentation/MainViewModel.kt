package com.template.chucknorris.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.template.chucknorris.domain.entity.JokeWrapper
import com.template.chucknorris.domain.usecase.GetJokeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getJokeUseCase: GetJokeUseCase) : ViewModel() {
    private val jokeMutable = MutableLiveData<String?>(null)
    val joke: LiveData<String?>
        get() = jokeMutable

    private val errorMutable = MutableLiveData<String?>(null)
    val error: LiveData<String?>
        get() = errorMutable

    private val loadingMutable = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = loadingMutable

    fun loadJoke() {
        loadingMutable.postValue(true)
        viewModelScope.launch {
            when (val jokeWrapper = getJokeUseCase.execute()) {
                is JokeWrapper.Joke -> {
                    jokeMutable.postValue(jokeWrapper.value)
                    errorMutable.postValue(null)
                }
                is JokeWrapper.Error -> {
                    errorMutable.postValue(jokeWrapper.error)
                    jokeMutable.postValue(null)
                }
            }
            loadingMutable.postValue(false)
        }
    }
}