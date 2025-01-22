package com.shivam.animehub.fragments.listing.viewmodel

import androidx.lifecycle.ViewModel
import com.shivam.animehub.fragments.listing.model.AnimeModel
import com.shivam.animehub.repository.AnimeListingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnimeListingViewModel @Inject constructor(private val repository: AnimeListingRepository) :
    ViewModel() {

    suspend fun getAnimeListing(url: String): AnimeModel? {
        return repository.getAnimeListing(url)
    }

}