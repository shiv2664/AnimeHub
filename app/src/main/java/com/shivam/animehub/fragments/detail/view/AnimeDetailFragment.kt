package com.shivam.animehub.fragments.detail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.shivam.animehub.databinding.FragmentAnimeDetailBinding
import com.shivam.animehub.databinding.FragmentAnimeListingBinding
import com.shivam.animehub.fragments.detail.viewmodel.DetailsViewModel
import com.shivam.animehub.fragments.listing.adapter.AnimeListAdapter
import com.shivam.animehub.fragments.listing.viewmodel.AnimeListingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.getValue

@AndroidEntryPoint
class AnimeDetailFragment : Fragment() {
    private var _binding: FragmentAnimeDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailsViewModel by viewModels()
    private val url = "https://api.jikan.moe/v4/top/anime"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (_binding == null) {
            _binding = FragmentAnimeDetailBinding.inflate(inflater, container, false)

            val id = arguments?.getString("id")

            binding.ivBack.setOnClickListener {
                findNavController().navigateUp()
            }

            viewLifecycleOwner.lifecycleScope.launch {
                val details = viewModel.getAnimeDetails("https://api.jikan.moe/v4/anime/$id")
                Glide.with(requireContext()).load(details?.data?.images?.webp?.large_image_url)
                    .into(binding.posterImageView)
                binding.titleTextView.text = details?.data?.title
                binding.ratingTextView.text = details?.data?.rating
                binding.descTextView.text = details?.data?.synopsis

                binding.youtubePlayerView.addYouTubePlayerListener(object : YouTubePlayerListener {
                    override fun onApiChange(youTubePlayer: YouTubePlayer) {

                    }

                    override fun onCurrentSecond(
                        youTubePlayer: YouTubePlayer,
                        second: Float
                    ) {

                    }

                    override fun onError(
                        youTubePlayer: YouTubePlayer,
                        error: PlayerConstants.PlayerError
                    ) {

                    }

                    override fun onPlaybackQualityChange(
                        youTubePlayer: YouTubePlayer,
                        playbackQuality: PlayerConstants.PlaybackQuality
                    ) {

                    }

                    override fun onPlaybackRateChange(
                        youTubePlayer: YouTubePlayer,
                        playbackRate: PlayerConstants.PlaybackRate
                    ) {

                    }

                    override fun onReady(youTubePlayer: YouTubePlayer) {

                        youTubePlayer.loadVideo(details?.data?.trailer?.youtube_id.toString(), 0f)

                    }

                    override fun onStateChange(
                        youTubePlayer: YouTubePlayer,
                        state: PlayerConstants.PlayerState
                    ) {

                    }

                    override fun onVideoDuration(
                        youTubePlayer: YouTubePlayer,
                        duration: Float
                    ) {

                    }

                    override fun onVideoId(
                        youTubePlayer: YouTubePlayer,
                        videoId: String
                    ) {

                    }

                    override fun onVideoLoadedFraction(
                        youTubePlayer: YouTubePlayer,
                        loadedFraction: Float
                    ) {

                    }
                })


            }

            return binding.root
        }else{
            return binding.root
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}
