package com.kathayat.imagecaching.network

import com.kathayat.imagecaching.network.remote.ImagesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApi {

    companion object {
        // the value are exposed as just for testing purpose
        const val BASE_URL = "https://api.unsplash.com/"
        const val API_KEY = "iQlhDmJM7qyT9yZ40XFOsyujtD0pSA_X6ztzSWvtlrM"
    }

    @GET("photos")
    suspend fun getPhotos(
        @Query("per_page") perPage:Int,
        @Query("client_id") clientId:String,
        @Query("page") page:Int
    ): ResponseDto<ImagesDto>

}