package com.user576.tonometerrecorder

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso

class TonometerFragment : Fragment(R.layout.tonometer_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageView = view.findViewById<ImageView>(R.id.image_view)
        Picasso.get().load(IMG_URL).into(imageView)
    }

    companion object {
        private const val IMG_URL = "https://raw.githubusercontent.com/user576g/TonometerRecorder/main/screenshots/tonometer.jpg"
    }
}