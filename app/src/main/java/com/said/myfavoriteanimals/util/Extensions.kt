package com.said.myfavoriteanimals.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.said.myfavoriteanimals.R

fun ImageView.downloadFromUrl(url: String?) {
    val options = RequestOptions()
        .placeholder(placeholderProgressBar(context))
        .error(R.drawable.ic_launcher_foreground)
        .centerCrop()

    Glide
        .with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this);
}

fun placeholderProgressBar(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}

@BindingAdapter("android:downloadImage")
fun downloadImage(view: ImageView, url: String?) {
    view.downloadFromUrl(url)
}