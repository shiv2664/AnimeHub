package com.shivam.animehub.fragments.listing.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import com.shivam.animehub.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.shivam.animehub.fragments.listing.adapter.AnimeListAdapter
import com.shivam.animehub.databinding.FragmentAnimeListingBinding
import com.shivam.animehub.fragments.listing.model.Data
import com.shivam.animehub.fragments.listing.viewmodel.AnimeListingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AnimeListingFragment : Fragment() {

    private var _binding: FragmentAnimeListingBinding?=null
    private val binding get()=_binding!!

    private val viewModel: AnimeListingViewModel by viewModels()
    private var animeListAdapter:AnimeListAdapter?=null
    private val url = "https://api.jikan.moe/v4/top/anime"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (_binding==null){
            _binding=FragmentAnimeListingBinding.inflate(inflater,container,false)

            animeListAdapter= AnimeListAdapter(object :AnimeListAdapter.MovieListListener{
                override fun onItemClick(item: Data) {

                    val bundle = Bundle()
                    bundle.putString("id", item.mal_id.toString())
                    findNavController().navigate(R.id.action_animeListingFragment_to_animeDetailFragment,bundle)

                }
            })

            initRecyclerView()
            if (isNetworkAvailable(requireContext())){
                viewLifecycleOwner.lifecycleScope.launch{
                    val result=viewModel.getAnimeListing(url)
                    animeListAdapter?.setContext(requireContext(),result?.data)
                }

            }


            return binding.root

        }else{
            return binding.root

        }
    }

    private fun initRecyclerView() {

        val linearLayoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        binding. moviesRecyclerview.adapter = animeListAdapter
        binding.moviesRecyclerview.layoutManager=linearLayoutManager

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val activeNetwork =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                // For other devices which may have different transports
                else -> false
            }
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo?.isConnected == true
        }
    }

}
