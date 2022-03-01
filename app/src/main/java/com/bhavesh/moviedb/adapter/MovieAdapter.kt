package com.bhavesh.moviedb.adapter

import android.content.Context
import android.util.Log
import android.view.*
import android.view.View.inflate
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.Toast
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.recyclerview.widget.RecyclerView
import com.bhavesh.moviedb.R
import com.bhavesh.moviedb.model.MovieResponse
import com.bumptech.glide.Glide
import com.card.visitingcardscanner.utils.Constant
import kotlinx.android.synthetic.main.item_movie_list.view.*

class MovieAdapter(var mActivity: Context? = null, private val mList: List<MovieResponse.MovieRequestModel?>?) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(mActivity).inflate(
                R.layout.item_movie_list,
                parent,
                false
            )
        )
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                Toast.makeText(mActivity, mList?.get(position)?.movie_title, Toast.LENGTH_SHORT).show()

            }
        }
    }
    open fun setAnimation(viewToAnimate: View) {
        val anim = ScaleAnimation(0.0f, 1.0f, 0.1f, 1.0f
            , Animation.RELATIVE_TO_PARENT, 0.5f,
            Animation.RELATIVE_TO_PARENT, 0.5f
        )
        anim.duration = 1000
        viewToAnimate.startAnimation(anim)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        setAnimation(holder.itemView)
        val ItemsViewModel = mList!![position]
        Glide.with(mActivity!!).load(
            Constant.IMAGEPATH + ItemsViewModel?.movie_poster
        ).placeholder(
            R.drawable.placeholder
        ).into(holder.itemView.imgMovie)
        holder.itemView.tvTitle.text = ItemsViewModel?.movie_title


    }

    override fun getItemCount(): Int {
        return mList?.size!!
    }

}
