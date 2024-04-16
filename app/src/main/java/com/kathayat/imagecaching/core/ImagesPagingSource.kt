package com.kathayat.imagecaching.core

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kathayat.imagecaching.data.ImageRemoteDataSource
import com.kathayat.imagecaching.network.UnsplashApi
import com.kathayat.imagecaching.network.remote.ImagesDtoItem
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ImagesPagingSource @Inject constructor(
    private val imagesDataSource: ImageRemoteDataSource
): PagingSource<Int, ImagesDtoItem>() {
    override fun getRefreshKey(state: PagingState<Int, ImagesDtoItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImagesDtoItem> {
        return try {
            val currentPage = params.key ?: 1
            val images = imagesDataSource.getImages(
                apiKey = UnsplashApi.API_KEY,
                pageNumber = currentPage,
                perpage = 20
            )
            LoadResult.Page(
                data = images.results!!,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (images.results.isEmpty()) null else images.page!! + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
        }
    }
