package com.example.strangernews.data.model.error_response

import com.google.gson.annotations.SerializedName

data class ErrorData(
    @SerializedName("code")
    var code: String?,
    @SerializedName("message")
    var message: String?
)
