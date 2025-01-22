package com.shivam.animehub.fragments.detail.model

data class DetailsModel(
    val data: Data
)

data class Aired(
    val from: String,
    val prop: Prop,
    val string: String,
    val to: String
)

data class Broadcast(
    val day: String,
    val string: String,
    val time: String,
    val timezone: String
)

data class Data(
    val aired: Aired,
    val airing: Boolean,
    val approved: Boolean,
    val background: String,
    val broadcast: Broadcast,
    val demographics: List<Demographic>,
    val duration: String,
    val episodes: Int,
    val explicit_genres: List<Any>,
    val favorites: Int,
    val genres: List<Genre>,
    val images: Images,
    val licensors: List<Licensor>,
    val mal_id: Int,
    val members: Int,
    val popularity: Int,
    val producers: List<Producer>,
    val rank: Int,
    val rating: String,
    val score: Double,
    val scored_by: Int,
    val season: String,
    val source: String,
    val status: String,
    val studios: List<Studio>,
    val synopsis: String,
    val themes: List<Any>,
    val title: String,
    val title_english: String,
    val title_japanese: String,
    val title_synonyms: List<String>,
    val titles: List<Title>,
    val trailer: Trailer,
    val type: String,
    val url: String,
    val year: Int
)

data class Demographic(
    val mal_id: Int,
    val name: String,
    val type: String,
    val url: String
)

data class From(
    val day: Int,
    val month: Int,
    val year: Int
)

data class Genre(
    val mal_id: Int,
    val name: String,
    val type: String,
    val url: String
)

data class Images(
    val jpg: Jpg,
    val webp: Webp
)

data class ImagesX(
    val image_url: String,
    val large_image_url: String,
    val maximum_image_url: String,
    val medium_image_url: String,
    val small_image_url: String
)

data class Licensor(
    val mal_id: Int,
    val name: String,
    val type: String,
    val url: String
)

data class Jpg(
    val image_url: String,
    val large_image_url: String,
    val small_image_url: String
)

data class Producer(
    val mal_id: Int,
    val name: String,
    val type: String,
    val url: String
)

data class Prop(
    val from: From,
    val to: To
)

data class Studio(
    val mal_id: Int,
    val name: String,
    val type: String,
    val url: String
)

data class Title(
    val title: String,
    val type: String
)

data class To(
    val day: Int,
    val month: Int,
    val year: Int
)


data class Trailer(
    val embed_url: String,
    val images: ImagesX,
    val url: String,
    val youtube_id: String
)

data class Webp(
    val image_url: String,
    val large_image_url: String,
    val small_image_url: String
)