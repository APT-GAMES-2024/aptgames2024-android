package com.onemonth.aptgame.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.onemonth.aptgame.R
import com.onemonth.aptgame.databinding.VhShopAptCardBinding
import com.onemonth.aptgame.model.ShopModel


class ShopItemAdapter(val callback: ((ShopModel) -> Unit)? = null) :
    ListAdapter<ShopModel, ShopItemAdapter.ShopItemViewHolder>(Companion) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val dataBinding = VhShopAptCardBinding.inflate(
            layoutInflater,
            parent,
            false
        )

        return ShopItemViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val item = getItem(holder.absoluteAdapterPosition) ?: return
        holder.itemView.setOnClickListener {
            callback?.invoke(getItem(position))
        }
        holder.bindView(item)

    }

    class ShopItemViewHolder(val binding: VhShopAptCardBinding) : RecyclerView.ViewHolder(binding.root) {
            fun bindView(item: ShopModel) {

                //추후 수정
                Glide.with(binding.root.context).load(R.drawable.image_apt_1)
                    .into(binding.ivAptCard)

            }
        }

    companion object : DiffUtil.ItemCallback<ShopModel>() {
        override fun areItemsTheSame(
            oldItem: ShopModel,
            newItem: ShopModel
        ): Boolean {
            return oldItem.aptCardId == newItem.aptCardId
        }

        override fun areContentsTheSame(
            oldItem: ShopModel,
            newItem: ShopModel
        ): Boolean {
            return oldItem.aptCardId.hashCode() == newItem.aptCardId.hashCode()
        }
    }
}