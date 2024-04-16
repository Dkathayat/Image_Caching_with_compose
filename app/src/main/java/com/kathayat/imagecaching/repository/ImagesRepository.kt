package com.kathayat.imagecaching.repository

import androidx.paging.PagingData
import com.kathayat.imagecaching.network.remote.ImagesDtoItem
import kotlinx.coroutines.flow.Flow

interface ImagesRepository {

    suspend fun getImages(): Flow<PagingData<ImagesDtoItem>>
}