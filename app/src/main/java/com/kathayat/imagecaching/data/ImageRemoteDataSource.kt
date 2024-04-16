package com.kathayat.imagecaching.data

import com.kathayat.imagecaching.network.ResponseDto
import com.kathayat.imagecaching.network.remote.ImagesDto

interface ImageRemoteDataSource {

    suspend fun getImages(
        apiKey: String,
        pageNumber: Int,
        perpage:Int
    ): ResponseDto<ImagesDto>


}