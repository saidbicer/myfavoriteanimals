package com.said.myfavoriteanimals.data.model

import com.google.gson.annotations.SerializedName

data class AnimalModel(
    @SerializedName("message")
    var imgUrl: String?,
    @SerializedName("status")
    var status: String?,
)