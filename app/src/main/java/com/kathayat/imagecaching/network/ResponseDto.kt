package com.kathayat.imagecaching.network


class ResponseDto<T : Any?> {
   // @SerializedName("results")
    val results: T? = null

   // @SerializedName("page")
    val page: Int? = null

   // @SerializedName("total_pages")
    val total_Pages: Int? = null

  //  @SerializedName("total_results")
    val total_results: Int? = null
}