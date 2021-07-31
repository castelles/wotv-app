package castelles.com.github.wotv_app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import castelles.com.github.api.model.Character
import castelles.com.github.wotv_app.databinding.ItemCharBinding

class CharacterAdapter(
    val items: MutableList<Character> = mutableListOf(),
    private val onClick: (item: Character) -> Unit
): RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder =
        with(LayoutInflater.from(parent.context)) {
            CharacterViewHolder(ItemCharBinding.inflate(this, parent, false))
        }


    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, onClick)
    }

    override fun getItemCount(): Int = items.size

    override fun getItemId(position: Int): Long = items[position].id.toLong()

    inner class CharacterViewHolder(private val binding: ItemCharBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(item: Character, onClick: (item: Character) -> Unit) {
                with(binding) {
                    character = item
                    root.setOnClickListener {
                        onClick.invoke(item)
                    }
                }
            }

    }
}