package com.hypernova.appteam2ndtask.ApiClasses

import com.google.gson.annotations.SerializedName

data class Instructions(

    @SerializedName("name") var name: String,
    @SerializedName("steps") var steps: List<Steps>
)

data class Steps(
    @SerializedName("number") var number: Int,
    @SerializedName("step") var step: String
)
