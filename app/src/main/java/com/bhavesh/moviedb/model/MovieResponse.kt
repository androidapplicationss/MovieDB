package com.bhavesh.moviedb.model


import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("page")
    var page: Int? = 0,
    @SerializedName("results")
    var getResults: List<MovieRequestModel?>? = listOf(),
    @SerializedName("total_results")
    var total_results: Int? = 0,
    @SerializedName("total_pages")
    var total_pages: Int? = 0,

)
{
data class MovieRequestModel(
    @SerializedName("title")
    val movie_title: String? = null,

    @SerializedName("poster_path")
    val movie_poster: String? = null,

    @SerializedName("id")
    val movie_id: Int? = 0
)

}