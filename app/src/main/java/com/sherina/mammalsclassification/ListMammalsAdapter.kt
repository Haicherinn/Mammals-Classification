package com.sherina.mammalsclassification

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlin.collections.ArrayList


class ListMammalsAdapter(val listMammals: ArrayList<Mammals>) :
    RecyclerView.Adapter<ListMammalsAdapter.ViewHolder>(), Filterable {
    var listMammalsFilter: ArrayList<Mammals> = ArrayList()

    init {
        listMammalsFilter = listMammals
    }

    interface OnItemClick {
        fun onItemClicked(data: Mammals)
    }

    private lateinit var onItemClick: OnItemClick

    fun setOnItemClick(onItemClick: OnItemClick) {
        this.onItemClick = onItemClick
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtMammalsPhoto: ImageView = itemView.findViewById(R.id.tvMammalsPhoto)
        var txtMammalsName: TextView = itemView.findViewById(R.id.tvMammalsName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_dashboard, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mammals = listMammalsFilter[position]

        Glide.with(holder.itemView.context)
            .load(mammals.mammalsPhoto)
            .apply(RequestOptions().override(90, 90))
            .into(holder.txtMammalsPhoto)

        holder.txtMammalsName.text = mammals.mammalsName.trim()

        holder.itemView.setOnClickListener {
            onItemClick.onItemClicked(listMammalsFilter[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return listMammalsFilter.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint.toString()
                charString.trim()
                if (charString.isEmpty()) {
                    listMammalsFilter = listMammals
                } else {
                    val resultList = ArrayList<Mammals>()

                    listMammals.filter {
                        (it.mammalsName.toLowerCase()
                            .contains(constraint!!.trim())) or (it.mammalsInformation.toLowerCase()
                            .contains(constraint.trim()))
                    }.forEach {
                        resultList.add(it)
                    }
                    listMammalsFilter = resultList

                }
                return FilterResults().apply { values = listMammalsFilter }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                listMammalsFilter = if (results?.values == null)
                    ArrayList()
                else
                    results.values as ArrayList<Mammals>
                notifyDataSetChanged()
            }
        }
    }
}
