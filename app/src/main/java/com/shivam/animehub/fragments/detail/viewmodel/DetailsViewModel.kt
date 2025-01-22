package com.shivam.animehub.fragments.detail.viewmodel

import androidx.lifecycle.ViewModel
import com.shivam.animehub.fragments.detail.model.DetailsModel
import com.shivam.animehub.repository.AnimeListingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: AnimeListingRepository):ViewModel() {

    suspend fun getAnimeDetails(url: String): DetailsModel?{
        return repository.getAnimeDetail(url)
    }
}