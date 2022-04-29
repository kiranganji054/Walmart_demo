package com.walmart.demo.ui.countries_list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.walmart.demo.R
import com.walmart.demo.model.Country
import com.walmart.demo.ui.countries_list.adapter.CountryListAdapter.CountryViewHolder

class CountryListAdapter(private val onClick: (Country) -> Unit) :
  ListAdapter<Country, CountryViewHolder>(FlowerDiffCallback) {

  /* ViewHolder for Country, takes in the inflated view and the onClick behavior. */
  class CountryViewHolder(
    itemView: View,
    val onClick: (Country) -> Unit
  ) :
    RecyclerView.ViewHolder(itemView) {

    private val countryName: TextView = itemView.findViewById(R.id.txtName)
    private val countryCapital: TextView = itemView.findViewById(R.id.txtCaptial)
    private val countryCode: TextView = itemView.findViewById(R.id.txtCode)
    private val countryRegion: TextView = itemView.findViewById(R.id.txtRegion)
    private var country: Country? = null

    init {
      itemView.setOnClickListener {
        country?.let {
          onClick(it)
        }
      }
    }

    /* Bind flower name and image. */
    fun bind(country: Country) {
      this.country = country

      countryName.text = country.name
      countryCapital.text = country.capital
      countryCode.text = country.code
      countryRegion.text = ",${country.region}"
    }
  }

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): CountryViewHolder {
    val view = LayoutInflater.from(parent.context)
      .inflate(R.layout.item_country, parent, false)
    return CountryViewHolder(view, onClick)
  }

  /* Gets current Country and uses it to bind view. */
  override fun onBindViewHolder(
    holder: CountryViewHolder,
    position: Int
  ) {
    val flower = getItem(position)
    holder.bind(flower)
  }
}

object FlowerDiffCallback : DiffUtil.ItemCallback<Country>() {
  override fun areItemsTheSame(
    oldItem: Country,
    newItem: Country
  ): Boolean {
    return oldItem == newItem
  }

  override fun areContentsTheSame(
    oldItem: Country,
    newItem: Country
  ): Boolean {
    return oldItem.name == newItem.name
  }
}