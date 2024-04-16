package com.kathayat.imagecaching.data

import com.kathayat.imagecaching.network.remote.ImagesDto
import retrofit2.Response

interface ImageRemoteDataSource {

    suspend fun getImages(
        apiKey: String,
        pageNumber: Int,
        perpage:Int
    ): Response<ImagesDto>


}