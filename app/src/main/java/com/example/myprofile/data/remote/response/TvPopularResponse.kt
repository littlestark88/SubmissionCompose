package com.example.myprofile.data.remote.response

import com.google.gson.annotations.SerializedName

data class TvPopularResponse(

	@field:SerializedName("results")
	val tvPopularListItem: List<TvPopularListItem>
)