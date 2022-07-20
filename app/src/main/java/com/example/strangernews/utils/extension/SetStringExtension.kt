package com.example.strangernews.utils.extension

fun Set<String>.toQueryString(): String{
    val separator = ", "
    var queryString = ""
    this.forEach{
        queryString+= it
        queryString+=separator
    }
    return queryString.dropLast(separator.length)
}