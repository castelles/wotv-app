package castelles.com.github.wotv_app.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import castelles.com.github.wotv_app.BuildConfig
import castelles.com.github.wotv_app.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("imageChar")
fun getImage(view: ImageView, char: String) {
    loadCrop(view, BuildConfig.IMAGE_URL + BuildConfig.AVATAR_PATH + char)
}

@BindingAdapter("imageEsper")
fun getEsperImage(view: ImageView, esper: String) {
    loadFit(view, BuildConfig.IMAGE_URL + BuildConfig.ESPER_PATH + esper)
}

@BindingAdapter("imageVisionCard")
fun getVisionCardImage(view: ImageView, visionCard: String) {
    loadCrop(view, BuildConfig.IMAGE_URL + BuildConfig.VISION_CARD_PATH + visionCard)
}

@BindingAdapter("imageEquip")
fun getEquipment(view: ImageView, equipment: String) {
    loadFit(view, BuildConfig.IMAGE_URL + BuildConfig.EQUIPMENT_PATH + equipment)
}

@BindingAdapter("imageRarity")
fun getRarity(view: ImageView, rarity: String) {
    loadFit(view, BuildConfig.IMAGE_URL + BuildConfig.RARITY_PATH + rarity)
}

@BindingAdapter("imageElement")
fun getElement(view: ImageView, element: String?) {
    element?.let {
        loadFit(view, BuildConfig.IMAGE_URL + BuildConfig.ELEMENT_PATH + element)
    }
}

private fun loadFit(view: ImageView, path: String) {
    val options = RequestOptions().apply {
        fitCenter()
    }

    Glide
        .with(view.context)
        .load(path)
        .placeholder(view.context.resources.getDrawable(R.drawable.ic_launcher, null))
        .apply(options)
        .into(view)
}

private fun loadCrop(view: ImageView, path: String) {
    val options = RequestOptions().apply {
        centerCrop()
    }

    Glide
        .with(view.context)
        .load(path)
        .placeholder(view.context.resources.getDrawable(R.drawable.ic_launcher, null))
        .apply(options)
        .into(view)
}