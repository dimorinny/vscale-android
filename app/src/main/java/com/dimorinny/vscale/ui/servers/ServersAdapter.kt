package com.dimorinny.vscale.ui.servers

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dimorinny.vscale.R
import com.dimorinny.vscale.db.entity.ServerEntity
import com.dimorinny.vscale.dependency.bindView
import de.hdodenhof.circleimageview.CircleImageView
import java.util.*

/**
 * Created by Dimorinny on 31.12.15.
 */
public class ServersAdapter : RecyclerView.Adapter<ServersAdapter.ViewHolder>() {

    var servers : List<ServerEntity> = ArrayList()
        set(s : List<ServerEntity>) {
            field = s
            notifyDataSetChanged()
        }

    public class ViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        val serverName : TextView by bindView(R.id.item_server_name)
        val hostName : TextView by bindView(R.id.item_server_hostname)
        val image : CircleImageView by bindView(R.id.item_server_image)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val server = servers[position]

        holder.hostName.text = server.hostName
        holder.serverName.text = server.name
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_server, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return servers.size
    }
}