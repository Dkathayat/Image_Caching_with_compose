package com.kathayat.imagecaching.usecases

import androidx.paging.PagingData
import com.kathayat.imagecaching.network.remote.ImagesDtoItem
import com.kathayat.imagecaching.repository.ImagesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetImagesUseCases @Inject constructor(
    private val repository:ImagesRepository
): BaseUseCase<Unit, Flow<PagingData<ImagesDtoItem>>> {
    override suspend fun execute(input: Unit): Flow<PagingData<ImagesDtoItem>> {
        return repository.getImages()
    }


}