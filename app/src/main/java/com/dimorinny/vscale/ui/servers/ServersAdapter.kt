package com.dimorinny.vscale.ui.servers

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dimorinny.vscale.R
import com.dimorinny.vscale.db.entity.ServerEntity
import com.dimorinny.vscale.dependency.bindView
import com.dimorinny.vscale.util.ImageUtils
import com.dimorinny.vscale.util.StatusUtils
import de.hdodenhof.circleimageview.CircleImageView
import java.util.*

/**
 * Created by Dimorinny on 31.12.15.
 */
public class ServersAdapter(val context: Context) : RecyclerView.Adapter<ServersAdapter.ViewHolder>() {

    var servers : List<ServerEntity> = ArrayList()
        set(s : List<ServerEntity>) {
            field = s
            notifyDataSetChanged()
        }

    public class ViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        val serverName : TextView by bindView(R.id.item_server_name)
        val hostName : TextView by bindView(R.id.item_server_hostname)
        val location : TextView by bindView(R.id.item_server_location)
        val status : TextView by bindView(R.id.item_server_status)
        val image : CircleImageView by bindView(R.id.item_server_image)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val server = servers[position]
        val image = ImageUtils.imageByMadeFrom(server.madeFrom)

        holder.image.setImageDrawable(context.getDrawable(image))
        holder.hostName.text = server.hostName
        holder.serverName.text = server.name
        holder.location.text = server.locations
        holder.status.text = context.getString(StatusUtils.getStatus(server.status))
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_server, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return servers.size
    }
}