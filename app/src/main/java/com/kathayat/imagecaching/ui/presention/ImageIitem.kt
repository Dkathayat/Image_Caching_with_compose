package com.kathayat.imagecaching.ui.presention

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kathayat.imagecaching.R
import com.kathayat.imagecaching.network.remote.Urls
import com.kathayat.imagecaching.ui.theme.ImageCachingTheme

@Composable
fun ImageItem(urls: Urls) {
    Image(
        painter = painterResource(R.drawable.testimg),
        modifier = Modifier.fillMaxSize()
            .padding(20.dp)
            .clip(RoundedCornerShape(10)),
        contentDescription = "testimages",
        contentScale = ContentScale.FillBounds
    )
}


@Preview(showSystemUi = true)
@Composable
private fun ImageItemPreview() {
    ImageCachingTheme {
        ImageItem(Urls("", "", "", "", "", ""))
    }
}