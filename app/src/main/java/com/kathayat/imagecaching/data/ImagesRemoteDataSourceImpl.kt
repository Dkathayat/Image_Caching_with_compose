package com.kathayat.imagecaching.data

import com.kathayat.imagecaching.network.UnsplashApi
import com.kathayat.imagecaching.network.remote.ImagesDto
import retrofit2.Response
import javax.inject.Inject

class ImagesRemoteDataSourceImpl @Inject constructor(
    private val api:UnsplashApi
) : ImageRemoteDataSource{
    override suspend fun getImages(
        apiKey: String,
        pageNumber: Int,
        perpage: Int
    ): Response<ImagesDto> {
        return api.getPhotos(perpage,apiKey,pageNumber)
    }

}