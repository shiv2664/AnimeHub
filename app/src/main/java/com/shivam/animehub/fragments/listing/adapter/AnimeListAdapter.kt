package com.shivam.animehub.fragments.listing.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shivam.animehub.databinding.ItemAnimeListingBinding
import com.shivam.animehub.fragments.listing.model.Data

class AnimeListAdapter(private val listener:MovieListListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var context: Context?=null
    private var dataList: MutableList<Data>? = mutableListOf()

    fun setContext(context: Context,list:List<Data>?){
        this.context=context
        this.dataList=list?.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val binding = ItemAnimeListingBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ListingItemViewHolder(binding)
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val listingItemViewHolder = holder as ListingItemViewHolder
        val item = dataList?.getOrNull(position)
        listingItemViewHolder.binding.apply {

            if (item != null) {
                context?.let { Glide.with(it).load(item.images.webp.large_image_url).into(poster) }
            }
            title.text=item?.title
            episodes.text="Episode ${item?.episodes.toString()}"
            ratings.text="Ratings ${item?.rating}"


           root.setOnClickListener {
                if (item != null) {
                    listener.onItemClick(item)
                }
            }
        }


    }

    override fun getItemCount(): Int {
        return dataList?.size?:0
    }

    interface MovieListListener{
        fun onItemClick(item: Data)
    }

    inner class ListingItemViewHolder( val binding: ItemAnimeListingBinding):
        RecyclerView.ViewHolder(binding.root)

}