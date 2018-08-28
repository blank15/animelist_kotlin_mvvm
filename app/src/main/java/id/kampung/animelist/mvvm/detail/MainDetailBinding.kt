package id.kampung.animelist.mvvm.detail

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

object MainDetailBinding {
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun setImageUrl(imageView: ImageView, url: String?) {
        Glide.with(imageView.context).load(url).into(imageView)
    }
}