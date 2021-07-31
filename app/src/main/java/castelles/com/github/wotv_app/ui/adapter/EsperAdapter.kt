package castelles.com.github.wotv_app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import castelles.com.github.api.model.Esper
import castelles.com.github.wotv_app.databinding.ItemEsperBinding

class EsperAdapter(
    val items: MutableList<Esper> = mutableListOf(),
    private val onClick: (item: Esper) -> Unit
): RecyclerView.Adapter<EsperAdapter.EsperViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EsperViewHolder =
        with(LayoutInflater.from(parent.context)) {
            EsperViewHolder(ItemEsperBinding.inflate(this, parent, false))
        }


    override fun onBindViewHolder(holder: EsperViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, onClick)
    }

    override fun getItemCount(): Int = items.size

    override fun getItemId(position: Int): Long = items[position].id.toLong()

    inner class EsperViewHolder(private val binding: ItemEsperBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Esper, onClick: (item: Esper) -> Unit) {
            with(binding) {
                esper = item
                root.setOnClickListener {
                    onClick.invoke(item)
                }
            }
        }

    }
}