package com.kathayat.imagecaching.ui.presention

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.kathayat.imagecaching.network.remote.ImagesDtoItem
import com.kathayat.imagecaching.utils.ErrorMessage
import com.kathayat.imagecaching.utils.LoadingNextPageItem
import com.kathayat.imagecaching.utils.PageLoader
import com.kathayat.imagecaching.vm.ImageViewModel


@Composable
fun InitilizeHomeScreen(
    viewModel: ImageViewModel = hiltViewModel()
) {
    val imagesPagingItems: LazyPagingItems<ImagesDtoItem> =
        viewModel.imagesState.collectAsLazyPagingItems()

    imagesPagingItems.apply {
        when {
            loadState.refresh is LoadState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                )
                {
                    PageLoader(modifier = Modifier.fillMaxSize())
                }
            }

            loadState.refresh is LoadState.Error -> {
                val error = imagesPagingItems.loadState.refresh as LoadState.Error
                ErrorMessage(
                    modifier = Modifier.fillMaxHeight(),
                    message = error.error.localizedMessage!!,
                    onClickRetry = { retry() })
            }


            loadState.append is LoadState.Loading -> {
                LoadingNextPageItem(modifier = Modifier)
            }

            loadState.append is LoadState.Error -> {
                val error = imagesPagingItems.loadState.append as LoadState.Error
                ErrorMessage(
                    modifier = Modifier,
                    message = error.error.localizedMessage!!,
                    onClickRetry = { retry() })
            }

            else -> {
                ImageHomeScreen()
            }
        }
    }


}

@Composable
fun ImageHomeScreen(
    viewModel: ImageViewModel = hiltViewModel()
) {
    val imagesPagingItems: LazyPagingItems<ImagesDtoItem> =
        viewModel.imagesState.collectAsLazyPagingItems()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalArrangement = Arrangement.Center,
        ) {


            itemsIndexed(imagesPagingItems.itemSnapshotList.items) { index, item ->

                ImageItem(
                    urls = imagesPagingItems[index]!!.urls
                )
                Log.d("ImageUrls", imagesPagingItems[index]!!.urls.toString())
            }
//            imagesPagingItems.apply {
//                when {
//                    loadState.refresh is LoadState.Loading -> {
//                        item(span = 1) {
//                            Box(
//                                modifier = Modifier.fillMaxSize(),
//                                contentAlignment = Alignment.Center
//                            )
//                            {
//                                PageLoader(modifier = Modifier.fillMaxSize())
//                            }
//                        }
//                    }
//
//                    loadState.refresh is LoadState.Error -> {
//                        val error = imagesPagingItems.loadState.refresh as LoadState.Error
//                        item {
//                            ErrorMessage(
//                                modifier = Modifier.fillMaxSize(),
//                                message = error.error.localizedMessage!!,
//                                onClickRetry = { retry() })
//                        }
//                    }
//
//                    loadState.append is LoadState.Loading -> {
//                        item { LoadingNextPageItem(modifier = Modifier) }
//                    }
//
//                    loadState.append is LoadState.Error -> {
//                        val error = imagesPagingItems.loadState.append as LoadState.Error
//                        item {
//                            ErrorMessage(
//                                modifier = Modifier,
//                                message = error.error.localizedMessage!!,
//                                onClickRetry = { retry() })
//                        }
//                    }
//                }
//            }
        }
    }
}

