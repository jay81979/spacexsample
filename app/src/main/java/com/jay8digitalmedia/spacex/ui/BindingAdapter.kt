package com.jay8digitalmedia.spacex.ui.launchlist

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("app:launchName")
fun launchName(textView: TextView, name: String?) {
    name?.let {
        textView.text = String.format("Name: %s", it)
    }
}

@BindingAdapter("app:launchDate")
fun launchDate(textView: TextView, date: Date?) {
    date?.let {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        textView.text = String.format("Date: %s", formatter.format(it))
    }
}

@BindingAdapter("app:launchSuccess")
fun launchSuccess(textView: TextView, success: Boolean) {
    textView.text = if (success) "Successful" else "Unsuccessful"
}
