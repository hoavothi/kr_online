package com.example.hoavot.karaokeonline.ui.feed

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.hoavot.karaokeonline.R
import com.example.hoavot.karaokeonline.data.model.other.Feed
import com.example.hoavot.karaokeonline.ui.extensions.underlineText
import org.jetbrains.anko.AnkoContext


/**
 *
 * @author at-hoavo.
 */
class FeedAdapter(private val feeds: MutableList<Feed>) : RecyclerView.Adapter<FeedAdapter.FeedHolder>() {
    override fun onBindViewHolder(holder: FeedHolder?, position: Int) {
        holder?.onBind()
    }

    override fun getItemCount() = feeds.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedHolder {
        val ui = FeedHolderUI()
        return FeedHolder(ui, ui.createView(AnkoContext.Companion.create(parent.context, parent, false)))
    }

    inner class FeedHolder(private val ui: FeedHolderUI, private val item: View) : RecyclerView.ViewHolder(item) {
        private val option = RequestOptions()
                .centerCrop()
                .override(ui.avatar.width, ui.avatar.width)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE) // https://github.com/bumptech/glide/issues/319
                .placeholder(R.drawable.bg_item_place_holder)

        fun onBind() {
            ui.comments = feeds[layoutPosition].comments
            Glide.with(item)
                    .asBitmap()
                    .load(feeds[layoutPosition].avatar)
                    .apply(option)
                    .transition(BitmapTransitionOptions.withCrossFade())
                    .into(ui.avatar)
            ui.userName.text = feeds[layoutPosition].userName
            ui.countLike.text = feeds[layoutPosition].likeCount.toInt().toString()
            ui.countComment.text = feeds[layoutPosition].commentCount.toInt().toString().plus(" comment").underlineText {}
            ui.captionArea.text = feeds[layoutPosition].caption
        }
    }
}
