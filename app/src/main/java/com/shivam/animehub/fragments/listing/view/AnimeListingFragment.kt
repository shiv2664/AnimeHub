package com.shivam.animehub.fragments.listing.view

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
            viewLifecycleOwner.lifecycleScope.launch{
                val result=viewModel.getAnimeListing(url)
                animeListAdapter?.setContext(requireContext(),result?.data)
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
}
