package com.app.musicplayer.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.SeekBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.musicplayer.R
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.BlurTransformation
import java.time.Duration

class PlayerFragment : Fragment() {

    private var songNameList = mutableListOf<String>()
    private var artistNameList = mutableListOf<String>()
    private var durationList = mutableListOf<String>()
    private var albumCoverList = mutableListOf<Int>()
    private var handlePlay:Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val parentImage = view.findViewById<ImageView>(R.id.parent_img)
        val childImage = view.findViewById<ImageView>(R.id.childImageView)
        val songTitle = view.findViewById<TextView>(R.id.song_title)
        val progress = view.findViewById<ProgressBar>(R.id.progress)
        val playPauseBtn = view.findViewById<ImageButton>(R.id.playPauseButton)
        val rewindBtn = view.findViewById<ImageButton>(R.id.rewindButton)
        val seekBar = view.findViewById<SeekBar>(R.id.seekBar)
        val fastForwardBtn = view.findViewById<ImageButton>(R.id.fastForwardButton)
        val songsDetailsView = view.findViewById<ConstraintLayout>(R.id.song_details_view)
        val recylerViewSongs = view.findViewById<RecyclerView>(R.id.recycler_view)

        songNameList.add("Havana")
        songNameList.add("All we know")
        songNameList.add("Sunflower")
        songNameList.add("8 Legged dreams")
        songNameList.add("Counting stars")
        songNameList.add("Legends never dies")
        songNameList.add("Blinding lights")
        songNameList.add("Wolves")
        songNameList.add("Harley's in hawaii")

        artistNameList.add("Camila")
        artistNameList.add("Chain smokers")
        artistNameList.add("Post malone")
        artistNameList.add("Unlike pluto")
        artistNameList.add("One Republic")
        artistNameList.add("M.A.K.O")
        artistNameList.add("Weekend")
        artistNameList.add("Marshmallow")
        artistNameList.add("Katty perry")

        durationList.add("4:13")
        durationList.add("5:00")
        durationList.add("6:23")
        durationList.add("4:35")
        durationList.add("2:38")
        durationList.add("7:00")
        durationList.add("3:38")
        durationList.add("4:46")
        durationList.add("1:03")

        albumCoverList.add(R.drawable.havana)
        albumCoverList.add(R.drawable.all_we_know)
        albumCoverList.add(R.drawable.sunflower)
        albumCoverList.add(R.drawable.dreams_unlike_pluto)
        albumCoverList.add(R.drawable.native_one_republic)
        albumCoverList.add(R.drawable.legends_never_dies)
        albumCoverList.add(R.drawable.weekend)
        albumCoverList.add(R.drawable.wolves)
        albumCoverList.add(R.drawable.katty)


        songTitle.text = songNameList[0]

        Picasso.get()
            .load(albumCoverList[0])
            .transform(BlurTransformation(context))
            .into(parentImage)

        Picasso.get()
            .load(albumCoverList[0])
            .into(childImage)


        val dividerItemDecoration = DividerItemDecoration(recylerViewSongs.context, LinearLayoutManager.VERTICAL)
        recylerViewSongs.addItemDecoration(dividerItemDecoration)
        recylerViewSongs.layoutManager = LinearLayoutManager(activity)

        val myAdapter = MyAdapter(songNameList,artistNameList,durationList,albumCoverList)


        playPauseBtn.setOnClickListener(View.OnClickListener {
            if(handlePlay){
                handlePlay = false
                playPauseBtn.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.pause))
            }else{
                handlePlay = true
                playPauseBtn.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.play))
            }
        })

        fastForwardBtn.setOnClickListener(View.OnClickListener {
            val currentProgress = seekBar.progress
            if (currentProgress < seekBar.max) {
                seekBar.progress = currentProgress + 10
            }
        })

        rewindBtn.setOnClickListener(View.OnClickListener {
            val currentProgress = seekBar.progress
            if (currentProgress > 0) {
                seekBar.progress = currentProgress - 10
            }
        })

        myAdapter.setOnItemClickListener(object: MyAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {

                progress.visibility = View.VISIBLE
                songsDetailsView.visibility = View.GONE

                val handler = Handler(Looper.getMainLooper())
                val runnable = Runnable{
                    progress.visibility = View.GONE
                    songsDetailsView.visibility = View.VISIBLE
                }

                handler.postDelayed(runnable,2000)

                songTitle.text = songNameList[position]

                Picasso.get()
                    .load(albumCoverList[position])
                    .transform(BlurTransformation(context))
                    .into(parentImage)

                Picasso.get()
                    .load(albumCoverList[position])
                    .into(childImage)
            }

        })
        recylerViewSongs.adapter = myAdapter


    }

    class MyAdapter(private var songName:List<String>,private var artistName:List<String>,
                    private var duration: List<String>, private var albumCover: List<Int>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
        private var listener: OnItemClickListener? = null

        inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val artistName: TextView = itemView.findViewById(R.id.artist_text_view)
            val albumImg: ImageView = itemView.findViewById(R.id.album_cover_image_view)
            val songName: TextView = itemView.findViewById(R.id.song_name_text_view)
            val duration: TextView = itemView.findViewById(R.id.duration_text_view)
            init {
                itemView.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        listener?.onItemClick(position)
                    }
                }
            }
        }

        fun setOnItemClickListener(listener: OnItemClickListener) {
            this.listener = listener
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.songs_list_item_view, parent, false)
            return MyViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.songName.text = songName[position]
            holder.artistName.text = artistName[position]
            holder.duration.text = duration[position]

            Picasso.get()
                .load(albumCover[position])
                .into(holder.albumImg)
        }

        override fun getItemCount(): Int {
            return songName.size
        }
        interface OnItemClickListener {
            fun onItemClick(position: Int)
        }
    }


}