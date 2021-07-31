package castelles.com.github.wotv_app.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import castelles.com.github.wotv_app.BuildConfig
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("imageChar")
fun getImage(view: ImageView, path: String) {
    loadGlide(view, BuildConfig.IMAGE_URL + BuildConfig.AVATAR_PATH + path)
}

@BindingAdapter("imageRarity")
fun getRarity(view: ImageView, path: String) {
    loadGlide(view, BuildConfig.IMAGE_URL + BuildConfig.RARITY_PATH + path)
}

@BindingAdapter("imageElement")
fun getElement(view: ImageView, path: String) {
    loadGlide(view, BuildConfig.IMAGE_URL + BuildConfig.ELEMENT_PATH + path)
}

private fun loadGlide(view: ImageView, path: String) {
    val options = RequestOptions().apply {
        fitCenter()
    }

    Glide.with(view.context)
        .load(path)
        .apply(options)
        .into(view)
}