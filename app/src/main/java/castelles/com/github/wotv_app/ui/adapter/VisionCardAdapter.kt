package castelles.com.github.wotv_app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import castelles.com.github.api.model.VisionCard
import castelles.com.github.wotv_app.databinding.ItemVisionCardBinding

class VisionCardAdapter(
    val items: MutableList<VisionCard> = mutableListOf(),
    private val onClickItem: (item: VisionCard) -> Unit
): RecyclerView.Adapter<VisionCardAdapter.VisionCardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VisionCardViewHolder =
        with(LayoutInflater.from(parent.context)) {
            VisionCardViewHolder(ItemVisionCardBinding.inflate(this, parent, false))
        }

    override fun onBindViewHolder(holder: VisionCardViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, onClickItem)
    }

    override fun getItemCount(): Int = items.size

    override fun getItemId(position: Int): Long = items[position].id.toLong()

    inner class VisionCardViewHolder(private val binding: ItemVisionCardBinding):
            RecyclerView.ViewHolder(binding.root) {

                fun bind(item: VisionCard, onClickItem: (item: VisionCard) -> Unit) {
                    with(binding) {
                        visionCard = item
                        root.setOnClickListener {
                            onClickItem.invoke(item)
                        }
                    }
                }

            }
}