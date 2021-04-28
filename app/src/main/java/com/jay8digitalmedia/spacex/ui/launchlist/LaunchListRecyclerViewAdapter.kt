package com.jay8digitalmedia.spacex.ui.launchlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jay8digitalmedia.spacex.databinding.LaunchListItemBinding
import com.jay8digitalmedia.spacex.vo.Launch
import java.util.*

class LaunchListRecyclerViewAdapter : RecyclerView.Adapter<LaunchListRecyclerViewAdapter.LaunchViewHolder>() {

    var launches = emptyList<Launch>()
        set(value) {
            field = value
            updateList()
        }

    private var processedLaunches = emptyList<Launch>()

    var sortByDate: Boolean = true
        set(value) {
            field = value
            updateList()
        }

    var filterBySuccess: Boolean = false
        set(value) {
            field = value
            updateList()
        }

    var onItemClick: ((id: String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
        val view = LaunchListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return LaunchViewHolder(view)
    }

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
        val launch = processedLaunches[position]
        holder.binding.also {
            it.launch = launch
        }
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(launch?.id)
        }
    }

    override fun getItemCount(): Int = processedLaunches.size

    private fun updateList() {
        val sortedLaunches = if (sortByDate) {
            launches.sortedBy {
                val cal = Calendar.getInstance()
                cal.time = it.date
                cal.get(Calendar.YEAR)
            }
        } else {
            launches.sortedBy {
                it.name
            }
        }

        processedLaunches = if (filterBySuccess) {
            sortedLaunches.filter { it.success }
        } else {
            sortedLaunches
        }


        notifyDataSetChanged()
    }

    class LaunchViewHolder(val binding: LaunchListItemBinding) : RecyclerView.ViewHolder(binding.root)
}