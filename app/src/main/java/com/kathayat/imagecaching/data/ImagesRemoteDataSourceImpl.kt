package com.kathayat.imagecaching.data

import com.kathayat.imagecaching.network.ResponseDto
import com.kathayat.imagecaching.network.UnsplashApi
import com.kathayat.imagecaching.network.remote.ImagesDto
import javax.inject.Inject

class ImagesRemoteDataSourceImpl @Inject constructor(
    private val api:UnsplashApi
) : ImageRemoteDataSource{
    override suspend fun getImages(
        apiKey: String,
        pageNumber: Int,
        perpage: Int
    ): ResponseDto<ImagesDto> {
        return api.getPhotos(perpage,apiKey,pageNumber)
    }

}