package com.shivam.animehub.repository

import com.shivam.animehub.fragments.detail.model.DetailsModel
import com.shivam.animehub.fragments.listing.model.AnimeModel
import com.shivam.animehub.utility.ApiInterface
import javax.inject.Inject

class AnimeListingRepository @Inject constructor(private val apiInterface: ApiInterface) {

    suspend fun getAnimeListing(url: String): AnimeModel?{
        val result=apiInterface.getAnimeList(url)
        if (result.isSuccessful){
            return result.body()
        }
        return null
    }

    suspend fun getAnimeDetail(url: String): DetailsModel?{
        val result=apiInterface.getAnimeDetail(url)
        if (result.isSuccessful){
            return result.body()
        }
        return null
    }




}