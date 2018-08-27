package id.kampung.animelist.mvvm.main

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import id.kampung.animelist.R
import id.kampung.animelist.data.model.DetailModel
import id.kampung.animelist.databinding.ItemMainBinding

class AdapterList(private var model : List<DetailModel>,private var userActionListener: MainItemClickAction) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val mainItemBinding: ItemMainBinding = DataBindingUtil.inflate(LayoutInflater.from(parent!!.context),
                R.layout.item_main, parent, false)

        return MainItemHolder(mainItemBinding)
    }

    override fun getItemCount(): Int {
        println("ukuran list ${model.size}")
        return model.size
    }
    fun setList(detailModel: List<DetailModel>) {
        this.model = detailModel

        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val item = model[position]


        (holder as MainItemHolder).bindItem(item, userActionListener )

    }
    class MainItemHolder(itemView: ItemMainBinding) : RecyclerView.ViewHolder(itemView.root) {
        private val mainItemBinding = itemView

        //==========================================================================================

        fun bindItem(data: DetailModel, userActionListener: MainItemClickAction) {
            mainItemBinding.item = data
            mainItemBinding.userActionListener = userActionListener
            mainItemBinding.executePendingBindings()
        }
    }
}