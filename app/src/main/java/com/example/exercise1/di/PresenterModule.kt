package com.example.exercise1.di

import com.example.exercise1.data.remote.repository.RemoteRepository
import com.example.exercise1.pages.others.Others
import com.example.exercise1.pages.others.OthersPresenter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PresenterModule {

    @Provides
    @Singleton
    fun provideOthersPresenter(remoteRepository: RemoteRepository) : Others.Presenter {
        return OthersPresenter(remoteRepository)
    }

}