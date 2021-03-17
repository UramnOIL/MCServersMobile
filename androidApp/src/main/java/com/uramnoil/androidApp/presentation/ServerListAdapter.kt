package com.uramnoil.androidApp.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.uramnoil.androidApp.R
import com.uramnoil.androidApp.databinding.ListServerItemBinding
import com.uramnoil.shared.model.entity.Server


class ServerListAdapter(private val context: Context, @LayoutRes private val resource: Int, private val servers: List<Server>) : BaseAdapter() {

    data class ViewHolder(val serverNameView: TextView, val playerCountView: TextView)

    private val inflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return servers.count()
    }

    override fun getItem(position: Int) = servers[position]

    override fun getItemId(position: Int) = servers[position].id.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return convertView ?: ListServerItemBinding.inflate(inflater).apply {
            val item = getItem(position)
            serverName.text = item.name
            item.latestPing.run {
                if (maxPlayer != null && currentPlayer != null) playerCount.text = context.getString(R.string.player_counter, currentPlayer, maxPlayer)
            }
        }.root
    }
}