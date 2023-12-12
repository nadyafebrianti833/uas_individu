import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.DetailActivity
import com.example.recyclerview.R
import com.example.recyclerview.video

class ListAdapter(private val context: Context, private val videolist: List<video>) : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private val mediaPlayer: MediaPlayer = MediaPlayer.create(context, R.raw.sound)

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gambar: ImageView = itemView.findViewById(R.id.img_view)
        val judulvideo: TextView = itemView.findViewById(R.id.tv_judul_video)
        val deskripsi: TextView = itemView.findViewById(R.id.tv_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemView =
            LayoutInflater.from(context).inflate(R.layout.item_recycler_view, parent, false)
        return ListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (gambar, judul, deskripsi) = videolist[position]
        holder.gambar.setImageResource(gambar)
        holder.judulvideo.text = judul
        holder.deskripsi.text = deskripsi

        holder.itemView.setOnClickListener {
            // Play click sound
            mediaPlayer.start()

            val intentDetail = Intent(context, DetailActivity::class.java)
            intentDetail.putExtra("shadow", videolist[holder.adapterPosition])
            context.startActivity(intentDetail)
        }
    }

    override fun getItemCount(): Int = videolist.size
}
