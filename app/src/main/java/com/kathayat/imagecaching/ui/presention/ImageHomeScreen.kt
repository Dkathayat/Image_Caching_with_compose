package com.kathayat.imagecaching.ui.presention

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
fun ImageHomeScreen(
    viewModel: ImageViewModel = hiltViewModel()
){
    val imagesPagingItems: LazyPagingItems<ImagesDtoItem> = viewModel.imagesState.collectAsLazyPagingItems()
    LazyColumn(
     //   modifier = androidx.compose.ui.Modifier.padding(it)
    ) {
        item { Spacer(modifier = Modifier.padding(4.dp)) }
        items(imagesPagingItems.itemCount) { index ->
            ImageItem(
                urls = imagesPagingItems[index]!!.urls
            )
        }
        imagesPagingItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { PageLoader(modifier = Modifier.fillParentMaxSize()) }
                }

                loadState.refresh is LoadState.Error -> {
                    val error = imagesPagingItems.loadState.refresh as LoadState.Error
                    item {
                        ErrorMessage(
                            modifier = Modifier.fillParentMaxSize(),
                            message = error.error.localizedMessage!!,
                            onClickRetry = { retry() })
                    }
                }

                loadState.append is LoadState.Loading -> {
                    item { LoadingNextPageItem(modifier = Modifier) }
                }

                loadState.append is LoadState.Error -> {
                    val error = imagesPagingItems.loadState.append as LoadState.Error
                    item {
                        ErrorMessage(
                            modifier = Modifier,
                            message = error.error.localizedMessage!!,
                            onClickRetry = { retry() })
                    }
                }
            }
        }
        item { Spacer(modifier = Modifier.padding(4.dp)) }
    }

}