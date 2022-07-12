package com.example.strangernews.data.model.error_response

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("error")
    var error: ErrorData?
)
