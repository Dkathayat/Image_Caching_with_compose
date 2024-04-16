package com.kathayat.imagecaching.di

import android.content.Context
import com.kathayat.imagecaching.data.ImageRemoteDataSource
import com.kathayat.imagecaching.data.ImagesRemoteDataSourceImpl
import com.kathayat.imagecaching.network.UnsplashApi
import com.kathayat.imagecaching.repository.ImagesRepository
import com.kathayat.imagecaching.repository.ImagesRepositoryImp
import com.kathayat.imagecaching.usecases.GetImagesUseCases
import com.kathayat.imagecaching.utils.NetworkConnectionInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit.SECONDS
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesImagesRemoteDataSource(
        api: UnsplashApi
    ): ImageRemoteDataSource {
        return ImagesRemoteDataSourceImpl(api)
    }

    @Singleton
    @Provides
    fun providesImagesRepository(
        imageRemoteDataSource: ImageRemoteDataSource
    ): ImagesRepository {
        return ImagesRepositoryImp(imageRemoteDataSource)
    }

    @Singleton
    @Provides
    fun providesGetImagesUseCase(
        imageRepository: ImagesRepository
    ): GetImagesUseCases {
        return GetImagesUseCases(imageRepository)
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addNetworkInterceptor(httpLoggingInterceptor)
            .addInterceptor(NetworkConnectionInterceptor(context))
            .connectTimeout(60, SECONDS)
            .readTimeout(60, SECONDS)
            .writeTimeout(60, SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(UnsplashApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }


    @Provides
    @Singleton
    fun provideUserService(retrofit: Retrofit): UnsplashApi {
        return retrofit.create(UnsplashApi::class.java)
    }


}