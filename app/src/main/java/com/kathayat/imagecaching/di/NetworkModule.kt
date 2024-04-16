package com.kathayat.imagecaching.di

import com.kathayat.imagecaching.usecases.GetImagesUseCases
import com.kathayat.imagecaching.vm.ImageViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
@Module
@InstallIn(ViewModelComponent::class)
object NetworkModule {

    @Provides
    @ViewModelScoped
    fun provideImageViewModel(
        getImagesUseCases: GetImagesUseCases
    ): ImageViewModel {
        return ImageViewModel(getImagesUseCases)
    }
}