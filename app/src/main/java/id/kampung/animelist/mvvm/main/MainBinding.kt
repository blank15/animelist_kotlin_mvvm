package id.kampung.animelist.mvvm.main

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView

object MainBinding {
    @BindingAdapter("adapter")
    @JvmStatic
    fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
        view.adapter = adapter
    }
}