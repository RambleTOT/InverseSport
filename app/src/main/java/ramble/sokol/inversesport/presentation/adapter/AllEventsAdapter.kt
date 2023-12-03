package ramble.sokol.inversesport.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ramble.sokol.inversesport.databinding.ItemEventBinding
import ramble.sokol.inversesport.model.entity.GetAllEvents

class AllEventsAdapter (
    private val eventsList: List<GetAllEvents>
): RecyclerView.Adapter<AllEventsAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemEventBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return eventsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = eventsList[position]
        holder.binding.apply {
            titleEvent.text = currentItem.name
            placeDateEvent.text = "${currentItem.date} · ${currentItem.platform!!.address}"
            Picasso.get().load("https://inverse-tracker.store/${currentItem.cover}").into(imageEvent);
        }
    }

}