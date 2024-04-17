package com.kathayat.imagecaching.ui.presention

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kathayat.imagecaching.network.remote.Urls
import com.kathayat.imagecaching.ui.theme.ImageCachingTheme

@Composable
fun ImageItem(urls: Urls) {
    Column(
        modifier = Modifier.height(250.dp).width(200.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
           model = urls.regular,
            modifier = Modifier.fillMaxSize()
                .padding(20.dp)
                .clip(RoundedCornerShape(10)),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
    }
}

@Composable
fun RandomImages(urls: Urls){
    Box(modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(10.dp)))
}


@Preview(showSystemUi = true)
@Composable
private fun ImageItemPreview() {
    ImageCachingTheme {
        ImageItem(Urls("", "", "", "", "", ""))
    }
}