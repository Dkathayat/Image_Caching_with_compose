package com.kathayat.imagecaching.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kathayat.imagecaching.core.ImagesPagingSource
import com.kathayat.imagecaching.data.ImageRemoteDataSource
import com.kathayat.imagecaching.network.remote.ImagesDtoItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImagesRepositoryImp @Inject constructor(
    private val imagesRemoteDataSource: ImageRemoteDataSource
): ImagesRepository {
    override suspend fun getImages(): Flow<PagingData<ImagesDtoItem>> {
        return Pager(
            config = PagingConfig(pageSize = 20, prefetchDistance = 2),
            pagingSourceFactory = {
                        ImagesPagingSource(imagesRemoteDataSource)
            }
        ).flow
    }

}