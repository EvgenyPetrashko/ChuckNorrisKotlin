package com.template.chucknorris.di

import android.app.Application
import com.template.chucknorris.data.JokeService
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [JokeService::class, RepositoryModule::class, AndroidInjectionModule::class])
interface AppComponent: AndroidInjector<MyApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(myApplication: MyApplication)
}