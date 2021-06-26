package com.hypernova.appteam2ndtask.ApiClasses

data class SearchResult (
    var results : List<Results>
)

data class Results (
    var id : Int,
    var title : String,
    var image : String,
    var imageType : String
)