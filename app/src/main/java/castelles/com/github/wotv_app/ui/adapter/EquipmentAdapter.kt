package castelles.com.github.wotv_app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import castelles.com.github.api.model.Equipments
import castelles.com.github.wotv_app.databinding.ItemEquipmentBinding
import castelles.com.github.wotv_app.databinding.ItemEsperBinding

class EquipmentAdapter(
    val items: MutableList<Equipments> = mutableListOf(),
    private val onClickItem: (item: Equipments) -> Unit
): RecyclerView.Adapter<EquipmentAdapter.EquipmentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipmentViewHolder =
        with(LayoutInflater.from(parent.context)) {
            EquipmentViewHolder(ItemEquipmentBinding.inflate(this, parent, false))
        }

    override fun onBindViewHolder(holder: EquipmentViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, onClickItem)
    }

    override fun getItemCount(): Int = items.size

    override fun getItemId(position: Int): Long = items[position].id.toLong()

    inner class EquipmentViewHolder(private val binding: ItemEquipmentBinding):
            RecyclerView.ViewHolder(binding.root) {

                fun bind(item: Equipments, onClickItem: (item: Equipments) -> Unit) {
                    with(binding) {
                        equip = item
                        root.setOnClickListener {
                            onClickItem.invoke(item)
                        }
                    }
                }
            }

}