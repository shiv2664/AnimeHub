package com.shivam.animehub.utility

import com.shivam.animehub.fragments.detail.model.DetailsModel
import com.shivam.animehub.fragments.listing.model.AnimeModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiInterface {

    @GET
    suspend fun getAnimeList(@Url url:String):Response<AnimeModel?>

    @GET
    suspend fun getAnimeDetail(@Url url:String):Response<DetailsModel?>

}