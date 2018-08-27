package id.kampung.animelist.data.model

data  class TopModel(
        var request_hash : String,
        var request_cached : String,
        var top : List<DetailModel>
)