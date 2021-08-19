package com.nytimes.homeModule.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.nytimes.R
import com.nytimes.homeModule.interfaces.IMostPopularClickEvent
import com.nytimes.homeModule.model.MostPopular
import com.squareup.picasso.Picasso

class MostPopularAdapter(
    private val mostPopularArrayList: ArrayList<MostPopular>, private val iMostPopularClickEvent: IMostPopularClickEvent) : RecyclerView.Adapter<MostPopularAdapter.CustomViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_most_popular, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val mostPopular=mostPopularArrayList[position]
       holder.itemView.apply {
           if (mostPopular.media!=null && mostPopular.media.isNotEmpty()){
               if (mostPopular.media[0].mediaMetadata!=null && mostPopular.media[0].mediaMetadata.isNotEmpty()){
                   var url = mostPopular.media[0].mediaMetadata[0].url
                   Picasso.get().load(url).fit()
                       .placeholder(R.drawable.placeholder_cricle).into(holder.itemImage)
               }
           }
           holder.itemTitleTextView.text = mostPopular.title
           holder.itemSubTitleTextView.text = mostPopular.abstractValue
           holder.itemDateTimeTextView.text = mostPopular.publishedDate
        }
        holder.itemView.setOnClickListener {iMostPopularClickEvent.onClickEvent(mostPopular)}
    }

    override fun getItemCount(): Int {
        return mostPopularArrayList.size
    }

    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemTitleTextView: AppCompatTextView = view.findViewById(R.id.itemTitleTextView)
        val itemSubTitleTextView: AppCompatTextView = view.findViewById(R.id.itemSubTitleTextView)
        val itemDateTimeTextView: AppCompatTextView = view.findViewById(R.id.itemDateTimeTextView)
        val itemImage: ImageView = view.findViewById(R.id.itemImage)
    }
}