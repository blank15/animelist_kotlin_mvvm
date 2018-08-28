package id.kampung.animelist.mvvm.main

import id.kampung.animelist.data.model.DetailModel

interface MainItemClickAction {

    fun onItemClicked(detailModel: DetailModel)

}